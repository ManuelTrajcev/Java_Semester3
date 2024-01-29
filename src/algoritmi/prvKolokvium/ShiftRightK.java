package algoritmi.prvKolokvium;

import algoritmi.av2.SLL;

import java.util.Scanner;

public class ShiftRightK {

    public static void shiftRightK(DLL<Integer> list, int k) {
        DLLNode<Integer> last = list.getLast();
        for (int i = 0; i < k; i++) {
            list.insertFirst(last.element);
            list.deleteLast();
            last = list.getLast();
        }
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            list.insertLast(scanner.nextInt());
        }
        int k = scanner.nextInt();
        System.out.println(list);
        shiftRightK(list, k);
        System.out.println(list);
    }
}
