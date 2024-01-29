package mk.ukim.finki.kolkviumsk2;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}


class Product {
    String category;
    String id;
    String name;
    LocalDateTime createdAt;
    double price;
    int quantitySold;

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.quantitySold = 0;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold += quantitySold;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + quantitySold +
                '}';
    }
}


class OnlineShop {
    Map<String, Product> products;

    public OnlineShop() {
        products = new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price) {
        products.put(id, new Product(category, id, name, createdAt, price));
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException {
        if (!products.containsKey(id))
            throw new ProductNotFoundException(String.format("Product with id %s does not exist in the online shop!", id));
        products.get(id).setQuantitySold(quantity);
        return products.get(id).price * quantity;
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<List<Product>> result = new ArrayList<>();
        List<Product> sorted = new ArrayList<>();

        if (category == null) {
            sorted = products.values().stream().collect(Collectors.toList());
        } else {
            sorted = products.values().stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
        }

        switch (comparatorType) {
            case NEWEST_FIRST:
                sorted = sorted.stream().sorted(Comparator.comparing(Product::getCreatedAt).reversed()).collect(Collectors.toList());
                break;
            case OLDEST_FIRST:
                sorted = sorted.stream().sorted(Comparator.comparing(Product::getCreatedAt)).collect(Collectors.toList());
                break;
            case LEAST_SOLD_FIRST:
                sorted = sorted.stream().sorted(Comparator.comparing(Product::getQuantitySold)).collect(Collectors.toList());
                break;
            case MOST_SOLD_FIRST:
                sorted = sorted.stream().sorted(Comparator.comparing(Product::getQuantitySold).reversed()).collect(Collectors.toList());
                break;
            case LOWEST_PRICE_FIRST:
                sorted = sorted.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
                break;
            case HIGHEST_PRICE_FIRST:
                sorted = sorted.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
                break;
        }
        if (pageSize == 0) {
            result.add(sorted);
        } else {
            int n = sorted.size() / pageSize;
            int i;
            for (i = 0; i < n; i++) {
                result.add(sorted.subList(i * pageSize, i * pageSize + pageSize));
            }
            if (sorted.size() % pageSize != 0) {
                result.add(sorted.subList(i * pageSize, sorted.size()));
            }
        }
        return result;
    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category = null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);
    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}

