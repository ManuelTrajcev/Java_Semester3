//package mk.ukim.finki.kolkviumsk2;
//
//import java.util.*;
//
//
//class Block<T extends Comparable<T>> {
//    TreeSet<T> elements;
//
//    public Block() {
//        this.elements = new TreeSet<>();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[");
//        elements.stream().limit(elements.size()-1).forEach(i -> stringBuilder.append(i).append(", "));
//        stringBuilder.append(elements.getLast());
//        stringBuilder.append("]");
//        return stringBuilder.toString();
//    }
//
//
//}
//
//class BlockContainer<T extends Comparable<T>> {
//    int size;
//    Map<Integer, Block<T>> blocks;
//    int last;
//
//    public BlockContainer(int size) {
//        this.size = size;
//        last = -1;
//        blocks = new LinkedHashMap<>();
//    }
//
//    public void add(T element) {
//        if (last == -1) {
//            blocks.put(0, new Block<>());
//            last++;
//        }
//        if (blocks.get(last).elements.size() == size) {
//            last++;
//            blocks.put(last, new Block<>());
//        }
//        blocks.get(last).elements.add(element);
//    }
//
//    public void remove(T element) {
//        blocks.get(last).elements.remove(element);
//        if (blocks.get(last).elements.isEmpty()) {
//            blocks.remove(last);
//            last--;
//        }
//    }
//
//    public void sort() {
//
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < last; i++) {
//            stringBuilder.append(blocks.get(i)).append(",");
//        }
//        stringBuilder.append(blocks.get(last));
//        return stringBuilder.toString();
//    }
//}
//
//public class BlockContainerTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int size = scanner.nextInt();
//        BlockContainer<Integer> integerBC = new BlockContainer<Integer>(size);
//        scanner.nextLine();
//        Integer lastInteger = null;
//        for (int i = 0; i < n; ++i) {
//            int element = scanner.nextInt();
//            lastInteger = element;
//            integerBC.add(element);
//        }
//        System.out.println("+++++ Integer Block Container +++++");
//        System.out.println(integerBC);
//        System.out.println("+++++ Removing element +++++");
//        integerBC.remove(lastInteger);
//        System.out.println("+++++ Sorting container +++++");
//        integerBC.sort();
//        System.out.println(integerBC);
//        BlockContainer<String> stringBC = new BlockContainer<String>(size);
//        String lastString = null;
//        for (int i = 0; i < n; ++i) {
//            String element = scanner.next();
//            lastString = element;
//            stringBC.add(element);
//        }
//        System.out.println("+++++ String Block Container +++++");
//        System.out.println(stringBC);
//        System.out.println("+++++ Removing element +++++");
//        stringBC.remove(lastString);
//        System.out.println("+++++ Sorting container +++++");
//        stringBC.sort();
//        System.out.println(stringBC);
//    }
//}
//
//// Вашиот код овде
//
//
//
