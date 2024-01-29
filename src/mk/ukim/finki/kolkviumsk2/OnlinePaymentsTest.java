package mk.ukim.finki.kolkviumsk2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

class ItemClass implements Comparable<ItemClass> {
    String item;
    double price;

    public ItemClass(String item, double price) {
        this.item = item;
        this.price = price;
    }

    public int getPrice() {
        return (int) price;
    }

    public String getItem() {
        return item;
    }

    public double getFee() {
        return price * 0.0114;
    }

    @Override
    public int compareTo(ItemClass o) {
        return Integer.compare((int) o.price, (int) this.price);
    }
}

class OnlinePayments {
    HashMap<String, List<ItemClass>> students;

    public OnlinePayments() {
        this.students = new HashMap<>();
    }

    public void readItems(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        br.lines().forEach(l -> {
            String[] parts = l.split(";");
            if (students.containsKey(parts[0])) {
                students.get(parts[0]).add(new ItemClass(parts[1], Double.parseDouble(parts[2])));
            } else {
                ArrayList<ItemClass> items = new ArrayList<>();
                items.add(new ItemClass(parts[1], Double.parseDouble(parts[2])));
                students.put(parts[0], items);
            }
        });
    }

    public void printStudentReport(String index, OutputStream os) {
        if (!students.containsKey(index)) {
            System.out.println(String.format("Student %s not found!", index));
            return;
        }
        students.values().stream().forEach(i -> i.sort(Comparator.naturalOrder()));
        int net = students.get(index).stream().mapToInt(i -> i.getPrice()).sum();
        int fee = (int) Math.round(students.get(index).stream().mapToDouble(i -> i.getFee()).sum());
        if (fee < 3) fee = 3;
        if (fee > 300) fee = 300;
        int total = net + fee;
        int i = 1;

        System.out.println(String.format("Student: %s Net: %d Fee: %d Total: %d", index, net, fee, total));
        System.out.println("Items:");
        for (ItemClass it : students.get(index)) {
            System.out.println(String.format("%d. %s %d", i++, it.getItem(), it.getPrice()));
        }
    }
}

public class OnlinePaymentsTest {
    public static void main(String[] args) {
        OnlinePayments onlinePayments = new OnlinePayments();

        onlinePayments.readItems(System.in);

        IntStream.range(151020, 151025).mapToObj(String::valueOf).forEach(id -> onlinePayments.printStudentReport(id, System.out));
    }
}