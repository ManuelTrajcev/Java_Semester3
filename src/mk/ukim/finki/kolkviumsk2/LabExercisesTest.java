package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    String index;
    List<Integer> points;
    String sig;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
        if (points.size() >= 8) {
            sig = "YES";
        } else {
            sig = "NO";
        }
    }

    public double getAvgPoints() {
        return (double) points.stream().mapToInt(Integer::intValue).sum() / 10;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, sig, getAvgPoints());
    }
}

class LabExercises {
    List<Student> studentList;

    public LabExercises() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }


    public void printByAveragePoints(boolean ascending, int n) {
        if (ascending) {
            List<Student> filtered = studentList
                    .stream()
                    .sorted(Comparator.comparing(Student::getAvgPoints).thenComparing(Student::getIndex))
                    .collect(Collectors.toList());
            if (n > filtered.size()) n = filtered.size();
            filtered.subList(0, n).forEach(System.out::println);
        } else {
            List<Student> filtered = studentList
                    .stream()
                    .sorted(Comparator.comparing(Student::getAvgPoints).thenComparing(Student::getIndex).reversed())
                    .collect(Collectors.toList());
            if (n > filtered.size()) n = filtered.size();
            filtered.subList(0, n).forEach(System.out::println);
        }
    }

    public List<Student> failedStudents() {
        return studentList.stream()
                .filter(i -> i.sig.equals("NO"))
                .sorted(Comparator.comparing(Student::getIndex))
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> getStatisticsByYear() {
        Map<Integer, Double> map = new HashMap<>();
        List<Student> filtered = studentList.stream().filter(i -> i.sig.equals("YES")).collect(Collectors.toList());
        for (int i = 2; i < 10; i++) {
            int finalI = i;
            double sum = filtered
                    .stream()
                    .filter(student -> {
                        int curr = Integer.parseInt(student.getIndex());
                        curr /= 10000;
                        return 20 - curr == finalI;
                    })
                    .mapToDouble(s -> s.getAvgPoints()).sum();
            double n = filtered
                    .stream()
                    .filter(student -> {
                        int curr = Integer.parseInt(student.getIndex());
                        curr /= 10000;
                        return 20 - curr == finalI;
                    }).count();
            if (n > 0)
                map.put(i, sum / n);
        }

        return map;
    }
}

public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}