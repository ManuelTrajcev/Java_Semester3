//package algoritmi.vtorKolokvium;
//
//import java.util.Scanner;
//
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
//        return "(" + key + "," + value + ")";
//    }
//}
//
//class SLLNode<E> {
//    protected E element;
//    protected SLLNode<E> succ;
//
//    public SLLNode(E elem, SLLNode<E> succ) {
//        this.element = elem;
//        this.succ = succ;
//    }
//
//    @Override
//    public String toString() {
//        return element.toString();
//    }
//}
//
//class CBHT<K extends Comparable<K>, E> {
//
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
//    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
//        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
//        int b = hash(key);
//        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
//            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
//                curr.succ.element = newEntry;
//                return;
//            }
//        }
//        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
//    }
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
//
//    public SLLNode<MapEntry<K, E>> firstBucket() {
//        return buckets[0];
//    }
//
//    public SLLNode<MapEntry<K, E>>[] getBuckets() {
//        return buckets;
//    }
//
//
//}
//
//public class Birthdays {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int count = 0;
//        int n = sc.nextInt();
//        sc.nextLine();
//
//
//        CBHT<Integer, String> birthdays = new CBHT<>(23);
//
//        for (int i = 0; i < n; i++) {
//            String birthday = sc.nextLine();
//            String[] parts = birthday.split("\\.");
//            birthdays.insert(Integer.parseInt(parts[1]), birthday);
//        }
//
//        int m = sc.nextInt();
//
//        SLLNode<MapEntry<Integer, String>> curr = birthdays.search(m);
//
//        if (curr == null) {
//            System.out.println("EMPTY");
//        } else {
//            while (curr != null) {
//                count++;
//                curr = curr.succ;
//            }
//        }
//
//        System.out.println(count);
//    }
//}
