package algoritmi.av2;

public class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("ERROR");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (before != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                insertFirst(o);
            } else {
                while (tmp.succ != before && tmp.succ != null) {
                    tmp = tmp.succ;
                }
                if (tmp.succ == before) {
                    tmp.succ = new SLLNode<E>(o, before);
                }
            }
        } else {
            System.out.println("ERROR");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null) {
                tmp = tmp.succ;
            }
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("ERROR");
            return null;
        }
    }

    public E delete(SLLNode<E> o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == o) {
                deleteFirst();
            } else {
                while (tmp.succ != o && tmp.succ.succ != null) {
                    tmp = tmp.succ;
                    if (tmp.succ == o) {
                        tmp.succ = tmp.succ.succ;
                        return o.element;
                    }
                }
            }
            return tmp.element;
        } else {
            System.out.println("ERROR");
            return null;
        }
    }

    public SLLNode<E> find(int node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(node) && tmp.succ != null) {
                tmp = tmp.succ;
            }
            if (tmp.element.equals(node)) {
                return tmp;
            } else {
                System.out.println("Element not found");
                return null;
            }

        } else {
            System.out.println("ERROR");
            return null;
        }
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null) {
                tmp = tmp.succ;
            }
            tmp.succ = in.getFirst();
        } else {
            System.out.println("ERROR");
        }
    }

    public void deleteList() {
        first = null;
    }

    public void mirror() {
//        SLLNode<E> prev = null;
//        SLLNode<E> current = first;
//
//        while (current != null) {
//            SLLNode<E> next = current.succ;
//            current.succ = prev;
//            prev = current;
//            current = next;
//        }
//
//        first = prev;
        if (first != null) {
            SLLNode<E> tmp = first;
            SLLNode<E> newSucc = null;
            SLLNode<E> next;
            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newSucc;
                newSucc = tmp;
                tmp = next;
            }
            first = newSucc;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        SLLNode<E> tmp = first;

        if (tmp != null) {
            str.append(tmp.element);
            tmp = tmp.succ;
        }

        while (tmp != null) {
            str.append(",").append(tmp.element);
            tmp = tmp.succ;
        }

        str.append("}");
        return str.toString();
    }


    public static void main(String[] args) {
        SLL<Integer> lista = new SLL<Integer>();
        lista.insertLast(5);
        System.out.print("Listata po vmetnuvanje na 5 kako posleden element: ");
        System.out.println(lista.toString());
        lista.insertFirst(3);
        System.out.print("Listata po vmetnuvanje na 3 kako prv element: ");
        System.out.println(lista.toString());
        lista.insertLast(1);
        System.out.print("Listata po vmetnuvanje na 1 kako posleden element: ");
        System.out.println(lista.toString());
        lista.insertLast(1);
        System.out.print("Listata po vmetnuvanje na 1 kako posleden element: ");
        System.out.println(lista.toString());
        lista.deleteFirst();
        System.out.print("Listata po brishenje na prviot element: ");
        System.out.println(lista.toString());
        SLLNode<Integer> pom = lista.find(5);
        lista.insertBefore(2, pom);
        System.out.print("Listata po vmetnuvanje na elementot 2 pred elementot 5: ");
        System.out.println(lista.toString());
        pom = lista.find(1);
        lista.insertAfter(3, pom);
        System.out.print("Listata po vmetnuvanje na elementot 3 posle elementot 1: ");
        System.out.println(lista.toString());

        System.out.println("Momentalna dolzina na listata:" + lista.size());
        System.out.print("Listata po prevrtuvanje: ");
        lista.mirror();
        System.out.println(lista.toString());
        pom = lista.find(2);
        lista.delete(pom);
        System.out.print("Listata po brishenje na elementot 2: ");
        System.out.println(lista.toString());
        System.out.println("Momentalna dolzina na listata: " + lista.size());
        lista.deleteList();
        System.out.print("Pecatenje na listata po nejzino brishenje: ");
        System.out.println(lista.toString());
        System.out.println("Momentalna dolzina na listata: " + lista.size());
    }
}


