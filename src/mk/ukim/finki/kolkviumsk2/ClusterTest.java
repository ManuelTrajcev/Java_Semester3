package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * January 2016 Exam problem 2
 */
class Point2D {
    long id;
    float x;
    float y;

    public Point2D(long id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public float distanceTo(Point2D other) {
        return (float) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}

class Cluster<T extends Point2D> {
    Map<Long, T> items;

    public Cluster() {
        this.items = new HashMap<>();
    }

    public void addItem(T point2D) {
        items.put(point2D.id, point2D);
    }

    public void near(long id, int top) {
        Point2D p = items.get(id);
        TreeMap<Float, Point2D> closestTo = new TreeMap<>();
        items.entrySet()
                .stream()
                .forEach(i -> closestTo.put(i.getValue().distanceTo(p), i.getValue()));
        Iterator<Map.Entry<Float, Point2D>> it = closestTo.entrySet().iterator();
        it.next();
        for (int i = 0; i < top; i++) {
            Map.Entry<Float, Point2D> curr = it.next();
            System.out.println(String.format("%d. %d -> %.3f", i + 1, curr.getValue().id, curr.getKey()));
        }
    }
}

public class ClusterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cluster<Point2D> cluster = new Cluster<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            long id = Long.parseLong(parts[0]);
            float x = Float.parseFloat(parts[1]);
            float y = Float.parseFloat(parts[2]);
            cluster.addItem(new Point2D(id, x, y));
        }
        int id = scanner.nextInt();
        int top = scanner.nextInt();
        cluster.near(id, top);
        scanner.close();
    }
}

// your code here