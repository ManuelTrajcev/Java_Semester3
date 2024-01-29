package algoritmi.labs.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static algoritmi.labs.lab7.OBHT.NONE;

//class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {
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


//class OBHT<K extends Comparable<K>, E> {
//
//    private MapEntry<K, E>[] buckets;
//
//    static final int NONE = -1;
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    private static final MapEntry former = new MapEntry(null, null);
//
//    private int occupancy = 0;
//
//    @SuppressWarnings("unchecked")
//    public OBHT(int m) {
//        buckets = (MapEntry<K, E>[]) new MapEntry[m];
//    }
//
//    private int hash(K key) {
//        return Math.abs(key.hashCode()) % buckets.length;
//    }
//
//    public MapEntry<K, E> getBucket(int i) {
//        return buckets[i];
//    }
//
//
//    public int search(K targetKey) {
//        int b = hash(targetKey);
//        for (; ; ) {
//            MapEntry<K, E> oldEntry = buckets[b];
//            if (oldEntry == null)
//                return NONE;
//            else if (targetKey.equals(oldEntry.key))
//                return b;
//            else
//                b = (b + 1) % buckets.length;
//        }
//    }
//
//
//    public void insert(K key, E val) {
//        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
//        int b = hash(key);
//        for (; ; ) {
//            MapEntry<K, E> oldEntry = buckets[b];
//            if (oldEntry == null) {
//                if (++occupancy == buckets.length) {
//                    System.out.println("Hash tabelata e polna!!!");
//                }
//                buckets[b] = newEntry;
//                return;
//            } else if (oldEntry == former
//                    || key.equals(oldEntry.key)) {
//                buckets[b] = newEntry;
//                return;
//            } else
//                b = (b + 1) % buckets.length;
//        }
//    }
//
//
//    @SuppressWarnings("unchecked")
//    public void delete(K key) {
//        int b = hash(key);
//        for (; ; ) {
//            MapEntry<K, E> oldEntry = buckets[b];
//            if (oldEntry == null)
//                return;
//            else if (key.equals(oldEntry.key)) {
//                buckets[b] = former;
//                return;
//            } else {
//                b = (b + 1) % buckets.length;
//            }
//        }
//    }
//
//
//    public String toString() {
//        String temp = "";
//        for (int i = 0; i < buckets.length; i++) {
//            temp += i + ":";
//            if (buckets[i] == null)
//                temp += "\n";
//            else if (buckets[i] == former)
//                temp += "former\n";
//            else
//                temp += buckets[i] + "\n";
//        }
//        return temp;
//    }
//
//
//    public OBHT<K, E> clone() {
//        OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
//        for (int i = 0; i < buckets.length; i++) {
//            MapEntry<K, E> e = buckets[i];
//            if (e != null && e != former)
//                copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
//            else
//                copy.buckets[i] = e;
//        }
//        return copy;
//    }
//}


public class Preveduvac {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        OBHT<String, String> translate = new OBHT<>(2 * n);

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            translate.insert(parts[1], parts[0]);
        }

        String word = sc.nextLine();

        while (!word.equals("KRAJ")){
            if (translate.search(word) != -1) {
                System.out.println(translate.getBucket(translate.search(word)).value);
            } else {
                System.out.println("/");
            }

            word = sc.nextLine();
        }

//        OBHT<String, String> hashTable = new OBHT<>(97);
//        for (int i = 0; i < n; i++) {
//            String[] words = sc.nextLine().split("\\s+");
//            if (hashTable.search(words[1]) == -1) {
//                hashTable.insert(words[1], words[0]);
//            }
//        }
//        String line = sc.nextLine();
//        while (!line.equals("KRAJ")) {
//            if (hashTable.search(line) == -1) {
//                System.out.println("/");
//            } else {
//                System.out.println(hashTable.getBucket(hashTable.search(line)).value);
//            }
//
//            line = sc.nextLine();
//        }
    }
}
