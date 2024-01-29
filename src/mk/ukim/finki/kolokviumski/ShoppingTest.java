package mk.ukim.finki.kolokviumski;

import java.io.BufferedWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(message);
    }
}

class ProductFactory {
    static Product create(String line) throws InvalidOperationException {
        String[] parts = line.split(";");
        if (parts[4].equals("0")) {
            throw new InvalidOperationException("The quantity of the product with id " + parts[1] + " can not be 0.");
        }
        if (parts[0].equals("PS")) {
            return new Ps(parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
        } else {
            return new Ws(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
        }
    }
}

abstract class Product implements Comparable<Product> {
    String productID;
    String productName;
    int productPrice;

    public Product(String productID, String productName, int productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    abstract public double getTotalPrice();

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.getTotalPrice(), o.getTotalPrice());
    }

    public int getProductID() {
        return Integer.parseInt(productID);
    }
}

class Ws extends Product {
    int quantity;

    public Ws(String productID, String productName, int productPrice, int quantity) {
        super(productID, productName, productPrice);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", productID, getTotalPrice());
    }

    @Override
    public double getTotalPrice() {
        return productPrice * quantity;
    }

}

class Ps extends Product {
    double quantity;

    public Ps(String productID, String productName, int productPrice, double quantity) {
        super(productID, productName, productPrice);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", productID, getTotalPrice());
    }

    @Override
    public double getTotalPrice() {
        return productPrice * quantity / 1000;
    }
}


class ShoppingCart {
    static double DISCOUNT = 0.1;
    List<Product> products = new ArrayList<>();


    public void addItem(String s) throws InvalidOperationException {
        products.add(ProductFactory.create(s));

    }

    public void printShoppingCart(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);

        products.stream().sorted(Comparator.reverseOrder()).forEach(p -> pw.println(p));

        pw.flush();
        pw.close();
    }


    boolean isOnDiscount(Product p, List<Integer> discounted) {
        for (int i = 0; i < discounted.size(); i++) {
            if (discounted.get(i) == p.getProductID()) {
                return true;
            }
        }
        return false;
    }

    public void blackFridayOffer(List<Integer> discountItems, PrintStream out) throws InvalidOperationException {
        PrintWriter pw = new PrintWriter(out);

        if (discountItems.isEmpty()) {
            throw new InvalidOperationException("There are no products with discount.");
        }

        products.stream().
                filter(p -> isOnDiscount(p, discountItems))
                .forEach(p -> pw.println(String.format("%s - %.2f", p.productID, p.getTotalPrice() * DISCOUNT)));

        pw.flush();
        pw.close();
    }
}

public class ShoppingTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        int items = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < items; i++) {
            try {
                cart.addItem(sc.nextLine());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Integer> discountItems = new ArrayList<>();
        int discountItemsCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < discountItemsCount; i++) {
            discountItems.add(Integer.parseInt(sc.nextLine()));
        }

        int testCase = Integer.parseInt(sc.nextLine());
        if (testCase == 1) {
            cart.printShoppingCart(System.out);
        } else if (testCase == 2) {
            try {
                cart.blackFridayOffer(discountItems, System.out);
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid test case");
        }
    }
}