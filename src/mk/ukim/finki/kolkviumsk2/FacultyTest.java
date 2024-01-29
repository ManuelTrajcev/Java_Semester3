//package mk.ukim.finki.kolkviumsk2;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//class OperationNotAllowedException extends Exception {
//    public OperationNotAllowedException(String message) {
//        super(message);
//    }
//}
//
//class Student {
//    private final String id;
//    private final int yearsOfStudy;
//    final HashMap<Integer, List<Integer>> grades;
//    final List<String> courses;
//
//    public Student(String id, int yearsOfStudy) {
//        this.id = id;
//        this.yearsOfStudy = yearsOfStudy;
//        grades = makeTheMap();
//        courses = new ArrayList<>();
//    }
//
//    private HashMap<Integer, List<Integer>> makeTheMap() {
//        HashMap<Integer, List<Integer>> tmp = new HashMap<>();
//        if (yearsOfStudy == 3) {
//            IntStream.range(0, 6).forEach(i -> tmp.put(i + 1, new ArrayList<>()));
//        } else {
//            IntStream.range(0, 8).forEach(i -> tmp.put(i + 1, new ArrayList<>()));
//        }
//        return tmp;
//    }
//
//    public int getNumTerms() {
//        return yearsOfStudy == 3 ? 6 : 8;
//    }
//
//    public int passedCourses() {
//        return grades.values()
//                .stream().mapToInt(List::size)
//                .sum();
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public int getYearsOfStudy() {
//        return yearsOfStudy;
//    }
//
//    public double getAvgGrade() {
//        return grades.values()
//                .stream()
//                .flatMapToInt(l -> l.stream().mapToInt(Integer::intValue))
//                .average()
//                .orElse(5.0);
//    }
//
//    public String getReport() {
//        StringBuilder str = new StringBuilder();
//        str.append("Student: ").append(id).append("\n");
//        for (int i = 1; i <= grades.size(); i++) {
//            str.append("Term ").append(i).append("\n");
//            str.append("Courses: ").append(grades.get(i).size()).append("\n");
//
//            double averageGrade = grades.get(i).stream()
//                    .mapToInt(Integer::intValue)
//                    .average()
//                    .orElse(5.0);
//
//            str.append("Average grade for term: ").append(String.format("%.2f", averageGrade)).append("\n");
//        }
//        str.append(String.format("Average grade: %.2f", getAvgGrade())).append("\n");
//        str.append("Courses attended: ");
//        courses.stream().forEach(c -> str.append(c).append(","));
//        str.append("\n");
//
//        return str.toString();
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Student: %s Courses passed: %d Average grade: %.2f", id, passedCourses(), getAvgGrade());
//    }
//}
//
//class Faculty {
//    private final Map<String, Student> students;
//    private final List<String> logs;
//    private final Map<String, List<Integer>> courses;
//
//    public Faculty() {
//        students = new HashMap<>();
//        logs = new ArrayList<>();
//        courses = new HashMap<>();
//    }
//
//    void addStudent(String id, int yearsOfStudies) {
//        students.put(id, new Student(id, yearsOfStudies));
//    }
//
//    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
//        Student curr = students.get(studentId);
//        if (students.get(studentId).getNumTerms() < term) {
//            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, studentId));
//        }
//        if (students.get(studentId).grades.get(term).size() == 3) {
//            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", studentId, term));
//        }
//        students.get(studentId).grades.get(term).add(grade);
//        if (courses.containsKey(courseName)) {
//            courses.get(courseName).add(grade);
//        } else {
//            List<Integer> tmp = new ArrayList<>();
//            tmp.add(grade);
//            courses.put(courseName, tmp);
//        }
//        if (!students.get(studentId).courses.contains(courseName)) students.get(studentId).courses.add(courseName);
//
//        if (students.get(studentId).getYearsOfStudy() == 3) {
//            if (students.get(studentId).passedCourses() == 18) {
//                logs.add(String.format("Student with ID %s graduated with average grade %.2f in %d years.",
//                        curr.getId(), curr.getAvgGrade(), curr.getYearsOfStudy()));
//                students.remove(studentId);
//            }
//        } else {
//            if (students.get(studentId).passedCourses() == 24) {
//                logs.add(String.format("Student with ID %s graduated with average grade %.2f in %d years.",
//                        curr.getId(), curr.getAvgGrade(), curr.getYearsOfStudy()));
//                students.remove(studentId);
//            }
//        }
//    }
//
//    String getFacultyLogs() {
//        StringBuilder stringBuilder = new StringBuilder();
//        logs.forEach(log ->
//                stringBuilder.append(log).append("\n"));
//        return stringBuilder.toString();
//    }
//
//    String getDetailedReportForStudent(String id) {
//        return students.get(id).getReport();
//    }
//
//    void printFirstNStudents(int n) {
//        if (n > students.size()) n = students.size();
//        List<Student> sorted = students.values()
//                .stream()
//                .sorted(Comparator.comparing(Student::passedCourses).thenComparing(Student::getAvgGrade).thenComparing(Student::getId).reversed())
//                .collect(Collectors.toList());
//        sorted.subList(0, n)
//                .forEach(System.out::println);
//    }
//
//    void printCourses() {
//        courses.entrySet()
//                .stream()
//                .sorted(Comparator.comparingDouble(entry -> entry.getValue().stream().mapToDouble(Integer::doubleValue).average().orElse(5.0)))
//                .forEach(course -> {
//                    System.out.println(String.format("%s %d %.2f", course.getKey(), course.getValue().size(), course.getValue().stream().mapToDouble(i -> i).average().orElse(5.0)));
//                });
//    }
//}
//
//public class FacultyTest {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int testCase = sc.nextInt();
//
//        if (testCase == 1) {
//            System.out.println("TESTING addStudent AND printFirstNStudents");
//            Faculty faculty = new Faculty();
//            for (int i = 0; i < 10; i++) {
//                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
//            }
//            faculty.printFirstNStudents(10);
//
//        } else if (testCase == 2) {
//            System.out.println("TESTING addGrade and exception");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("123", 3);
//            faculty.addStudent("1234", 4);
//            try {
//                faculty.addGradeToStudent("123", 7, "NP", 10);
//            } catch (OperationNotAllowedException e) {
//                System.out.println(e.getMessage());
//            }
//            try {
//                faculty.addGradeToStudent("1234", 9, "NP", 8);
//            } catch (OperationNotAllowedException e) {
//                System.out.println(e.getMessage());
//            }
//        } else if (testCase == 3) {
//            System.out.println("TESTING addGrade and exception");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("123", 3);
//            faculty.addStudent("1234", 4);
//            for (int i = 0; i < 4; i++) {
//                try {
//                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
//                } catch (OperationNotAllowedException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            for (int i = 0; i < 4; i++) {
//                try {
//                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
//                } catch (OperationNotAllowedException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        } else if (testCase == 4) {
//            System.out.println("Testing addGrade for graduation");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("123", 3);
//            faculty.addStudent("1234", 4);
//            int counter = 1;
//            for (int i = 1; i <= 6; i++) {
//                for (int j = 1; j <= 3; j++) {
//                    try {
//                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
//                    } catch (OperationNotAllowedException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    ++counter;
//                }
//            }
//            counter = 1;
//            for (int i = 1; i <= 8; i++) {
//                for (int j = 1; j <= 3; j++) {
//                    try {
//                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
//                    } catch (OperationNotAllowedException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    ++counter;
//                }
//            }
//            System.out.println("LOGS");
//            System.out.println(faculty.getFacultyLogs());
//            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
//            faculty.printFirstNStudents(2);
//        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
//            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
//            Faculty faculty = new Faculty();
//            for (int i = 1; i <= 10; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//            }
//            if (testCase == 5)
//                faculty.printFirstNStudents(10);
//            else if (testCase == 6)
//                faculty.printFirstNStudents(3);
//            else
//                faculty.printFirstNStudents(20);
//        } else if (testCase == 8 || testCase == 9) {
//            System.out.println("TESTING DETAILED REPORT");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
//            int grade = 6;
//            int counterCounter = 1;
//            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
//                for (int j = 1; j < 3; j++) {
//                    try {
//                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
//                    } catch (OperationNotAllowedException e) {
//                        e.printStackTrace();
//                    }
//                    grade++;
//                    if (grade == 10)
//                        grade = 5;
//                    ++counterCounter;
//                }
//            }
//            System.out.println(faculty.getDetailedReportForStudent("student1"));
//        } else if (testCase == 10) {
//            System.out.println("TESTING PRINT COURSES");
//            Faculty faculty = new Faculty();
//            for (int i = 1; i <= 10; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
//                        int grade = sc.nextInt();
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//            }
//            faculty.printCourses();
//        } else if (testCase == 11) {
//            System.out.println("INTEGRATION TEST");
//            Faculty faculty = new Faculty();
//            for (int i = 1; i <= 10; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
//                        int grade = sc.nextInt();
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//
//            }
//
//            for (int i = 11; i < 15; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= 3; k++) {
//                        int grade = sc.nextInt();
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//            }
//            System.out.println("LOGS");
//            System.out.println(faculty.getFacultyLogs());
//            System.out.println("DETAILED REPORT FOR STUDENT");
//            System.out.println(faculty.getDetailedReportForStudent("student2"));
//            try {
//                System.out.println(faculty.getDetailedReportForStudent("student11"));
//                System.out.println("The graduated students should be deleted!!!");
//            } catch (NullPointerException e) {
//                System.out.println("The graduated students are really deleted");
//            }
//            System.out.println("FIRST N STUDENTS");
//            faculty.printFirstNStudents(10);
//            System.out.println("COURSES");
//            faculty.printCourses();
//        }
//    }
//}
