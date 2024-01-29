//package algoritmi.vtorKolokvium;
//
//import java.util.Scanner;
//import java.util.Stack;
//
//
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
//    public void inOrderNonRecursive() {
//        Stack<BNode<E>> s = new Stack<>();
//        BNode<E> p = root;
//
//        while (true) {
//            while (p != null) {
//                s.push(p);
//                p = p.left;
//            }
//
//            if (s.isEmpty()) {
//                break;
//            }
//
//            p = s.peek();
//
//            System.out.println(p.info.toString() + " ");
//
//            s.pop();
//
//            p = p.right;
//        }
//    }
//
//    public int insideNodesRecursive(BNode<E> node) {
//        if (node == null) {
//            return 0;
//        }
//        if ((node.left == null) && (node.right == null)) {
//            return 0;
//        }
//        return insideNodesRecursive(node.left) + insideNodesRecursive(node.right) + 1;
//    }
//
//    public int insideNodes() {
//        return insideNodesRecursive(root);
//    }
//
//    int leavesRecursive(BNode<E> node) {
//        if (node == null) {
//            return 0;
//        }
//        if (node.left == null && node.right == null) {
//            return 1;
//        } else {
//            return leavesRecursive(node.left) + leavesRecursive(node.right);
//        }
//    }
//
//    public int leaves() {
//        return leavesRecursive(root);
//    }
//
//    int depthRecursive(BNode<E> node) {
//        if (node == null) {
//            return 0;
//        }
//        if (node.left == null && node.right == null) {
//            return 0;
//        }
//        return (1 + Math.max(depthRecursive(node.left), depthRecursive(node.right)));
//    }
//
//    public int depth() {
//        return depthRecursive(root);
//    }
//
//    void mirrorRecursive(BNode<E> node) {
//        if (node == null) {
//            return;
//        }
//        mirrorRecursive(node.left);
//        mirrorRecursive(node.right);
//        BNode<E> tmp = node.left;
//        node.left = node.right;
//        node.right = tmp;
//    }
//
//    public void mirror() {
//        mirrorRecursive(root);
//    }
//
//}
//
//public class SumOfLeftChildren {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        BNode<Integer> a, b, c;
//        BTree<Integer> tree = new BTree<>();
//
//        System.out.println(findSum(tree.root));
//    }
//
//    private static Integer findSum(BNode<Integer> root) {
//        if (root.left != null && root.right == null) {
//            return root.left.info + findSum(root.left);
//        } else if (root.left != null) {
//            return findSum(root.left);
//        }
//        return null;
//    }
//}
