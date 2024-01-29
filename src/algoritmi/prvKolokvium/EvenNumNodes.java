package algoritmi.prvKolokvium;

import algoritmi.av2.SLL;
import algoritmi.av2.SLLNode;

import java.util.ArrayList;
import java.util.Scanner;

public class EvenNumNodes {


    public static boolean contains(int a, ArrayList<Integer> l) {
        for (int i = 0; i < l.size(); i++) {
            if (a == l.get(i)) {
                l.remove(a);
                return true;
            }
        }
        l.add(a);
        return false;
    }

    public static SLLNode<Integer> findBeforeAndDelete(int a, SLL<Integer> list) {
        SLLNode<Integer> sll = list.getFirst();
        while (sll != null) {
            if (sll.getElement() == a){
                return sll;
            }
            sll = sll.getSucc();
        }
        return sll;
    }


    public static void deleteEven(SLL<Integer> list) {
        ArrayList<Integer> count = new ArrayList<>();
        SLLNode<Integer> curr = list.getFirst();

        while (curr != null) {
            int a = curr.getElement();
            if (contains(a, count)) {
                list.delete(findBeforeAndDelete(a, list));
                list.delete(curr);
            }

            curr = curr.getSucc();
        }
    }

    public static void main(String[] args) {
        SLL<Integer> list = new SLL<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        System.out.println(list);
        deleteEven(list);
        System.out.println(list);
    }
}
