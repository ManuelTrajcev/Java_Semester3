package algoritmi.av7;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Lecture implements Comparable<Lecture> {
    LocalDate date;
    LocalTime time;
    String city;
    int price;

    public Lecture(String line) {
        String[] parts = line.split("\\s+");
        String date = parts[0].replaceAll("/", "-");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = LocalDate.parse(date, df);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(parts[1], dt);
        this.city = parts[2];
        this.price = Integer.parseInt(parts[3]);
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time.toString();
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(Lecture o) {
        return Integer.compare(this.price, o.price);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d", city, date, time, price);
    }
}

public class zad2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        CBHT<String, Lecture> lecturesPerDate = new CBHT<String, Lecture>(2 * n);


        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String date = line.split("\\s+")[0];
            Lecture tmp = new Lecture(line);
//            if (lecturesPerDate.search(date) != null) {
                lecturesPerDate.insert(date, tmp);
//            } else {
//                ArrayList<Lecture> curr = new ArrayList<>();
//                curr.add(tmp);
//                lecturesPerDate.insert(date, curr);
//            }
        }

        String findDate = sc.nextLine();

//        SLLNode<MapEntry<String, ArrayList<Lecture>>> found = lecturesPerDate.search(findDate);

//        if (found == null) {
//            System.out.println("No offers");
//        } else {
//            found.element.value
//                    .sort(Comparator.comparing(Lecture::getPrice).reversed());
//            System.out.println(found.element.value.get(0));
//
//        }

//        CBHT<String, ArrayList<Lecture>> hash = new CBHT<>(2 * n);
//        for (int i = 0; i < n; i++) {
//            String line = sc.nextLine();
//            Lecture l = new Lecture(line);
//            if (hash.search(l.getDate()) == null) {
//                ArrayList<Lecture> lectures = new ArrayList<>();
//                lectures.add(l);
//                hash.insert(l.getDate(), lectures);
//            } else {
//                SLLNode<MapEntry<String, ArrayList<Lecture>>> res = hash.search(l.getDate());
//                ArrayList<Lecture> lectures = res.element.value;
//                lectures.add(l);
//                Collections.sort(lectures, Collections.reverseOrder());
//                hash.insert(l.getDate(), lectures);
//            }
//        }
//        String date = sc.nextLine().replaceAll("/", "-");
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate searchDate = LocalDate.parse(date, df);
//        SLLNode<MapEntry<String, ArrayList<Lecture>>> s = hash.search(searchDate.toString());
//        if (s != null) {
//            System.out.println(s.element.value.get(0));
//        } else {
//            System.out.println("No offers for that date");
//        }
    }
}
