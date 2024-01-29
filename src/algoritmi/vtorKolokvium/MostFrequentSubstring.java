package algoritmi.vtorKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

    public SLLNode<MapEntry<K, E>> firstBucket() {
        return buckets[0];
    }

    public SLLNode<MapEntry<K, E>>[] getBuckets() {
        return buckets;
    }


}

public class MostFrequentSubstring {
    public static String mostFrequent(CBHT<String, Integer> table) {
        SLLNode<MapEntry<String, Integer>>[] buckets = table.getBuckets();
        SLLNode<MapEntry<String, Integer>> max = null;
        for (int i = 0; i < buckets.length; i++) {
            SLLNode<MapEntry<String, Integer>> curr = buckets[i];
            while (curr != null) {
                if (max == null) max = curr;
                if (curr.element.value > max.element.value) {
                    max = curr;
                } else if (curr.element.value == max.element.value) {
                    if (curr.element.key.length() > max.element.key.length()) {
                        max = curr;
                    }
                }
                curr = curr.succ;
            }
        }

        return max.element.key;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().trim();
        CBHT<String, Integer> tabela = new CBHT<String, Integer>(599);

        String firstElement = word.substring(0, 1);

        for (int i = 1; i < word.length(); i++) {
            for (int j = 0; j + i <= word.length(); j++) {
                String substring = word.substring(j, i + j);
                int count = 1;
                if (tabela.search(substring) != null) {
                    count = tabela.search(substring).element.value + 1;
                }
                tabela.insert(substring, count);
            }
        }

        tabela.insert(word, 1);

        System.out.println(mostFrequent(tabela));
    }
}
