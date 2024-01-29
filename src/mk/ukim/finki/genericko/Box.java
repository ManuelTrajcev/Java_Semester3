package mk.ukim.finki.genericko;

import java.util.ArrayList;
import java.util.Random;

public class Box<T> {
    ArrayList<T> items;

    public Box() {
        this.items = new ArrayList<>();
    }

    public void add(T newItem) {
        items.add(newItem);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public T drawItem() {
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.add("Dexter");
        stringBox.add("Seinfeld");
        stringBox.add("Barney");
        stringBox.add("Sheldon");
        stringBox.add("Costanza");
        stringBox.add("Hank");
        System.out.println(stringBox.drawItem());
        Box<Integer> intBox = new Box<>();
        intBox.add(23);
        intBox.add(15);
        intBox.add(19);
        intBox.add(3);
        intBox.add(92);
        System.out.println(intBox.drawItem());
    }
}
