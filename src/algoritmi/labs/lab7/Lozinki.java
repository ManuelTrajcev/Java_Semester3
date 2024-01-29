package algoritmi.labs.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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


class OBHT<K extends Comparable<K>, E> {

    private MapEntry<K, E>[] buckets;

    static final int NONE = -1;

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final MapEntry former = new MapEntry(null, null);

    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT(int m) {
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public MapEntry<K, E> getBucket(int i) {
        return buckets[i];
    }


    public int search(K targetKey) {
        int b = hash(targetKey);
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
                b = (b + 1) % buckets.length;
        }
    }


    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else
                b = (b + 1) % buckets.length;
        }
    }


    @SuppressWarnings("unchecked")
    public void delete(K key) {
        int b = hash(key);
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
                return;
            } else {
                b = (b + 1) % buckets.length;
            }
        }
    }


    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K, E> clone() {
        OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K, E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
}

public class Lozinki {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String> passwords = new CBHT<>(2 * N);

        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split("\\s+");
            passwords.insert(parts[0], parts[1]);
        }

        String attempt = br.readLine();

        while (!attempt.equals("KRAJ")) {
            String[] parts = attempt.split("\\s+");
            if (passwords.search(parts[0]) != null){
                if (passwords.search(parts[0]).element.value.equals(parts[1])){
                    System.out.println("Najaven");
                    break;
                } else {
                    System.out.println("Nenajaven");
                }
            } else {
                System.out.println("Nenajaven");
            }

            attempt = br.readLine();
        }


//        OBHT<String, String> hashTable = new OBHT<>(97);
//        for (int i = 1; i <= N; i++) {
//            String imelozinka = br.readLine();
//            String[] pom = imelozinka.split(" ");
//            hashTable.insert(pom[0], pom[1]);
//        }
//        String line = br.readLine();
//        while (!line.equals("KRAJ")) {
//            String[] imelozinka = line.split("\\s+");
//            if (hashTable.search(imelozinka[0]) == -1) {
//                System.out.println("Nenajaven");
//            } else {
//                if (hashTable.getBucket(hashTable.search(imelozinka[0])).value.equals(imelozinka[1])) {
//                    System.out.println("Najaven");
//                    break;
//                } else {
//                    System.out.println("Nenajaven");
//                }
//            }
//
//
//            line = br.readLine();
//        }

    }
}