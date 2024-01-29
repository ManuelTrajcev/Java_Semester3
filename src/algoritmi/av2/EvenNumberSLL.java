package algoritmi.av2;

import java.util.Scanner;

public class EvenNumberSLL {
    public static int evenNumber(SLL<Integer> list) {
        SLLNode<Integer> tmp = list.getFirst();
        int count = 0;
        while (tmp != null) {
            if (tmp.element % 2 == 0) {
                count++;
            }
            tmp = tmp.succ;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert number of elements: ");
        int n = sc.nextInt();

        SLL<Integer> list = new SLL<>();
        System.out.println("Insert the elements of the list:");
        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }
        System.out.println("Number of even numbers in the list: " + evenNumber(list));
        sc.close();
    }

}
