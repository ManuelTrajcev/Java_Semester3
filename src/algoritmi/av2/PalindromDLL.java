package algoritmi.av2;

import java.util.LinkedList;
import java.util.Scanner;

public class PalindromDLL {
    public static int isPalindrom(DLL<Integer> list) {
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();
        while (first != last && first.pred != last) {
            if (!first.element.equals(last.element)) {
                return -1;
            }
            first = first.succ;
            last = last.pred;
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DLL<Integer> list = new DLL<>();
        LinkedList<Integer> list1 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int tmp = in.nextInt();
            list.insertLast(tmp);
            list1.add(tmp);
        }
        in.close();
        System.out.println(isPalindrom(list));
    }
}
