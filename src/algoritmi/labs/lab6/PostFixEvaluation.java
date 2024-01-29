//package algoritmi.labs.lab6;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.NoSuchElementException;
//
//
//public class PostFixEvaluation {
//
//    static boolean isInt(String s) {
//        try {
//            int a = Integer.parseInt(s);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//
//        return true;
//    }
//
//    static int operation(int a, int b, String c) {
//        if (c.equals("+")) {
//            return a + b;
//        }
//        if (c.equals("-")) {
//            return a - b;
//        }
//        if (c.equals("*")) {
//            return a * b;
//        }
//        if (c.equals("/")) {
//            return a / b;
//        }
//
//        return -1;
//    }
//
//    static int evaluatePostfix(char[] izraz, int n) {
//        ArrayStack<Integer> arr = new ArrayStack<>(n);
//        String expression = String.copyValueOf(izraz);
//        String[] parts = expression.split("\\s+");
//        int a, b;
//
//
//        for (int i = 0; i < parts.length; i++) {
//            if (isInt(parts[i])) {
//                a = Integer.parseInt(parts[i]);
//                arr.push(a);
//            } else {
//                b = arr.pop();
//                a = arr.pop();
//                arr.push(operation(a, b, parts[i]));
//            }
//        }
//
//        return arr.pop();
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String expression = br.readLine();
//        char exp[] = expression.toCharArray();
//
//        int rez = evaluatePostfix(exp, exp.length);
//        System.out.println(rez);
//
//        br.close();
//
//    }
//
//}