package mk.ukim.finki.av5;

import mk.ukim.finki.av4.calc.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box<E extends Drawable> {
    private List<E> items;

    public Box() {
        items = new ArrayList<>();
    }

    public void addElement(E item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public E getIemFromBox() {
        Random random = new Random();
        int position = random.nextInt(items.size());
        return items.get(position);
    }

    public void drawBox() throws EmptyBoxException {
        if (isEmpty()) throw new EmptyBoxException();
        for (E item : items) {
            item.draw();
        }
//        items.stream().forEach(i -> i.draw);
//        items.stream().forEach(Drawable::draw);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        items.stream().forEach(i -> stringBuilder.append(i.toString()).append("\n"));
        return stringBuilder.toString();
    }
}
