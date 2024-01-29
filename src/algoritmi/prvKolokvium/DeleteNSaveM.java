//package algoritmi.prvKolokvium;
//
//import java.util.Scanner;
//
//public class DeleteNSaveM {
//
//    public static void transformSll(SLL1<Integer> sll, int n, int m) {
//        SLLNode1<Integer> curr = sll.getFirst();
//
//        while (curr != null) {
//            for (int i = 0; i < n; i++) {
//                if (curr == null) break;
//                curr = curr.succ;
//            }
//            for (int i = 0; i < m; i++) {
//                if (curr == null) break;
//                sll.delete(curr);
//                curr = curr.succ;
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        SLL1<Integer> sll = new SLL1<>();
//
//        for (int i = 0; i < n; i++) {
//            sll.insertLast(scanner.nextInt());
//        }
//
//        n = scanner.nextInt();
//        int m = scanner.nextInt();
//        transformSll(sll, n, m);
//
//        System.out.println(sll);
//    }
//}
