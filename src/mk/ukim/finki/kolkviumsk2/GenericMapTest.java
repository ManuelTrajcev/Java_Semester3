package mk.ukim.finki.kolkviumsk2;

import java.util.*;

class MapOps<T> {
    @SuppressWarnings("unchecked")
    public static <T> Map<T, T> merge(Map<T, T> leftMap, Map<T, T> rightMap, MergeStrategy strategy) {
        Map<T, T> mergedMap = new TreeMap<>();
        leftMap.entrySet()
                .forEach(i -> {
                    if (rightMap.containsKey(i.getKey())) {
                        T a = i.getValue();
                        T b = rightMap.get(i.getKey());
                        mergedMap.put(i.getKey(), (T) strategy.strategy(a, b));
                    } else {
                        mergedMap.put(i.getKey(), i.getValue());
                    }
                });
        rightMap.entrySet()
                .stream()
                .filter(i -> !leftMap.containsKey(i.getKey()))
                .forEach(m -> mergedMap.put(m.getKey(), m.getValue()));
        return mergedMap;
    }
}

interface MergeStrategy<T> {
    T strategy(T a, T b);
}

class SumOfIntegersStrategy implements MergeStrategy<Integer> {

    @Override
    public Integer strategy(Integer a, Integer b) {
        return a + b;
    }
}

class MaxOfIntegersStrategy implements MergeStrategy<Integer> {

    @Override
    public Integer strategy(Integer a, Integer b) {
        return Math.max(a, b);
    }
}

class ConcatStringsStrategy implements MergeStrategy<String> {

    @Override
    public String strategy(String a, String b) {
        return a + b;
    }
}

class MaskStringsStrategy implements MergeStrategy<String> {

    @Override
    public String strategy(String a, String b) {
        return a.replace(b, "*".repeat(b.length()));
    }
}

public class GenericMapTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase == 1) { //Mergeable integers
            Map<Integer, Integer> mapLeft = new HashMap<>();
            Map<Integer, Integer> mapRight = new HashMap<>();
            readIntMap(sc, mapLeft);
            readIntMap(sc, mapRight);

            //TODO Create an object of type MergeStrategy that will enable merging of two Integer objects into a new Integer object which is their sum
            MergeStrategy mergeStrategy = new SumOfIntegersStrategy();

            printMap(MapOps.merge(mapLeft, mapRight, mergeStrategy));
        } else if (testCase == 2) { // Mergeable strings
            Map<String, String> mapLeft = new HashMap<>();
            Map<String, String> mapRight = new HashMap<>();
            readStrMap(sc, mapLeft);
            readStrMap(sc, mapRight);

            //TODO Create an object of type MergeStrategy that will enable merging of two String objects into a new String object which is their concatenation
            MergeStrategy<String> mergeStrategy = new ConcatStringsStrategy();

            printMap(MapOps.merge(mapLeft, mapRight, mergeStrategy));
        } else if (testCase == 3) {
            Map<Integer, Integer> mapLeft = new HashMap<>();
            Map<Integer, Integer> mapRight = new HashMap<>();
            readIntMap(sc, mapLeft);
            readIntMap(sc, mapRight);

            //TODO Create an object of type MergeStrategy that will enable merging of two Integer objects into a new Integer object which will be the max of the two objects
            MergeStrategy<Integer> mergeStrategy = new MaxOfIntegersStrategy();

            printMap(MapOps.merge(mapLeft, mapRight, mergeStrategy));
        } else if (testCase == 4) {
            Map<String, String> mapLeft = new HashMap<>();
            Map<String, String> mapRight = new HashMap<>();
            readStrMap(sc, mapLeft);
            readStrMap(sc, mapRight);

            //TODO Create an object of type MergeStrategy that will enable merging of two String objects into a new String object which will mask the occurrences of the second string in the first string

            MergeStrategy<String> mergeStrategy = new MaskStringsStrategy();
            printMap(MapOps.merge(mapLeft, mapRight, mergeStrategy));
        }
    }

    private static void readIntMap(Scanner sc, Map<Integer, Integer> map) {
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            int k = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            map.put(k, v);
        }
    }

    private static void readStrMap(Scanner sc, Map<String, String> map) {
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            map.put(parts[0], parts[1]);
        }
    }

    private static void printMap(Map<?, ?> map) {
        map.forEach((k, v) -> System.out.printf("%s -> %s%n", k.toString(), v.toString()));
    }
}
