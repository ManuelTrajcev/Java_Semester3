package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;

class Names {
    Map<String, Integer> namesMap;

    public Names() {
        this.namesMap = new TreeMap<>();
    }

    public void addName(String name) {
        if (namesMap.containsKey(name)) {
            int occ = namesMap.get(name);
            namesMap.put(name, occ + 1);
        } else {
            namesMap.put(name, 1);
        }
    }

    public void printN(int n) {
        namesMap.entrySet()
                .stream()
                .filter(i -> i.getValue() >= n)
                .forEach(name -> System.out.println(String.format("%s (%d) %d", name.getKey(), name.getValue(), uniqueChars(name.getKey()))));
    }

    private int uniqueChars(String name) {
        Set<Character> uniques = new HashSet<>();
        uniques = name.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());
        return uniques.size();
    }

    public String findName(int len, int index) {
        List<String> unique = namesMap.entrySet()
                .stream()
                .filter(name -> name.getKey().length() < len)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        index %= unique.size();
        return unique.get(index);
    }
}

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}
