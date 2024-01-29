package mk.ukim.finki.lab6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

class SuperString {
    LinkedList<String> list;
    LinkedList<String> lastAdded;

    public SuperString() {
        this.list = new LinkedList<>();
        this.lastAdded = new LinkedList<>();
    }


    public void append(String s) {
        list.add(s);
        lastAdded.add(0, s);
    }

    public void insert(String s) {
        list.add(0, s);
        lastAdded.add(0, s);
    }

    public boolean contains(String s) {
        String str = this.toString();
        return str.contains(s);
    }

    public void reverse() {
        if (list.isEmpty()) return;
        Collections.reverse(list);
        LinkedList<String> tmp = new LinkedList<>();
        for (String s : list) {
            StringBuilder str = new StringBuilder();
            tmp.add(str.append(s).reverse().toString());
        }
        list = tmp;
        reverseLastAdded();
    }

    private void reverseLastAdded() {
        LinkedList<String> tmp = new LinkedList<>();
        for (String s : lastAdded) {
            StringBuilder str = new StringBuilder();
            tmp.add(str.append(s).reverse().toString());
        }
        lastAdded = tmp;
    }

    public void removeLast(int i) {
        for (int j = 0; j < i; j++) {
            list.remove(lastAdded.get(0));
            lastAdded.remove(0);
        }
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "";
        } else {
            StringBuilder str = new StringBuilder();
            list.forEach(str::append);
            str.append("\n");
            return str.toString();
        }
    }
}

public class SuperStringTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) {
            SuperString s = new SuperString();
            while (true) {
                int command = jin.nextInt();
                if (command == 0) {//append(String s)
                    s.append(jin.next());
                    continue;
                }
                if (command == 1) {//insert(String s)
                    s.insert(jin.next());
                    continue;
                }
                if (command == 2) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                    continue;
                }
                if (command == 3) {//reverse()
                    s.reverse();
                    continue;
                }
                if (command == 4) {//toString()
                    System.out.print(s);
                    continue;
                }
                if (command == 5) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                    continue;
                }
                if (command == 6) {//end
                    break;
                }
            }
        }
    }

}
