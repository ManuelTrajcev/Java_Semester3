package algoritmi.labs.lab8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
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
        root = new BNode<>(elem);
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

    //            0 10 ROOT
//            2 8 LEFT 1
//            3 7 LEFT 2
//            4 60 RIGHT 2
//            5 5 RIGHT 1
//            6 4 RIGHT 0
//            7 13 RIGHT 6
//            8 2 LEFT 7
//            9 1 RIGHT 7
//            10
    int leftSumR(BNode<E> node) {
        if (node == null) {
            return 0;
        }
        int nodeInfo = (Integer) node.info;
        int rootInfo = (Integer) root.info;

        if (nodeInfo < rootInfo) {
            return nodeInfo + leftSumR(node.left) + leftSumR(node.right);
        } else {
            return leftSumR(node.left) + leftSumR(node.right);
        }
    }

    int rightSumR(BNode<E> node) {
        if (node == null) {
            return 0;
        }
        int nodeInfo = (Integer) node.info;
        int rootInfo = (Integer) root.info;

        if (nodeInfo > rootInfo) {
            return nodeInfo + rightSumR(node.left) + rightSumR(node.right);
        } else {
            return rightSumR(node.left) + rightSumR(node.right);
        }
    }

    public int leftSum() {
        BNode<E> curr = root.left;
        return leftSumR(curr);
    }

    public int rightSum() {
        BNode<E> curr = root.right;
        return rightSumR(curr);
    }

    BNode<E> findR(BNode<E> node, E element) {
        if (node.info.equals(element)) {
            return node;
        }
        if (node.left != null) {
            BNode<E> found = findR(node.left, element);
            if (found != null)
                return found;
        }
        if (node.right != null) {
            BNode<E> found = findR(node.right, element);
            if (found != null)
                return found;
        }
        return null;
    }

    public BNode<E> find(E element) {
        BNode<E> curr = root;
        return findR(curr, element);
    }
}

public class BinaryTreeSum {


    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked")
        BNode<Integer>[] nodes = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int baranaVrednost = Integer.parseInt(br.readLine());

        br.close();

        tree.makeRootNode(tree.find(baranaVrednost));
        System.out.print(tree.leftSum() + " ");
        System.out.print(tree.rightSum());
    }
}
