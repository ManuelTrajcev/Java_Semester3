package mk.ukim.finki.genericko;

import java.util.ArrayList;
import java.util.Arrays;

public class PriorityQueue<T> {
    ArrayList<T> queue;
    ArrayList<Integer> priorities;

    public PriorityQueue() {
        this.queue = new ArrayList<>();
        this.priorities = new ArrayList<>();
    }

    public void add(T element, int priority) {
        int i = 0;
        for (i = 0; i < queue.size(); i++) {
            if (priorities.get(i) < priority) {
                break;
            }
        }
        queue.add(i, element);
        priorities.add(i, priority);
    }

    public T remove() {
        if (queue.isEmpty()) {
            return null;
        }
        queue.remove(queue.get(0));
        priorities.remove(priorities.get(0));
        return queue.get(0);
    }

    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<String>();
        pq.add("X", 0);
        pq.add("Y", 1);
        pq.add("Z", 3);
        System.out.println(pq.remove()); // Returns X
        System.out.println(pq.remove()); // Returns Z
        System.out.println(pq.remove()); // Returns Y
    }
}
