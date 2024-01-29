package mk.ukim.finki.av11;

import java.util.*;

class StatsThread extends Thread {
    int start;
    int end;

    IntSummaryStatistics iss = new IntSummaryStatistics();

    public StatsThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            iss.accept(ParallelSearchTest.ARRAY[i]);
        }
    }

    @Override
    public String toString() {
        return "StatsThread{" +
                "start=" + start +
                ", end=" + end +
                ", iss=" + iss +
                '}';
    }
}

public class ParallelSearchTest {
    static int NUMBER_OF_ELEMENTS = 100000000;
    static int[] ARRAY = new int[NUMBER_OF_ELEMENTS];
    static int NUMBER_OF_THREADS = 10;
    static Random RANDOM = new Random();

    public static void main(String[] args) {
        ARRAY = RANDOM.ints(NUMBER_OF_ELEMENTS,
                1,
                11
        ).toArray();

//        System.out.println(Arrays.stream(ARRAY).summaryStatistics());     //SINGLE THREAD
        int subArrayLength = NUMBER_OF_ELEMENTS / NUMBER_OF_THREADS;
        List<StatsThread> threads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            int start = i * subArrayLength;
            int end = (i + 1) * subArrayLength;
            threads.add(new StatsThread(start, end));
//            System.out.println(start+", "+end);
        }

        ARRAY[NUMBER_OF_ELEMENTS / 2] = -5;
        ARRAY[NUMBER_OF_ELEMENTS / 3] = 15;

        for (StatsThread thread : threads) {
            thread.start();
        }

//        for (StatsThread thread : threads) {
//            try {
//                System.out.println(thread.join());
//            } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//        }

        threads.forEach(System.out::println);

        int min = threads.stream().mapToInt(i -> i.iss.getMin()).min().getAsInt();
        int max = threads.stream().mapToInt(i -> i.iss.getMax()).max().getAsInt();

    }
}
