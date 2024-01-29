//package mk.ukim.finki.kolokviumski;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//
//class AmountNotAllowedException extends Exception {
//    public AmountNotAllowedException(String message) {
//        super(message);
//    }
//}
//
//class Item {
//    int price;
//    String type;
//
//    public Item(int price, String type) {
//        this.price = price;
//        this.type = type;
//    }
//
//    double getDDV() {
//        if (type.equals("A")) {
//            return price * 0.18;
//        } else if (type.equals("B")) {
//            return price * 0.05;
//        } else {
//            return 0;
//        }
//    }
//}
//
//class Fiskalna {
//    String id;
//    List<Item> items;
//    int totalAmount;
//
//    public Fiskalna(String id, List<Item> items) throws AmountNotAllowedException {
//        this.id = id;
//        this.items = items;
//        this.totalAmount = items.stream().mapToInt(i -> i.price).sum();
//        if (totalAmount >= 30000) {
//            throw new AmountNotAllowedException(String.format("Receipt with amount %d is not allowed to be scanned", totalAmount));
//        }
//    }
//
//    double returnedDDV() {
//        double total = items.stream().mapToDouble(i -> i.getDDV()).sum();
//        return total * 0.15;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s %d %.2f", id, totalAmount, returnedDDV());
//    }
//}
//
//class MojDDV {
//    List<Fiskalna> fiskalni;
//
//    public MojDDV() {
//        this.fiskalni = new ArrayList<>();
//    }
//
//    Fiskalna fiskalnaFactory(String line) {
//        String[] parts = line.split("\\s+");
//        String id = parts[0];
//        List<Item> items = new ArrayList<>();
//        for (int i = 1; i < parts.length; i++) {
//            int p = Integer.parseInt(parts[i]);
//            String c = parts[++i];
//            items.add(new Item(p, c));
//        }
//        try {
//            return new Fiskalna(id, items);
//        } catch (AmountNotAllowedException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//
//    void readRecords(InputStream inputStream) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//        br.lines().map(this::fiskalnaFactory).filter(Objects::nonNull).forEach(fiskalna -> fiskalni.add(fiskalna));
//    }
//
//    void printTaxReturns(OutputStream outputStream) {
//        PrintWriter pw = new PrintWriter(outputStream);
//        fiskalni.forEach(fiskalna -> pw.println(fiskalna));
//        pw.flush();
//        pw.close();
//    }
//}
//
//public class MojDDVTest {
//
//    public static void main(String[] args) {
//
//        MojDDV mojDDV = new MojDDV();
//
//        System.out.println("===READING RECORDS FROM INPUT STREAM===");
//        mojDDV.readRecords(System.in);
//
//        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
//        mojDDV.printTaxReturns(System.out);
//
//    }
//}