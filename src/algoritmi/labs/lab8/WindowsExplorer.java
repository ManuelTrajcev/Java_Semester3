package algoritmi.labs.lab8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    Node<E> findR(SLLNode<E> node, E element) {
        SLLNode<E> child = node.firstChild;
        SLLTreeIterator<E> it = new SLLTreeIterator<>(child);
        while (it.hasNext()) {
            if (it.current.element.equals(element)) {
                return it.current;
            } else if (it.current.firstChild != null) {
                Node<E> found = findR(it.current, element);
                if (found != null) {
                    return found;
                }
            }
            it.next();
        }
        return null;
    }

    public Node<E> find(E element, SLLNode<E> node) {
//        if (node.element.equals(element)) {
//            return node;
//        }
        SLLNode<E> curr = node.firstChild;
        SLLTreeIterator<E> it = new SLLTreeIterator<>(curr);
        while (it.hasNext()) {
            if (it.current.element.equals(element)) {
                return it.current;
            }
//            if (it.current.firstChild != null) {
//                Node<E> found = findR(it.current, element);
//                if (found != null) {
//                    return found;
//                }
//            }
            it.next();
        }
        return null;
    }

    void printPath(Stack<E> stack) {
        String result = stack.pop().toString();
        while (!stack.isEmpty()) {
            result = result + "\\" + stack.pop();
        }
        result += "\\";
        System.out.println(result);
    }

    void findPath(SLLNode<E> curr) {
        Stack<E> stack = new Stack<>();
        while (!curr.equals(root)) {
            stack.push(curr.element);
            curr = curr.parent;
        }
        stack.push(curr.element);
        printPath(stack);
    }

    public void findPathR(E element) {
        Stack<E> stack = new Stack<>();
        stack.push(root.element);
        SLLNode<E> curr = root;
        if (root.element.equals(element)) {
            System.out.println(element + "\\");
            return;
        }
        while (curr.firstChild != null) {
            curr = curr.firstChild;
            stack.push(curr.element);
            if (curr.element.equals(element)) {
                printPath(stack);
                return;
            }
        }

    }

    void sortRecursive(SLLNode<E> node) {
        boolean swaps = true;
        while (swaps) {
            swaps = false;
            SLLTreeIterator<E> it = new SLLTreeIterator<>(node.firstChild);
            while (it.hasNext()) {
                if (it.current.firstChild != null) {
                    sortRecursive(it.current);
                }
                Node<E> curr = it.current;
                SLLTreeIterator<E> tmpIt = new SLLTreeIterator<>(it.current);
                tmpIt.next();
                Node<E> next = tmpIt.current;
                if (next == null) return;
                if (compareNodes(curr, next) > 0) {
                    swaps = true;
                    swapNodes(it.current, tmpIt.current);
                } else {
                    it.next();
                }
            }
        }
    }

    void swapNodes(Node<E> node1, Node<E> node2) {
        E tmpElement = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(tmpElement);
        SLLNode<E> tmpChild = ((SLLNode<E>) node1).firstChild;
        SLLTreeIterator<E> it = new SLLTreeIterator<>(((SLLNode<E>) node1).firstChild);
        while (it.hasNext()) {
            it.current.parent = (SLLNode<E>) node2;
            it.next();
        }
        SLLTreeIterator<E> it2 = new SLLTreeIterator<>(((SLLNode<E>) node2).firstChild);
        while (it2.hasNext()) {
            it2.current.parent = (SLLNode<E>) node1;
            it2.next();
        }

        ((SLLNode<E>) node1).firstChild = ((SLLNode<E>) node2).firstChild;
        ((SLLNode<E>) node2).firstChild = tmpChild;
    }

    private int compareNodes(Node<E> curr, Node<E> next) {
        String first = curr.getElement().toString();
        String second = next.getElement().toString();
        return first.compareTo(second);
    }

    public void sort() {
        for (int i = 0; i < 100; i++) {
            sortRecursive(root);
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i = 0; i < level; i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>) node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level + 1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (i = 0; i < N; i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");

        Node<String> curr = tree.root;

        for (int l = 0; l < commands.length; l++) {
            String[] parts = commands[l].split("\\s+");
            switch (parts[0]) {
                case "CREATE":
                    tree.addChild(curr, parts[1]);
//                    tree.sort();
                    break;
                case "OPEN":
                    curr = tree.find(parts[1], (SLLNode<String>) curr);
                    break;
                case "DELETE":
                    Node<String> toDelete = tree.find(parts[1], (SLLNode<String>) curr);
//                    toDelete = null;
                    tree.remove(toDelete);
                    break;
                case "BACK":
                    curr = tree.parent(curr);
                    break;
                case "PATH":
                    tree.findPath((SLLNode<String>) curr);
                    break;
                case "PRINT":
                    SLLNode<String> test = ((SLLNode<String>) curr).parent;
                    if (test == null || test.parent == null) {
                        String tmp = curr.getElement();
                        curr = ((SLLNode<String>) curr).parent;
                        tree.sort();
                        tree.printTree();
                        if (curr == null) {
                            curr = tree.root;
                        } else
                            curr = tree.find(tmp, (SLLNode<String>) curr);
                        break;
                    } else {
                        String parent = ((SLLNode<String>) curr).parent.getElement();
                        String tmp = curr.getElement();
                        curr = ((SLLNode<String>) curr).parent.parent;
                        tree.sort();
                        tree.printTree();
                        if (curr == null) {
                            curr = tree.root;
                        } else {
                            curr = tree.find(parent, (SLLNode<String>) curr);
                            curr = tree.find(tmp, (SLLNode<String>) curr);
                        }


                        break;
                    }
            }
        }


    }

}
