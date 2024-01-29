package mk.ukim.finki.genericko;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class ExecuteTest<T> {
    List<T> inputs;
    Function<T, T> function;

    public ExecuteTest(List<T> inputs, Function<T, T> function) {
        this.inputs = inputs;
        this.function = function;
    }

    public static <T> List<T> execute(List<T> input, Function<T, T> function) {
        return input.stream().map(i -> function.apply(i)).collect(Collectors.toList());
    }
}

class Student {
    String id;

    public Student(String id) {
        this.id = id;
    }

    public Student setId() {
        id += "_FCSE";
        return this;
    }

    @Override
    public String toString() {
        return id;
    }
}

public class test {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(i + 100);
        }
        for (int i = 10; i < 20; i++) {
            studentList.add(new Student("2210"+i));
        }
        ExecuteTest.execute(studentList, Student::setId);
        List<Integer> result1 = ExecuteTest.execute(integerList, i -> i * i);
        System.out.println(result1);
        List<Student> studentList1 = ExecuteTest.execute(studentList, s -> s.setId());
        System.out.println(studentList1);
    }
}
