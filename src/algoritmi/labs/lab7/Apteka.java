package algoritmi.labs.lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Lek {
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina += kolicina;
    }

    public int getPozLista() {
        return pozLista;
    }

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        if (pozLista == 1) return ime + "\n" + "POZ\n" + cena + "\n" + kolicina;
        else return ime + "\n" + "NEG\n" + cena + "\n" + kolicina;
    }
}

class LekKluch {
    String ime;

    public LekKluch(String ime) {
        this.ime = ime.toUpperCase();
    }

}


class CBHT<K, E> {
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        int c1 = (int) key.toString().toUpperCase().charAt(0);
        int c2 = (int) key.toString().toUpperCase().charAt(1);
        int c3 = (int) key.toString().toUpperCase().charAt(2);
        //h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780
        int h = (29 * (29 * (29 * 0 + (c1)) + (c2)) + (c3)) % 102780;
        return h % buckets.length;
    }
//        private int hash (K key){
//            int h = (29 * (29 * (29 * 0 + ((String) key).charAt(0)) + ((String) key).charAt(1)) + ((String) key).charAt(2)) % 102780;
//            return h % buckets.length;
//        }


    // probajte da ja reshite ovaa zadacha bez koristenje na ovoj metod
    // try to solve this task without using this method

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                if (pred == null) buckets[b] = curr.succ;
                else pred.succ = curr.succ;
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

}

class MapEntry<K, E> {
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
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

public class Apteka {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        CBHT<String, Lek> lekovi = new CBHT<>(2 * n);


        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            if (lekovi.search(parts[0]) == null) {
                lekovi.insert(parts[0], new Lek(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
            } else {
                lekovi.search(parts[0]).element.value.setKolicina(Integer.parseInt(parts[3]));
            }
        }
//        System.out.println(lekovi);

        String find = sc.nextLine().toUpperCase();

        while (!find.equals("KRAJ")) {
            int quantity = Integer.parseInt(sc.nextLine());
            if (lekovi.search(find) != null) {
                Lek found = lekovi.search(find).element.value;
                System.out.println(found.ime);
            } else {
                System.out.println("Nema takov lek");
            }

            find = sc.nextLine();
        }


//        CBHT<String, Lek> hashTable = new CBHT<>(7013);
//        List<Lek> lekovi = new ArrayList<>();
////        String ime, int pozLista, int cena, int kolicina
//        for (int i = 0; i < n; i++) {
//            String[] inputs = sc.nextLine().split("\\s+");
//            if (hashTable.search(inputs[0].toUpperCase()) == null) {
//                hashTable.insert(inputs[0].toUpperCase(), new Lek(inputs[0].toUpperCase(), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3])));
//            } else {
//                int a = hashTable.search(inputs[0]).element.value.kolicina;
//                hashTable.delete(hashTable.search(inputs[0]).element.key);
//                hashTable.insert(inputs[0], new Lek(inputs[0], Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]) + a));
//            }
//        }
//
//        String line = sc.nextLine();
//        while (!line.equals("KRAJ")) {
//            line = line.toUpperCase();
//            int c = Integer.parseInt(sc.nextLine());
//            if (hashTable.search(line) == null) {
//                System.out.println("Nema takov lek");
//            } else {
//                System.out.println(hashTable.search(line).element.value);
//                int ck = hashTable.search(line).element.value.getKolicina();
//                if (hashTable.search(line).element.value.getKolicina() >= c) {
//                    System.out.println("Napravena naracka");
//                    hashTable.search(line).element.value.setKolicina(ck - c);
//                } else {
//                    System.out.println("Nema dovolno lekovi");
//                }
//            }
//            line = sc.nextLine();
//        }
    }
}
