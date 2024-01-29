 package mk.ukim.finki.lab5;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.LinkedList;

class ResizableArray<T> {
    protected T[] array;
    static int INITIAL_CAPACITY = 1000;

    @SuppressWarnings("unchecked")
    public ResizableArray() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ResizableArray(T[] array) {
        this.array = array;
    }

    public static <T> void copyAll(ResizableArray<T> dest, ResizableArray<T> src) {
        int countA = src.count();
        int countB = dest.count();
        int total = countA + countB;
        @SuppressWarnings("unchecked")
        ResizableArray<T> tmp = new ResizableArray<>();
        Arrays.stream(dest.array).forEach(i -> tmp.addElement(i));
        Arrays.stream(src.array).forEach(i -> tmp.addElement(i));
        dest.array = tmp.array;
    }

    public int count() {
        int c = 0;
        for (T t : array) {
            if (t != null) {
                c++;
            }
        }
        return c;
    }


    public T elementAt(int idx) {
        if (array.length < idx) throw new ArrayIndexOutOfBoundsException();
        return array[idx];
    }

    public void addElement(T element) {
        if (array.length > count()) {
            array[count()] = element;
        } else {
            @SuppressWarnings("unchecked")
            T[] copy = (T[]) new Object[(2 * INITIAL_CAPACITY)];
            for (int i = 0; i < array.length; i++) {
                copy[i] = array[i];
            }
            copy[count()] = element;
            array = copy;
        }
    }

    public boolean contains(T element) {
        return Arrays.stream(array).anyMatch(i -> i != null && i.equals(element));
    }

    public boolean removeElement(T element) {
        if (!contains(element)) {
            return false;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null && array[i].equals(element)) {
                    array[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (T t : array) {
            if (t != null) {
                return false;
            }
        }
        return true;
    }

    public Object[] toArray() {
        return Arrays.stream(array).toArray();
    }
}

class IntegerArray extends ResizableArray<Integer> {
//    private int[] array;


    public IntegerArray() {
        array = new Integer[INITIAL_CAPACITY];
    }

    public double sum() {
        return Arrays.stream(array).mapToInt(i -> i).sum();
    }

    public double mean() {
        return sum() / count();
    }

    public int countNonZero() {
        return Arrays.stream(array).filter(i -> i != 0).mapToInt(i -> i).sum();
    }

    public IntegerArray distinct() {
        IntegerArray clone = new IntegerArray();
        Arrays.stream(array).filter(i -> !clone.contains(i)).forEach(clone::addElement);
        return clone;
    }

    public IntegerArray increment(int offset) {
        IntegerArray clone = new IntegerArray();
        Arrays.stream(array).forEach(i -> clone.addElement(i + offset));
        return clone;
    }
}

public class ResizableArrayTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if (test == 0) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while (jin.hasNextInt()) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if (test == 1) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for (int i = 0; i < 4; ++i) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if (test == 2) { //test IntegerArray
            IntegerArray a = new IntegerArray();

            System.out.println(a.isEmpty());
            while (jin.hasNextInt()) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if (a.sum() > 100)
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if (test == 3) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for (int w = 0; w < 500; ++w) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k = 2000;
                int t = 1000;
                for (int i = 0; i < k; ++i) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for (int i = 0; i < t; ++i) {
                    a.removeElement(k - i - 1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }

}
