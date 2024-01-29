package algoritmi.prvKolokvium;

import java.util.*;

public class BoxStacking {

    public static class Box {
        int length;
        int width;
        int height;

        public Box(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }

        public int getLength() {
            return length;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "length=" + length +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    public static int getTallesStack(ArrayList<Box> boxes) {
        int max = 1;
        int[] heights = new int[boxes.size()];

        for (int i = 0; i < boxes.size(); i++) {
            heights[i] = boxes.get(i).getHeight();
            for (int j = 0; j < i; j++) {
                if (boxes.get(j).getWidth() < boxes.get(i).getWidth() && heights[j] + boxes.get(j).getHeight() > heights[i]) {
                    heights[i] += boxes.get(j).getHeight();
                }
            }
            if (max < heights[i]) {
                max = heights[i];
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Box> boxes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boxes.add(new Box(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        Collections.sort(boxes, Comparator.comparingInt(Box::getLength));

//        boxes.stream().forEach(box -> System.out.println(box.toString()));

        System.out.println(getTallesStack(boxes));

    }
}
