package algoritmi.av2;

import java.util.Arrays;

public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void insertLast(E o) {
        if (size + 1 > data.length) {
            this.resize();
        }
        data[size++] = o;
    }

    public void insert(int position, E o) {
        if (position >= 0 && position <= size) {
            if (size + 1 > data.length) {
                this.resize();
            }
            if (position == size) {
                insertLast(o);
            } else {
                for (int i = size; i > position; i--) {
                    data[i] = data[i - 1];
                }
                data[position] = o;
                size++;
            }
        } else {
            System.out.println("Invalid position");
        }
    }

    public void set(int position, E o) {
        if (position >= 0 && position <= size) {
            data[position] = o;
        } else {
            System.out.println("Invalid position");
        }
    }

    public E get(int position) {
        if (position >= 0 && position <= size) {
            return data[position];
        } else {
            System.out.println("Invalid position");
            return null;
        }
    }

    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public void delete(int position) {
        if (position >= 0 && position <= size) {
            E[] newData = (E[]) new Object[size - 1];
            for (int i = 0; i < position; i++) {
                newData[i] = data[i];
            }
            for (int i = position; i < size - 1; i++) {
                newData[i] = data[i + 1];
            }
            size--;
            data = newData;
        } else {
            System.out.println("Invalid position");
        }
    }

    public void resize() {
        E[] newData = (E[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        String str = new String();
        str += "{" + data[0];
        for (int i = 1; i < size; i++) {
            str += "," + data[i];
        }
        str += "}";
        return str;
    }

    public static void main(String[] args) {
        Array<Integer> niza = new Array<Integer>(4);
        niza.insertLast(4);
        System.out.print("Nizata po vmetnuvanje na 4 kako posleden element: ");
        System.out.println(niza.toString());
        niza.insertLast(7);
        niza.insertLast(13);
        System.out.print("Nizata po dodavanje na 7 i 13 kako elementi: ");
        System.out.println(niza.toString());
        niza.insert(1, 3);
        System.out.print("Nizata po dodavanje na 3 kako element na pozicija 1: ");
        System.out.println(niza.toString());
        niza.set(2, 6);
        System.out.print("Nizata po menuvanje na vrednosta na elementot na pozicija 2 vo 6: ");
        System.out.println(niza.toString());
        niza.delete(0);
        System.out.print("Nizata po brishenje na elementot na pozicija 0 (prviot element): ");
        System.out.println(niza.toString());
        System.out.print("Na pozicija 2 vo nizata sega se naogja: ");
        System.out.println(niza.get(2));
        System.out.print("Brojot 3 sega se naogja vo nizata na pozicija: ");
        System.out.println(niza.find(3));
        System.out.print("Sega na krajot goleminata na nizata e: ");
        System.out.println(niza.getSize());
    }
}
