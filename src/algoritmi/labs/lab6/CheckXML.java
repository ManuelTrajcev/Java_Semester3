//package algoritmi.labs.lab6;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.NoSuchElementException;
//
//
//interface Stack<E> {
//
//    // Elementi na stekot se objekti od proizvolen tip.
//
//    // Metodi za pristap:
//
//    public boolean isEmpty();
//    // Vrakja true ako i samo ako stekot e prazen.
//
//    public E peek();
//    // Go vrakja elementot na vrvot od stekot.
//
//    // Metodi za transformacija:
//
//    public void clear();
//    // Go prazni stekot.
//
//    public void push(E x);
//    // Go dodava x na vrvot na stekot.
//
//    public E pop();
//    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//}
//
//public class ArrayStack<E> implements Stack<E> {
//    private E[] elems;
//    private int depth;
//
//    @SuppressWarnings("unchecked")
//    public ArrayStack(int maxDepth) {
//        // Konstrukcija na nov, prazen stek.
//        elems = (E[]) new Object[maxDepth];
//        depth = 0;
//    }
//
//
//    public boolean isEmpty() {
//        // Vrakja true ako i samo ako stekot e prazen.
//        return (depth == 0);
//    }
//
//
//    public E peek() {
//        // Go vrakja elementot na vrvot od stekot.
//        if (depth == 0)
//            throw new NoSuchElementException();
//        return elems[depth - 1];
//    }
//
//
//    public void clear() {
//        // Go prazni stekot.
//        for (int i = 0; i < depth; i++) elems[i] = null;
//        depth = 0;
//    }
//
//
//    public void push(E x) {
//        // Go dodava x na vrvot na stekot.
//        elems[depth++] = x;
//    }
//
//
//    public E pop() {
//        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//        if (depth == 0)
//            throw new NoSuchElementException();
//        E topmost = elems[--depth];
//        elems[depth] = null;
//        return topmost;
//    }
//}
//
//public class CheckXML {
//
//    static int isValid(String[] rows) {
//        ArrayStack<String> stack = new ArrayStack<>(rows.length);
//        for (int i = 0; i < rows.length; i++) {
//            if (!rows[i].startsWith("["))
//                continue;
//            if (rows[i].startsWith("[/")) {
//                String last = stack.pop().substring(2);
//                if (last.equals(rows[i].substring(3))) {
//                    continue;
//                } else {
//                    return 0;
//                }
//            } else {
//                stack.push(rows[i]);
//            }
//        }
//        return 1;
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s = br.readLine();
//        int n = Integer.parseInt(s);
//        String[] redovi = new String[n];
//
//        for (int i = 0; i < n; i++)
//            redovi[i] = br.readLine();
//
//        int valid = 0;
//
//        valid = isValid(redovi);
//
//        System.out.println(valid);
//
//        br.close();
//    }
//}
