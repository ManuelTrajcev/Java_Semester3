//package mk.ukim.finki.kolkviumsk2;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//class InvalidPointsException extends Exception {
//    public InvalidPointsException() {
//    }
//}
//
//class Student {
//    String id;
//    String name;
//    int firstMidTermPoints;
//    int secondMidTermPoints;
//    int labsPoints;
//
//    public Student(String id, String name) {
//        this.id = id;
//        this.name = name;
//        firstMidTermPoints = 0;
//        secondMidTermPoints = 0;
//        labsPoints = 0;
//    }
//
//    public void setFirstMidTermPoints(int firstMidTermPoints) throws InvalidPointsException {
//        if (labsPoints > 100) throw new InvalidPointsException();
//        this.firstMidTermPoints = firstMidTermPoints;
//    }
//
//    public void setSecondMidTermPoints(int secondMidTermPoints) throws InvalidPointsException {
//        if (labsPoints > 100) throw new InvalidPointsException();
//        this.secondMidTermPoints = secondMidTermPoints;
//    }
//
//    public void setLabsPoints(int labsPoints) throws InvalidPointsException {
//        if (labsPoints > 10) throw new InvalidPointsException();
//        this.labsPoints = labsPoints;
//    }
//
//    public double getTotalPoints() {
//        return firstMidTermPoints * 0.45 + secondMidTermPoints * 0.45 + labsPoints;
//    }
//
//    public int getGrade() {
//        int grade = (int) Math.ceil(getTotalPoints() / 10);
//        if (grade < 5) grade = 5;
//        return grade;
//    }
//
//    public String getID() {
//        return id;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("ID: %s Name: %s First midterm: %d Second midterm %d Labs: %d Summary points: %.2f Grade: %d"
//                , id, name, firstMidTermPoints, secondMidTermPoints, labsPoints, getTotalPoints(), getGrade());
//    }
//}
//
//class AdvancedProgrammingCourse {
//    Map<String, Student> students;
//
//    public AdvancedProgrammingCourse() {
//        this.students = new LinkedHashMap<>();
//    }
//
//    public void addStudent(Student student) {
//        students.put(student.id, student);
//    }
//
//    public void updateStudent(String idNumber, String activity, int points) {
//        try {
//            if (activity.equals("midterm1")) {
//                students.get(idNumber).setFirstMidTermPoints(points);
//            } else if (activity.equals("midterm2")) {
//                students.get(idNumber).setSecondMidTermPoints(points);
//            } else if (activity.equals("labs")) {
//                students.get(idNumber).setLabsPoints(points);
//            }
//        } catch (InvalidPointsException e) {
//
//        }
//        students = students.values()
//                .stream()
//                .sorted(Comparator.comparing(Student::getTotalPoints).reversed())
//                .collect(Collectors.toMap(
//                        Student::getID,
//                        student -> student,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
//    }
//
//    public List<Student> getFirstNStudents(int n) {
//        return students.values().stream().collect(Collectors.toList()).subList(0, n);
//    }
//
//    public Map<Integer, Integer> getGradeDistribution() {
//        Map<Integer, Integer> distribution = new TreeMap<>();
//        for (int i = 5; i <= 10; i++) {
//            int finalI = i;
//            int num = (int) students.entrySet()
//                    .stream()
//                    .filter(s -> s.getValue().getGrade() == finalI)
//                    .count();
//            distribution.put(i, num);
//        }
//        return distribution;
//    }
//
//    public void printStatistics() {
//        DoubleSummaryStatistics stats = students.values().stream().filter(s -> s.getGrade() > 5).mapToDouble(Student::getTotalPoints).summaryStatistics();
//        System.out.printf("Count: %d Min: %.2f Average: %.2f Max: %.2f\n", stats.getCount(), stats.getMin(), stats.getAverage(), stats.getMax());
//    }
//}
//
//
//public class CourseTest {
//
//    public static void printStudents(List<Student> students) {
//        students.forEach(System.out::println);
//    }
//
//    public static void printMap(Map<Integer, Integer> map) {
//        map.forEach((k, v) -> System.out.printf("%d -> %d%n", k, v));
//    }
//
//    public static void main(String[] args) {
//        AdvancedProgrammingCourse advancedProgrammingCourse = new AdvancedProgrammingCourse();
//
//        Scanner sc = new Scanner(System.in);
//
//        while (sc.hasNextLine()) {
//            String line = sc.nextLine();
//            String[] parts = line.split("\\s+");
//
//            String command = parts[0];
//
//            if (command.equals("addStudent")) {
//                String id = parts[1];
//                String name = parts[2];
//                advancedProgrammingCourse.addStudent(new Student(id, name));
//            } else if (command.equals("updateStudent")) {
//                String idNumber = parts[1];
//                String activity = parts[2];
//                int points = Integer.parseInt(parts[3]);
//                advancedProgrammingCourse.updateStudent(idNumber, activity, points);
//            } else if (command.equals("getFirstNStudents")) {
//                int n = Integer.parseInt(parts[1]);
//                printStudents(advancedProgrammingCourse.getFirstNStudents(n));
//            } else if (command.equals("getGradeDistribution")) {
//                printMap(advancedProgrammingCourse.getGradeDistribution());
//            } else {
//                advancedProgrammingCourse.printStatistics();
//            }
//        }
//    }
//}
