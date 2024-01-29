package algoritmi.labs.lab9;

import java.util.Scanner;
import java.util.Stack;

class BNode {
    int data;
    BNode left, right;

    public BNode(int item) {
        data = item;
        left = right = null;
    }
}

class BST {
    BNode root;

    public BST() {
        root = null;
    }

    // Insert a new node with the given key
    private BNode insert(BNode root, int key) {
        if (root == null) {
            return new BNode(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Public method to insert a key into the BST
    public void insert(int key) {
        root = insert(root, key);
    }

    // Inorder traversal of the BST
    private void inorder(BNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Public method to perform inorder traversal of the BST
    public void inorder() {
        System.out.print("Inorder Traversal: ");
        inorder(root);
        System.out.println();
    }
}

public class Championship {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BST tree = new BST();
        for (int i = 0; i < n; i++) {
            tree.insert(sc.nextInt());
        }


        int m = sc.nextInt();
        BNode curr = tree.root;
        int position = 1;
        for (int i = 0; i < n; i++) {
            if (curr.data == m) {
                System.out.println(position);
                break;
            }

            position = i * 2;

            if (m > curr.data) {
                position = position + 1;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
    }

}
