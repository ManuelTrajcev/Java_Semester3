package mk.ukim.finki.av6;

import java.io.*;
import java.util.Comparator;
import java.util.List;

class Person implements Comparable<Person> {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(String line) {
        String[] parts = line.split("\\s+");
        this.age = Integer.parseInt(parts[1]);
        this.name = parts[0];
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(age, o.age);
    }
}

class OldestPerson {
    public static Person find (InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return br.lines()
                .map(line -> new Person(line))
                .max(Comparator.naturalOrder())
                .orElse(new Person(20, "Manuel"));
    }
}

public class OldestPersonTest {

    public static void main(String[] args) {
       try {
            InputStream is = new FileInputStream("D:\\FINKI\\SEMESTAR III\\NAPREDNO PROGRAMIRANJE\\NP2023\\src\\mk\\ukim\\finki\\av6\\oldest");
           System.out.println(OldestPerson.find(is));
        } catch (FileNotFoundException e) {
           e.getMessage();
       }
    }
}
