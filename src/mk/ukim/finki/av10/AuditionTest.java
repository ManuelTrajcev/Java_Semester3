package mk.ukim.finki.av10;

import java.util.*;
import java.util.stream.Collectors;


class Participant {
    String code;
    String name;
    int age;

    public Participant(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }
}

class Audition {
    Map<String, TreeSet<Participant>> participantByCity = new HashMap<>();
    Map<String, HashSet<String>> idByCity = new HashMap<>();

    public void addParticpant(String city, String code, String name, int age) {
        Participant participant = new Participant(code, name, age);

        idByCity.putIfAbsent(city, new HashSet<>());
        participantByCity.putIfAbsent(city, new TreeSet<>(Comparator.comparing(Participant::getName).thenComparing(Participant::getAge).thenComparing(Participant::getCode)));

        if (idByCity.get(city).contains(code)) {
            return;
        } else {
            idByCity.get(city).add(code);
            participantByCity.get(city).add(participant);
        }

    }

    public void listByCity(String city) {
//        Map<String, TreeSet<Participant>> participantsByCity = participatns.collect(Collectors.groupingBy(
//                Participant::getCity,
//                Collectors.toCollection(() -> TreeSet::new).Comparator.comparing(Participant::getName).thenComparing(Participant::getAge).thenComparing(Participant::getCode)))
//        ));
//        Map<String, Long> participantsByCity = participatns.collect(Collectors.groupingBy(
//                Participant::getCity,     //ucesnici po grad
//                Collectors.counting()
//        Map<String, Double> participants.collect(Collectors.groupingBy(
//        Participants::getCity,
//
//        )

        participantByCity.get(city).forEach(p -> System.out.println(p));
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}
