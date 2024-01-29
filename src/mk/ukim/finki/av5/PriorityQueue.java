package mk.ukim.finki.av5;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T> {
    private List<PriorityQueueElement<T>> items;

    public PriorityQueue() {
        this.items = new ArrayList<>();
    }

    public void addItems(T item, int priority) throws EmptyPriorityQueueException {
        if (items.isEmpty()) throw new EmptyPriorityQueueException();
        PriorityQueueElement<T> element= new PriorityQueueElement<>(item, priority);
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).compareTo(element) < 0) {
                break;
            }
        }
        items.add(i, element);
    }

    public List<PriorityQueueElement<T>> getItems() {
        return items;
    }

    public T remove() {
        if (items.size() == 0) {
            return null;
        }
        return items.remove(items.size() - 1).getItem();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        items.stream().forEach(i -> stringBuilder.append(i.toString()).append("\n"));
        return  stringBuilder.toString();
    }

    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        try {
            priorityQueue.addItems("middle1", 49);
            priorityQueue.addItems("middle2", 50);
            priorityQueue.addItems("middle3", 51);
            priorityQueue.addItems("top", 100);
            priorityQueue.addItems("bottom", 10);
        } catch (EmptyPriorityQueueException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println(priorityQueue);

        String element;
                while ((element = priorityQueue.remove()) != null)
                    System.out.println(element);

        System.out.println(priorityQueue);
    }
}
