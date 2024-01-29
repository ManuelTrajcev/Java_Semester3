package mk.ukim.finki.av9;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LuckySuitor {
    private final List<Integer> positions;

    public LuckySuitor(int n) {
        positions = IntStream.rangeClosed(3, n)
                .boxed()
                .collect(Collectors.toList());
    }

    public int getWinner() {
        ListIterator<Integer> lt = positions.listIterator();
        boolean right = true;
        while (positions.size() != 1) {
            int last = -1;
            for (int i = 0; i < 3; i++) {
                if (lt.hasNext() && right) {
                    last = lt.next();
                    if (!lt.hasNext()) {
                        right = false;
                        lt.previous();
                    }
                } else {
                    if (lt.hasPrevious()) {
                        last = lt.previous();
                        if (!lt.hasPrevious()) {
                            right = true;
                            lt.next();
                        }
                    }
                }
                lt.next();
            }
            lt.remove();
        }
        return positions.get(0);
    }
}

public class Suitors {

    public static void main(String[] args) {
        LuckySuitor luckySuitor = new LuckySuitor(9);
        System.out.println("Winner: " + luckySuitor.getWinner());
    }
}
