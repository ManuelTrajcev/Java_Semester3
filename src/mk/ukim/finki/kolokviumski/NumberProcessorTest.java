package mk.ukim.finki.kolokviumski;


import java.util.*;
import java.util.stream.Collectors;

interface NumberProcessor<T extends Number, R> {
    R compute(ArrayList<T> numbers);
}

class Numbers<T extends Number, R> {
    ArrayList<T> numbers;

    public Numbers(ArrayList<T> numbers) {
        this.numbers = numbers;
    }

    void process(NumberProcessor<T, R> processor) {
        System.out.println(processor.compute(numbers));
    }
}

public class NumberProcessorTest {
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ints.add(-i + 1);
        }
        ArrayList<Double> doubles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ints.add(i + i / 10);
        }

        NumberProcessor<Integer, Integer> firstProcessor = new NumberProcessor<Integer, Integer>() {
            @Override
            public Integer compute(ArrayList<Integer> numbers) {
                return (int) numbers.stream().filter(n -> n < 0).count();
            }
        };
        NumberProcessor<Integer, Integer> firstProcessorLambda = list -> (int) list.stream().filter(n -> n < 0).count();


        NumberProcessor<Integer, String> secondProcessor = new NumberProcessor<Integer, String>() {
            @Override
            public String compute(ArrayList<Integer> numbers) {
                IntSummaryStatistics iss = numbers.stream().mapToInt(i -> i).summaryStatistics();
                return String.format("%d %d %f ", iss.getMax(), iss.getMin(), iss.getAverage());
            }
        };

        NumberProcessor<Double, List<Double>> thirdProcessor = list -> list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(() -> new ArrayList<>()));

        NumberProcessor<Double, Double> fourthProcessor = list -> {
            if (list.size() % 2 == 0) {
                list = list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(() -> new ArrayList<>()));
                return (list.get(list.size() / 2) + list.get(list.size() / 2 + 1)) / 2;
            } else {
                list = (ArrayList<Double>) thirdProcessor.compute(list);
                return list.get(list.size() / 2);
            }
        };
        System.out.println(firstProcessor.compute(ints));
        System.out.println(secondProcessor.compute(ints));
        System.out.println(thirdProcessor.compute(doubles));
        System.out.println(fourthProcessor.compute(doubles));
    }
}
