package algoritmi.av2;

import java.util.Scanner;

public class JoinSortedList<E extends Comparable<E>> {
    public SLL<E> join(SLL<E> list1, SLL<E> list2) {
        SLL<E> result = new SLL<>();
        SLLNode<E> tmp1 = list1.getFirst();
        SLLNode<E> tmp2 = list2.getFirst();

        while (tmp1 != null && tmp2 != null) {
            if (tmp1.element.compareTo(tmp2.element) < 0) {
                result.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            } else {
                result.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
        }
        if (tmp1 != null) {
            while (tmp1 != null) {
                result.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
        }
        if (tmp2 != null) {
            while (tmp2 != null) {
                result.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<String> str1 = new SLL<>();
        SLL<String> str2 = new SLL<>();
        String garbage;

        System.out.print("Insert number of elements in the first list: ");
        int numberFirst = scanner.nextInt();
        System.out.print("Insert number of elements in the second list: ");
        int numberSecond = scanner.nextInt();
        System.out.println("Insert the elements of the first list: ");
        garbage = scanner.nextLine();
        for (int i = 0; i < numberFirst; i++) {
            str1.insertLast(scanner.nextLine());

        }
        System.out.println("Insert the elements of the second list: ");
        for (int i = 0; i < numberSecond; i++) {
            str2.insertLast(scanner.nextLine());
        }

        JoinSortedList<String> js = new JoinSortedList<>();
        System.out.println("List after joining: " + js.join(str1, str2));
    }

}
