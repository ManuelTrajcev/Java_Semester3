//package algoritmi.vtorKolokvium;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//class BNode<E extends Comparable<E>> {
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
//    @Override
//    public String toString() {
//        return "" + info;
//    }
//
//
//}
//
//class BTree<E extends Comparable<E>> {
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
//        root = new BNode(elem);
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
//    public boolean validityCheck() {
//        return validityCheckRecursive(root);
//    }
//
//    private boolean validityCheckRecursive(BNode<E> node) {
//        if (node == null) {
//            return true;
//        }
//        if (node.left == null && node.right == null) {
//            return true;
//        }
//
//        if (node.left == null) {
//            if (node.right.info.compareTo(node.info) <= 0) {
//                return validityCheckRecursive(node.right);
//            }
//        }
//
//        if (node.right == null) {
//            if (node.left.info.compareTo(node.info) <= 0) {
//                return validityCheckRecursive(node.left);
//            }
//
//        }
//
//        if (node.left.info.compareTo(node.info) <= 0 && node.right.info.compareTo(node.info) <= 0) {
//            return (validityCheckRecursive(node.left) && validityCheckRecursive(node.right));
//        }
//
//        return false;
//    }
//}
//
//
//public class ValidityCheck {
//
//    public static void main(String[] args) throws IOException {
//        int j, k;
//        int index;
//        String action;
//        String line;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int N = Integer.parseInt(br.readLine());
//
//        @SuppressWarnings("unchecked")
//        BNode<Integer>[] nodes = new BNode[N];
//        BTree<Integer> tree = new BTree<>();
//
//        for (int i = 0; i < N; i++) {
//            line = br.readLine();
//            st = new StringTokenizer(line);
//            index = Integer.parseInt(st.nextToken());
//            nodes[index].info = Integer.parseInt(st.nextToken());
//            action = st.nextToken();
//            if (action.equals("LEFT")) {
//                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
//            } else if (action.equals("RIGHT")) {
//                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
//            } else {
//                tree.makeRootNode(nodes[index]);
//            }
//        }
//
//        System.out.println(tree.validityCheck());
//        br.close();
//    }
//}
