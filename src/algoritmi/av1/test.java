package algoritmi.av1;

import javax.naming.NameClassPair;
import java.util.Scanner;

public class test {
    public static class Box<T>{
        private T t;
        public void setT(T t) {
            this.t = t;
        }
        public T getT(){
            return t;
        }
    }

    public static class OrderedPair<K,V>{
        private K key;
        private V value;

        public OrderedPair(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }
    }
    public static double Average(int[] arr) {
        double sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum / arr.length;
    }

    public static int odd(int[] arr, char c) {
        int oddCounter = 0;
        int evenCounter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0){
                evenCounter++;
            } else {
                oddCounter++;
            }
        }
        if (c == 'p'){
            return evenCounter;
        }else {
            return oddCounter;
        }

    }

    public static void main(String[] args) {
        int[] arr = {2, -9, 0, 5, 12, -25, 22, 9, 8, 12};
        System.out.println(Average(arr));
        System.out.println(odd(arr,'n'));

        Box<Integer> intBox = new Box<Integer>();
        intBox.setT(5);
        System.out.println(intBox.getT());
        OrderedPair<String, Integer> p1 = new OrderedPair<>("Even", 8);
        OrderedPair<String, Character> p2= new OrderedPair<>("Charr", 'c');
        System.out.println(p1.getValue() + p1.getKey());
        System.out.println(p2.getValue() + p2.getKey());
    }
}
