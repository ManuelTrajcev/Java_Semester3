package algoritmi.labs.lab7;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {
//
//    K key;
//    E value;
//
//    public MapEntry(K key, E val) {
//        this.key = key;
//        this.value = val;
//    }
//
//    public int compareTo(K that) {
//        @SuppressWarnings("unchecked")
//        MapEntry<K, E> other = (MapEntry<K, E>) that;
//        return this.key.compareTo(other.key);
//    }
//
//    public String toString() {
//        return "<" + key + "," + value + ">";
//    }
//}

//class SLLNode<E> {
//    protected E element;
//    protected SLLNode<E> succ;
//
//    public SLLNode(E elem, SLLNode<E> succ) {
//        this.element = elem;
//        this.succ = succ;
//    }
//
//
//    @Override
//    public String toString() {
//        return element.toString();
//    }
//}
//
//
//class CBHT<K extends Comparable<K>, E> {
//    private SLLNode<MapEntry<K, E>>[] buckets;
//
//    @SuppressWarnings("unchecked")
//    public CBHT(int m) {
//        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
//    }
//
//    private int hash(K key) {
//        return Math.abs(key.hashCode()) % buckets.length;
//    }
//
//    public SLLNode<MapEntry<K, E>> search(K targetKey) {
//        int b = hash(targetKey);
//        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
//                return curr;
//        }
//        return null;
//    }
//
//    public void insert(K key, E val) {
//        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
//        int b = hash(key);
//        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
//                curr.element = newEntry;
//                return;
//            }
//        }
//        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
//    }
//
//
//    public void delete(K key) {
//        int b = hash(key);
//        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
//            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
//                if (pred == null)
//                    buckets[b] = curr.succ;
//                else
//                    pred.succ = curr.succ;
//                return;
//            }
//        }
//    }
//
//    public String toString() {
//        String temp = "";
//        for (int i = 0; i < buckets.length; i++) {
//            temp += i + ":";
//            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
//                temp += curr.element.toString() + " ";
//            }
//            temp += "\n";
//        }
//        return temp;
//    }
//}
//

public class RoutingHashJava {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        CBHT<String, List<String>> routingTable = new CBHT<>(2 * n);

        for (int i = 0; i < n; i++) {
            String root = sc.nextLine();
            String[] interfaces = sc.nextLine().split(",");
            List<String> tmp = new ArrayList<>(List.of(interfaces));
            routingTable.insert(root, tmp);
        }

        n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String from = sc.nextLine();

            String[] parts = sc.nextLine().split("\\.");
            String to = "";
            for (int j = 0; j < 3; j++) {
                to += parts[j] + ".";
            }
            to += "0";

            if (routingTable.search(from) != null) {
                if (routingTable.search(from).element.value.contains(to)) {
                    System.out.println("postoi");

                } else {
                    System.out.println("ne postoi");
                }
            } else {
                System.out.println("ne postoi");
            }


//        CBHT<String, List<String>> hashTable = new CBHT<>(2 * n);
//        for (int i = 0; i < n; i++) {
//            String router = sc.nextLine();
//            String[] networks = sc.nextLine().split(",");
//            List<String> nets = new ArrayList<>();
//            for (String s : networks) {
//                String[] parts = s.split("\\.");
//                String network = parts[0] + "." + parts[1] + "." + parts[2];
//                nets.add(network);
//            }
//            hashTable.insert(router, nets);
//        }
//        n = sc.nextInt();
//        sc.nextLine();
//        for (int i = 0; i < n; i++) {
//            String router = sc.nextLine();
//            String[] parts = sc.nextLine().split("\\.");
//            String network = parts[0] + "." + parts[1] + "." + parts[2];
//            if (hashTable.search(router) == null) {
//                System.out.println("ne postoi");
//            } else {
//                if (hashTable.search(router).element.value.contains(network)) {
//                    System.out.println("postoi");
//                } else {
//                    System.out.println("ne postoi");
//                }
//            }
//
//        }
        }
    }
}