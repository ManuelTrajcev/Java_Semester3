package algoritmi.av8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree<E extends Comparable<E>> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public void inOrderNonRecursive() {
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
//
        Stack<BNode<E>> stack = new Stack<>();
        BNode<E> curr = root;

        while (true) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }


            if (stack.isEmpty()) {
                break;
            }

            curr = stack.pop();
            System.out.println(curr.info);

            curr = curr.right;
        }
    }

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

    private int insideNodesRecursive(BNode<E> node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) return 0;

        return insideNodesRecursive(node.left) + insideNodesRecursive(node.right) + 1;
    }

    public int insideNodes() {
        return insideNodesRecursive(root);
    }

    int leavesRecursive(BNode<E> node) {
        if (node == null) return 0;

        if (node.left == null && node.right == null) {
            return 1;
        } else {
            return leavesRecursive(node.left) + leavesRecursive(node.right);
        }
    }

    public int leaves() {
        return leavesRecursive(root);
    }

    int depthRecursive(BNode<E> node) {
//        if (node == null) {
//            return 0;
//        }
//        if (node.left == null && node.right == null) {
//            return 0;
//        }
//        return (1 + Math.max(depthRecursive(node.left), depthRecursive(node.right)));
        if (node == null) return 0;

        if (node.left == null && node.right == null) return 0;

        return (1 + Math.max(depthRecursive(node.left), depthRecursive(node.right)));
    }

    public int depth() {
        return depthRecursive(root);
    }

    void mirrorRecursive(BNode<E> node) {
        if (node == null) return;

        mirrorRecursive(node.left);
        mirrorRecursive(node.right);

        BNode<E> tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public void mirror() {
        mirrorRecursive(root);
    }

}

public class BinaryTreeTest {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>();

        // Given inorder sequence: 2, 3, 4, 6, 7, 9, 13, 15, 17, 18, 20

        // Create the root with the middle element (7)
        BNode<Integer> root = new BNode<>(7);
        tree.makeRootNode(root);

        // Construct the left subtree
        BNode<Integer> leftSubtree = new BNode<>(4);
        tree.addChildNode(root, BNode.LEFT, leftSubtree);

        BNode<Integer> leftLeft = new BNode<>(3);
        tree.addChildNode(leftSubtree, BNode.LEFT, leftLeft);

        BNode<Integer> leftRight = new BNode<>(6);
        tree.addChildNode(leftSubtree, BNode.RIGHT, leftRight);

        // Construct the right subtree
        BNode<Integer> rightSubtree = new BNode<>(15);
        tree.addChildNode(root, BNode.RIGHT, rightSubtree);

        BNode<Integer> rightLeft = new BNode<>(13);
        tree.addChildNode(rightSubtree, BNode.LEFT, rightLeft);

        BNode<Integer> rightRight = new BNode<>(18);
        tree.addChildNode(rightSubtree, BNode.RIGHT, rightRight);

        // Add the remaining nodes
        tree.addChildNode(rightRight, BNode.RIGHT, new BNode<>(17));
        tree.addChildNode(rightRight, BNode.RIGHT, new BNode<>(20));

//        tree.inOrderNonRecursive();

        System.out.println(tree.insideNodes());
//        System.out.println(tree.leaves());
//        System.out.println(tree.depth());
//
//        tree.inOrderNonRecursive();
//        System.out.println();
//        tree.mirror();
//        tree.inOrderNonRecursive();
    }
}
