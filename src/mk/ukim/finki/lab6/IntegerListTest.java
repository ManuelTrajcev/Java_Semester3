package mk.ukim.finki.lab6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

class IntegerList {
    ArrayList<Integer> list;

    public IntegerList() {
        list = new ArrayList<>();
    }

    public IntegerList(Integer[] array) {
        list = new ArrayList<>();
        Arrays.stream(array).forEach(i -> list.add(i));
    }

    public void add(int el, int idx) {
        if (idx > size()) {
            for (int i = size(); i < idx; i++) {
                list.add(0);
            }
        }
        list.add(idx, el);
    }

    public int remove(int idx) {
        int el = list.get(idx);
        list.remove(idx);
        return el;
    }

    public void set(int el, int idx) {
        list.add(idx, el);
    }

    public int get(int idx) {
        return list.get(idx);
    }

    public int size() {
        return list.size();
    }

    public int count(int el) {
        return (int) list.stream().filter(i -> i.equals(el)).count();
    }

    public void removeDuplicates() {
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (newList.contains(list.get(i))) {
                newList.remove(list.get(i));
                newList.add(list.get(i));
            } else {
                newList.add(list.get(i));
            }
        }
        list = newList;
    }

    public IntegerList addValue(int value) {
        Integer[] arr = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i) + value;
        }
        return new IntegerList(arr);
    }

    public void shiftRight(int idx, int k) {
        int tmp = list.get(idx);
        if (list.size() <= idx + k) {
            while (list.size() <= idx + k) {
                k = (k + idx) - list.size();
            }
        } else {
            k += idx + 1;
        }
        list.add(k, tmp);
        if (k > idx) {
            remove(idx);
        } else {
            list.remove(idx + 1);
        }
    }

    public void shiftLeft(int idx, int k) {
        int tmp = list.get(idx);
        int i = idx - k;
        if (i < 0) {
            while (idx - k < 0) {
                if (k - (idx + 1) < list.size()) {
                    k -= (idx + 1);
                    i = list.size() - k;
                    break;
                } else {
                    k -= list.size();
                }
            }
        }
        list.add(i, tmp);
        if (i > idx) {
            remove(idx);
        } else {
            list.remove(idx + 1);
        }
    }

    public int sumLast(int k) {
        if (k > list.size()) k = k % list.size();
        return list.subList(list.size() - k, list.size()).stream().mapToInt(i -> i).sum();
    }

    public int sumFirst(int k) {
        if (k > list.size()) k = list.size();
        return list.subList(0, k).stream().mapToInt(i -> i).sum();
    }
}

public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test standard methods
            int subtest = jin.nextInt();
            if (subtest == 0) {
                IntegerList list = new IntegerList();
                while (true) {
                    int num = jin.nextInt();
                    if (num == 0) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if (num == 1) {
                        list.remove(jin.nextInt());
                    }
                    if (num == 2) {
                        print(list);
                    }
                    if (num == 3) {
                        break;
                    }
                }
            }
            if (subtest == 1) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for (int i = 0; i < n; ++i) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if (k == 1) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for (int i = 0; i < n; ++i) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while (true) {
                int num = jin.nextInt();
                if (num == 0) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if (num == 1) {
                    list.removeDuplicates();
                }
                if (num == 2) {
                    print(list.addValue(jin.nextInt()));
                }
                if (num == 3) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if (num == 4) {
                    print(list);
                }
                if (num == 5) {
                    break;
                }
            }
        }
        if (k == 2) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for (int i = 0; i < n; ++i) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while (true) {
                int num = jin.nextInt();
                if (num == 0) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if (num == 1) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if (num == 2) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if (num == 3) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if (num == 4) {
                    print(list);
                }
                if (num == 5) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if (il.size() == 0) System.out.print("EMPTY");
        for (int i = 0; i < il.size(); ++i) {
            if (i > 0) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}