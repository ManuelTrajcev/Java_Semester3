package mk.ukim.finki.av9;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class TheSieveOfEarathosten {
    public static void sieve(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int curr = list.get(i);
            ListIterator<Integer> it = list.listIterator(i+1);
            while (it.hasNext()) {
                if (it.next() % curr == 0){
                    it.remove();
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        System.out.println(list);
        sieve(list);
        System.out.println(list);
    }
}
