//package mk.ukim.finki.kolkviumsk2;
//
//import java.io.*;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * January 2016 Exam problem 1
// */
//
//class Student implements Comparable<Student> {
//    String code;
//    String program;
//    List<Integer> grades;
//
//    public Student(String code, String program, List<Integer> grades) {
//        this.code = code;
//        this.program = program;
//        this.grades = grades;
//    }
//
//    public double avg() {
//        return (double) grades.stream().mapToInt(i -> i).sum() / grades.size();
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public static Comparator<Student> comparator() {
//        return Comparator.comparing(Student::avg).reversed().thenComparing(Student::getCode);
//    }
//
//    @Override
//    public int compareTo(Student o) {
//        return comparator().compare(this, o);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s %.2f", code, avg());
//    }
//}
//
//class StudentRecords {
//    TreeMap<String, TreeSet<Student>> students;
//
//    public StudentRecords() {
//        this.students = new TreeMap<>();
//    }
//
//    public Student studentFactory(String line) {
//        String[] parts = line.split("\\s+");
//        List<Integer> grades = new ArrayList<>();
//        for (int i = 2; i < parts.length; i++) {
//            grades.add(Integer.parseInt(parts[i]));
//        }
//        return new Student(parts[0], parts[1], grades);
//    }
//
//    public int readRecords(InputStream in) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//        br.lines().forEach(l -> {
//            String[] parts = l.split("\\s+");
//            if (students.containsKey(parts[1])) {
//                students.get(parts[1]).add(studentFactory(l));
//            } else {
//                TreeSet<Student> tmp = new TreeSet<>();
//                tmp.add(studentFactory(l));
//                students.put(parts[1], tmp);
//            }
//        });
//        return students.values().stream().mapToInt(TreeSet::size).sum();
//    }
//
//    public void writeTable(OutputStream os) {
//        students.entrySet()
//                .forEach(p -> {
//                    System.out.println(p.getKey());
//                    p.getValue()
//                            .forEach(System.out::println);
//                });
//    }
//
//    public List<Integer> getGradesPerProgram(TreeSet<Student> students) {
//        List<Integer> allGrades = students.stream().flatMap(s -> s.grades.stream()).collect(Collectors.toList());
//        List<Integer> sortedGrades = new ArrayList<>();
//        sortedGrades.add((int) allGrades.stream().filter(i -> i == 6).count());
//        sortedGrades.add((int) allGrades.stream().filter(i -> i == 7).count());
//        sortedGrades.add((int) allGrades.stream().filter(i -> i == 8).count());
//        sortedGrades.add((int) allGrades.stream().filter(i -> i == 9).count());
//        sortedGrades.add((int) allGrades.stream().filter(i -> i == 10).count());
//        return sortedGrades;
//    }
//
//    public void writeDistribution(OutputStream os) {
//        HashMap<String, List<Integer>> sorted = new HashMap<>();
//        students.entrySet()
//                .forEach(p -> {
//                    sorted.put(p.getKey(), getGradesPerProgram(p.getValue()));
//                });
//        LinkedHashMap<String, List<Integer>> finalMap = sorted.entrySet()
//                .stream()
//                .sorted(Comparator.comparing(i -> i.getValue().get(4), Comparator.reverseOrder()))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
//        finalMap.entrySet().forEach(i -> {
//            System.out.println(i.getKey());
//            for (int j = 0; j < i.getValue().size(); j++) {
//                String stars = "*".repeat((int) Math.ceil(i.getValue().get(j) / 10.0));
//                System.out.printf("%2d | %s(%d)\n", j + 6, stars, i.getValue().get(j));
//            }
//        });
//    }
//}
//
//public class StudentRecordsTest {
//    public static void main(String[] args) {
//        System.out.println("=== READING RECORDS ===");
//        StudentRecords studentRecords = new StudentRecords();
//        int total = studentRecords.readRecords(System.in);
//        System.out.printf("Total records: %d\n", total);
//        System.out.println("=== WRITING TABLE ===");
//        studentRecords.writeTable(System.out);
//        System.out.println("=== WRITING DISTRIBUTION ===");
//        studentRecords.writeDistribution(System.out);
//    }
//}
//
//// your code here