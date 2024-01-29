//package algoritmi.prvKolokvium;
//
//import java.util.Scanner;
//
//
//class SLLNode<E> {
//    protected E element;
//    protected SLLNode1<E> succ;
//
//    public SLLNode(E elem, SLLNode1<E> succ) {
//        this.element = elem;
//        this.succ = succ;
//    }
//
//    @Override
//    public String toString() {
//        return element.toString();
//    }
//}
//
//class SLL<E> {
//    private SLLNode1<E> first;
//
//    public SLL() {
//        this.first = null;
//    }
//
//    public void deleteList() {
//        first = null;
//    }
//
//    public int length() {
//        int ret;
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            ret = 1;
//            while (tmp.succ != null) {
//                tmp = tmp.succ;
//                ret++;
//            }
//            return ret;
//        } else
//            return 0;
//
//    }
//
//    @Override
//    public String toString() {
//        String ret = new String();
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            ret += tmp;
//            while (tmp.succ != null) {
//                tmp = tmp.succ;
//                ret += "->" + tmp;
//            }
//        } else
//            ret = "Prazna lista!!!";
//        return ret;
//    }
//
//    public void insertFirst(E o) {
//        SLLNode1<E> ins = new SLLNode1<E>(o, first);
//        first = ins;
//    }
//
//    public void insertAfter(E o, SLLNode1<E> node) {
//        if (node != null) {
//            SLLNode1<E> ins = new SLLNode1<E>(o, node.succ);
//            node.succ = ins;
//        } else {
//            System.out.println("Dadenot jazol e null");
//        }
//    }
//
//    public void insertBefore(E o, SLLNode1<E> before) {
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            if (first == before) {
//                this.insertFirst(o);
//                return;
//            }
//            while (tmp.succ != before)
//                tmp = tmp.succ;
//            if (tmp.succ == before) {
//                SLLNode1<E> ins = new SLLNode1<E>(o, before);
//                tmp.succ = ins;
//            } else {
//                System.out.println("Elementot ne postoi vo listata");
//            }
//        } else {
//            System.out.println("Listata e prazna");
//        }
//    }
//
//    public void insertLast(E o) {
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            while (tmp.succ != null)
//                tmp = tmp.succ;
//            SLLNode1<E> ins = new SLLNode1<E>(o, null);
//            tmp.succ = ins;
//        } else {
//            insertFirst(o);
//        }
//    }
//
//    public E deleteFirst() {
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            first = first.succ;
//            return tmp.element;
//        } else {
//            System.out.println("Listata e prazna");
//            return null;
//        }
//    }
//
//    public E delete(SLLNode1<E> node) {
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            if (first == node) {
//                return this.deleteFirst();
//            }
//            while (tmp.succ != node && tmp.succ.succ != null)
//                tmp = tmp.succ;
//            if (tmp.succ == node) {
//                tmp.succ = tmp.succ.succ;
//                return node.element;
//            } else {
//                System.out.println("Elementot ne postoi vo listata");
//                return null;
//            }
//        } else {
//            System.out.println("Listata e prazna");
//            return null;
//        }
//    }
//
//    public SLLNode1<E> getFirst() {
//        return first;
//    }
//
//    public SLLNode1<E> find(E o) {
//        if (first != null) {
//            SLLNode1<E> tmp = first;
//            while (tmp.element != o && tmp.succ != null)
//                tmp = tmp.succ;
//            if (tmp.element == o) {
//                return tmp;
//            } else {
//                System.out.println("Elementot ne postoi vo listata");
//            }
//        } else {
//            System.out.println("Listata e prazna");
//        }
//        return first;
//    }
//
//    public void mirror() {
//        if (first != null) {
//            //m=nextsucc, p=tmp,q=next
//            SLLNode1<E> tmp = first;
//            SLLNode1<E> newsucc = null;
//            SLLNode1<E> next;
//
//            while (tmp != null) {
//                next = tmp.succ;
//                tmp.succ = newsucc;
//                newsucc = tmp;
//                tmp = next;
//            }
//            first = newsucc;
//        }
//    }
//}
//
//public class DeleteSLL {
//
//    public static void change(SLL1<Integer> list, int br) {
//        int counter = 0;
//        SLLNode1<Integer> curr = list.getFirst();
//        while (curr != null) {
//            if (curr.element == br) {
//                counter++;
//            }
//            curr = curr.succ;
//        }
//
//        if (counter % 2 != 0) {
//            curr = list.getFirst();
//            while (true) {
//                if (curr.element == br) {
//                    list.insertAfter(br, curr);
//                    break;
//                }
//                curr = curr.succ;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Scanner scan = new Scanner(System.in);
//        int n, broj;
//        SLL1<Integer> list1 = new SLL1<Integer>();
//        n = scan.nextInt();
//        for (int i = 0; i < n; i++) {
//            list1.insertLast(scan.nextInt());
//        }
//        int br = scan.nextInt();
//        change(list1, br);
//        System.out.println(list1);
//
//
//    }
//}
