//package algoritmi.labs.lab8;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//import java.util.NoSuchElementException;
//
//class BNode<E> {
//
//    public E info;
//    public BNode<E> left;
//    public BNode<E> right;
//
//    static int LEFT = 1;
//    static int RIGHT = 2;
//
//    public BNode(E info) {
//        this.info = info;
//        left = null;
//        right = null;
//    }
//
//    public BNode() {
//        this.info = null;
//        left = null;
//        right = null;
//    }
//
//    public BNode(E info, BNode<E> left, BNode<E> right) {
//        this.info = info;
//        this.left = left;
//        this.right = right;
//    }
//
//}
//
//class BTree<E> {
//
//    public BNode<E> root;
//
//    public BTree() {
//        root = null;
//    }
//
//    public BTree(E info) {
//        root = new BNode<E>(info);
//    }
//
//    public void makeRoot(E elem) {
//        root = new BNode<E>(elem);
//    }
//
//    public void makeRootNode(BNode<E> node) {
//        root = node;
//    }
//
//    public BNode<E> addChild(BNode<E> node, int where, E elem) {
//
//        BNode<E> tmp = new BNode<E>(elem);
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // veke postoi element
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // veke postoi element
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // veke postoi element
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // veke postoi element
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//    public void PreorderNonRecursive() {
////        ArrayStack<BNode<E>> stack = new ArrayStack<>(100);
////        BNode<E> curr = root;
////        while (true) {
////            System.out.print(curr.info + " ");
////            if (curr.right != null) {
////                stack.push(curr.right);
////            }
////            if (curr.left != null) {
////                curr = curr.left;
////            } else {
////                if (!stack.isEmpty())
////                    curr = stack.pop();
////                else break;
////            }
////        }
//        ArrayStack<BNode<E>> stack = new ArrayStack<>(100);
//        BNode<E> curr = root;
//
//        while (true) {
//            System.out.print(curr.info + " ");
//            if (curr.right != null) {
//                stack.push(curr.right);
//            }
//            if (curr.left != null) {
//                curr = curr.left;
//            } else {
//                if (!stack.isEmpty()) {
//                    curr = stack.pop();
//                } else {
//                    break;
//                }
//            }
//        }
//    }
//
//    public void PostorderNonRecursive() {
//        ArrayStack<BNode<E>> stack = new ArrayStack<>(100);
//        BNode<E> curr = root;
//        stack.push(root);
//        while (true) {
//            if (stack.isEmpty()) return;
//            if (curr.left != null) {
//                stack.push(curr.left);
//            }
//            if (curr.right != null) {
//                stack.push(curr.right);
//                curr = curr.right;
//            } else {
////                if (curr.left != null) {
////                    curr = curr.left;
////                } else {
////                    System.out.print(curr.info + " ");
////                    curr = stack.pop();
////                }
//                System.out.print(curr.info + " ");
//            }
//        }
//    }
//}
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
//class ArrayStack<E> implements Stack<E> {
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
//public class PreorderNonRecursive {
//
//    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        sc.nextLine();
//        BTree<String> tree = new BTree<>();
//        @SuppressWarnings("unchecked")
//        BNode<String>[] nodes = new BNode[n];
//
//        for (int i = 0; i < n; i++) {
//            nodes[i] = new BNode<String>();
//        }
//
//        for (int i = 0; i < n; i++) {
//            String[] parts = sc.nextLine().split("\\s+");
//            int index = Integer.parseInt(parts[0]);
//
//            String info = parts[1];
//            String action = parts[2];
//            nodes[index].info = info;
//            if (action.equals("ROOT")) {
//                tree.makeRootNode(nodes[index]);
//            } else if (action.equals("LEFT")) {
//                int where = Integer.parseInt(parts[3]);
//                tree.addChildNode(nodes[where], BNode.LEFT, nodes[index]);
//            } else if (action.equals("RIGHT")) {
//                int where = Integer.parseInt(parts[3]);
//                tree.addChildNode(nodes[where], BNode.RIGHT, nodes[index]);
//            }
//        }
//
////        tree.PreorderNonRecursive();
//        tree.PostorderNonRecursive();
//
////        int i, j, k;
////        int index;
////        String action;
////
////        String line;
////
////        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////        StringTokenizer st;
////
////        int N = Integer.parseInt(br.readLine());
////
////        @SuppressWarnings("unchecked")
////        BNode<String> nodes[] = new BNode[N];
////        BTree<String> tree = new BTree<String>();
////
////        for (i = 0; i < N; i++)
////            nodes[i] = new BNode<String>();
////
////        for (i = 0; i < N; i++) {
////            line = br.readLine();
////            st = new StringTokenizer(line);
////            index = Integer.parseInt(st.nextToken());
////            nodes[index].info = st.nextToken();
////            action = st.nextToken();
////            if (action.equals("LEFT")) {
////                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
////            } else if (action.equals("RIGHT")) {
////                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
////            } else {
////                // this node is the root
////                tree.makeRootNode(nodes[index]);
////            }
////        }
////
////        br.close();
////
////        tree.PreorderNonRecursive();
//
//    }
//}