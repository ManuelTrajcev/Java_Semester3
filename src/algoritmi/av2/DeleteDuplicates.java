package algoritmi.av2;

import java.util.Scanner;

public class DeleteDuplicates<E> {

    public E delete(DLLNode<E> node) {
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }
    public void deleteDoubles(DLL<E> list) {
        DLLNode<E> tmp = list.getFirst();
        while (tmp != null) {
            DLLNode<E> tmp1 = tmp.succ;
            while (tmp1 != null) {
                if (tmp.element.equals(tmp1.element)) {
                    this.delete(tmp1);
                }
                tmp1 = tmp1.succ;
            }
            tmp = tmp.succ;
        }
    }

    public static void main(String[] args) {
        DLL<Integer> list = new DLL<Integer>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            list.insertLast(m);
        }
        list.deleteDoubles(list);
        System.out.println(list);
    }
}
