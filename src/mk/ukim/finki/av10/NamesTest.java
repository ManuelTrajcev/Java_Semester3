package mk.ukim.finki.av10;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Names {
    Map<String, Integer> maleNames;
    Map<String, Integer> femaleNames;

    public Names() {
    }

    private Map<String, Integer> readNames(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.lines().collect(Collectors.toMap(
                line -> line.split("\\s+")[0],
                line -> Integer.parseInt(line.split("\\s+")[1]),
                Integer::sum,
                TreeMap::new
        ));
    }

    void readMaleNames(InputStream is) {
        maleNames = readNames(is);
    }

    void readFemaleNames(InputStream is) {
        femaleNames = readNames(is);
    }

    void uniSexNames() {
        Set<String> allNames = new HashSet<>();
        Map<String, Integer> unisex = new HashMap<>();

        allNames.addAll(maleNames.keySet());
        allNames.addAll(femaleNames.keySet());

        allNames.stream()
                .filter(name -> maleNames.containsKey(name) && femaleNames.containsKey(name))
                .forEach(name -> unisex.put(name, maleNames.get(name) + femaleNames.get(name)));

        unisex.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> System.out.println(String.format("%s, %d", e.getKey(), e.getValue())));
    }


}

public class NamesTest {

    public static void main(String[] args) {

            Names names = new Names();

            try {
                names.readMaleNames(new FileInputStream("D:\\FINKI\\SEMESTAR III\\NAPREDNO PROGRAMIRANJE\\NP2023\\src\\mk\\ukim\\finki\\av10\\Data\\male.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                names.readFemaleNames(new FileInputStream("D:\\FINKI\\SEMESTAR III\\NAPREDNO PROGRAMIRANJE\\NP2023\\src\\mk\\ukim\\finki\\av10\\Data\\female.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            names.uniSexNames();
    }
}
