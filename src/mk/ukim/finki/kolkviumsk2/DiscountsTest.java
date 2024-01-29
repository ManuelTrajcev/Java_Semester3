package mk.ukim.finki.kolkviumsk2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Item {
    int priceWithDiscount;
    int regularPrice;

    public Item(String input) {
        String[] parts = input.split(":");
        this.priceWithDiscount = Integer.parseInt(parts[0]);
        this.regularPrice = Integer.parseInt(parts[1]);
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public float discountPercentage() {
        return (float) Math.floor((regularPrice - priceWithDiscount) / (float) regularPrice * 100.0);
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", (int) discountPercentage(), priceWithDiscount, regularPrice);
    }
}

class Store {
    String name;
    List<Item> items;

    public Store(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(new Item(item));
    }

    public float totalDiscount() {
        return (float) items.stream().mapToDouble(i -> i.getRegularPrice() - i.getPriceWithDiscount()).sum();
    }

    public float avgDiscount() {
        float f = (float) (items.stream().mapToDouble(Item::discountPercentage).sum() / items.size());
        return f;
    }

    @Override
    public String toString() {
        items = items.stream().sorted(Comparator.comparing(Item::discountPercentage).thenComparing(Item::getRegularPrice).reversed()).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s\nAverage discount: %.1f%%\nTotal discount: %.0f", name, avgDiscount(), totalDiscount()));
        items.forEach(item -> stringBuilder.append("\n").append(item));
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }
}

class Discounts {
    List<Store> stores;

    public Discounts() {
        this.stores = new ArrayList<>();
    }

    private Store storeFactory(String input) {
        String[] parts = input.split("\\s+");
        Store newStore = new Store(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            newStore.addItem(parts[i]);
        }

        return newStore;
    }

    public int readStores(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines().forEach(line -> stores.add(storeFactory(line)));
        return stores.size();
    }

    public List<Store> byAverageDiscount() {
        List<Store> sorted = stores.stream().sorted(Comparator.comparing(Store::avgDiscount).reversed()).collect(Collectors.toList());
        return sorted.subList(0, 3);
    }

    public List<Store> byTotalDiscount() {
        List<Store> sorted = stores.stream().sorted(Comparator.comparing(Store::totalDiscount).thenComparing(Store::getName)).collect(Collectors.toList());
        return sorted.subList(0, 3);
    }
}

public class DiscountsTest {
    public static void main(String[] args) {
        Discounts discounts = new Discounts();
        int stores = discounts.readStores(System.in);
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
        discounts.byAverageDiscount().forEach(System.out::println);
        System.out.println("=== By total discount ===");
        discounts.byTotalDiscount().forEach(System.out::println);
    }
}
