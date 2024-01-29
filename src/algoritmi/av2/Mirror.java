package algoritmi.av2;

public class Mirror {


    public static void main(String[] args) {
        DLL<Integer> list = new DLL<Integer>();
        list.insertLast(2);
        list.insertLast(4);
        list.insertLast(5);
        list.insertLast(1);
        list.insertLast(9);
        System.out.println(list);
        list.mirror();
        System.out.println(list);
    }
}
