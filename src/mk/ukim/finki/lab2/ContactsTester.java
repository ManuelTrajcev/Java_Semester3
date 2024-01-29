package mk.ukim.finki.lab2;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;


public class ContactsTester {

    public static class Operator {
        private String op;

        public Operator(int n) {
            if (n % 10 == 0 || n % 10 == 1 || n % 10 == 2) {
                op = "TMOBILE";
            } else if (n % 10 == 5 || n % 10 == 6) {
                op = "ONE";
            } else if (n % 10 == 7 || n % 10 == 8) {
                op = "VIP";
            }
        }

        @Override
        public String toString() {
            return op;
        }
    }

    public static abstract class Contact {
        private String date;

        public Contact(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        boolean isNewerThan(Contact c) {
            String[] parts = date.split("-");
            String[] partsC = c.getDate().split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            int yearC = Integer.parseInt(partsC[0]);
            int monthC = Integer.parseInt(partsC[1]);
            int dayC = Integer.parseInt(partsC[2]);

            if (year > yearC) {
                return true;
            } else if (year < yearC) {
                return false;
            } else {
                if (month > monthC) {
                    return true;
                } else if (month < monthC) {
                    return false;
                } else {
                    if (day > dayC) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        abstract String getType();


    }

    public static class EmailContact extends Contact {
        String email;

        public EmailContact(String date, String email) {
            super(date);
            this.email = email;
        }

        @Override
        boolean isNewerThan(Contact c) {
            return super.isNewerThan(c);
        }

        String getType() {
            return "Email";
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "\"" + email + '\"';
        }
    }

    public static class PhoneContact extends Contact {
        String phone;

        public PhoneContact(String date, String phone) {
            super(date);
            this.phone = phone;
        }

        @Override
        boolean isNewerThan(Contact c) {
            return super.isNewerThan(c);
        }

        String getType() {
            return "Phone";
        }

        public String getPhone() {
            return phone;
        }

        public int parsed(String phone) {
            return Integer.parseInt(phone.split("/")[0]);
        }

        Operator getOperator() {
            Operator op = new Operator(parsed(phone));
            return op;
        }

        @Override
        public String toString() {
            return "\"" + phone + '\"';
        }
    }

    public static class Student {
        private String firstName;
        private String lastName;
        private String city;
        private int age;
        private long index;

        private Contact[] contacts;
        private int numContacts;

        public Student(String firstName, String lastName, String city, int age, long index) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.city = city;
            this.age = age;
            this.index = index;
            this.contacts = new Contact[0];
            this.numContacts = 0;
        }

        public void addEmailContact(String date, String email) {
            EmailContact e = new EmailContact(date, email);
            Contact[] newContacts = new Contact[numContacts + 1];
            for (int i = 0; i < numContacts; i++) {
                newContacts[i] = contacts[i];
            }
            newContacts[numContacts++] = e;
            contacts = newContacts;
        }

        public void addPhoneContact(String date, String phone) {
            PhoneContact p = new PhoneContact(date, phone);
            Contact[] newContacts = new Contact[numContacts + 1];
            for (int i = 0; i < numContacts; i++) {
                newContacts[i] = contacts[i];
            }
            newContacts[numContacts++] = p;
            contacts = newContacts;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public String getCity() {
            return city;
        }

        public long getIndex() {
            return index;
        }

        public Contact getLatestContact() {
            Contact latest = contacts[0];
            for (int i = 0; i < numContacts; i++) {
                if (contacts[i].isNewerThan(latest)) {
                    latest = contacts[i];
                }
            }
            return latest;
        }

        public int getNumContacts() {
            return numContacts;
        }

        public Contact[] getEmailContacts() {
            int count = 0;
            int n = 0;
            for (int i = 0; i < contacts.length; i++) {
                if (contacts[i].getType().equals("Email")) {
                    count++;
                }
            }
            Contact[] emailContacts = new Contact[count];
            for (int i = 0; i < contacts.length; i++) {
                if (contacts[i].getType().equals("Email")) {
                    emailContacts[n++] = contacts[i];
                }
            }
            return emailContacts;
        }

        public Contact[] getPhoneContacts() {
            int count = 0;
            int n = 0;
            for (int i = 0; i < contacts.length; i++) {
                if (contacts[i].getType().equals("Phone")) {
                    count++;
                }
            }
            Contact[] emailContacts = new Contact[count];
            for (int i = 0; i < contacts.length; i++) {
                if (contacts[i].getType().equals("Phone")) {
                    emailContacts[n++] = contacts[i];
                }
            }
            return emailContacts;
        }

        @Override
        public String toString() {
            return "{\"ime\":\"" + firstName +
                    "\", \"prezime\":\"" + lastName +
                    "\", \"vozrast\":" + age +
                    ", \"grad\":\"" + city +
                    "\", \"indeks\":" + index +
                    ", \"telefonskiKontakti\":" + Arrays.toString(getPhoneContacts()) +
                    ", \"emailKontakti\":" + Arrays.toString(getEmailContacts()) +
                    "}";
        }
    }

    public static class Faculty {
        private String name;
        private Student[] students;

        public Faculty(String name, Student[] students) {
            this.name = name;
            this.students = students;
        }

        public int countStudentsFromCity(String cityName) {
            int counter = 0;
            for (int i = 0; i < students.length; i++) {
                if (students[i].getCity().equals(cityName)) {
                    counter++;
                }
            }
            return counter;
        }

        public Student getStudent(long index) {
            for (Student s :
                    students) {
                if (s.getIndex() == index) {
                    return s;
                }
            }
            return null;
        }

        public double getAverageNumberOfContacts() {
            double counter = 0;
            for (Student s :
                    students) {
                counter += s.getNumContacts();
            }
            return counter / students.length;
        }

        public Student getStudentWithMostContacts() {
            Student max = students[0];
            for (Student s :
                    students) {
                if (s.getNumContacts() > max.getNumContacts()) {
                    max = s;
                } else if (s.getNumContacts() == max.getNumContacts()) {
                    if (s.getIndex() > max.getIndex()) {
                        max = s;
                    }
                }
            }
            return max;
        }

        @Override
        public String toString() {
            return "{\"fakultet\":\"" +
                    name +
                    "\", \"studenti\":" + Arrays.toString(students) +
                    "}";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        Faculty faculty = null;

        int rvalue = 0;
        long rindex = -1;

        DecimalFormat df = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            rvalue++;
            String operation = scanner.next();

            switch (operation) {
                case "CREATE_FACULTY": {
                    String name = scanner.nextLine().trim();
                    int N = scanner.nextInt();

                    Student[] students = new Student[N];

                    for (int i = 0; i < N; i++) {
                        rvalue++;

                        String firstName = scanner.next();
                        String lastName = scanner.next();
                        String city = scanner.next();
                        int age = scanner.nextInt();
                        long index = scanner.nextLong();

                        if ((rindex == -1) || (rvalue % 13 == 0))
                            rindex = index;

                        Student student = new Student(firstName, lastName, city,
                                age, index);
                        students[i] = student;
                    }

                    faculty = new Faculty(name, students);
                    break;
                }

                case "ADD_EMAIL_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String email = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addEmailContact(date, email);
                    break;
                }

                case "ADD_PHONE_CONTACT": {
                    long index = scanner.nextInt();
                    String date = scanner.next();
                    String phone = scanner.next();

                    rvalue++;

                    if ((rindex == -1) || (rvalue % 3 == 0))
                        rindex = index;

                    faculty.getStudent(index).addPhoneContact(date, phone);
                    break;
                }

                case "CHECK_SIMPLE": {
                    System.out.println("Average number of contacts: "
                            + df.format(faculty.getAverageNumberOfContacts()));

                    rvalue++;

                    String city = faculty.getStudent(rindex).getCity();
                    System.out.println("Number of students from " + city + ": "
                            + faculty.countStudentsFromCity(city));

                    break;
                }

                case "CHECK_DATES": {

                    rvalue++;

                    System.out.print("Latest contact: ");
                    Contact latestContact = faculty.getStudent(rindex)
                            .getLatestContact();
                    if (latestContact.getType().equals("Email"))
                        System.out.println(((EmailContact) latestContact)
                                .getEmail());
                    if (latestContact.getType().equals("Phone"))
                        System.out.println(((PhoneContact) latestContact)
                                .getPhone()
                                + " ("
                                + ((PhoneContact) latestContact).getOperator()
                                .toString() + ")");

                    if (faculty.getStudent(rindex).getEmailContacts().length > 0
                            && faculty.getStudent(rindex).getPhoneContacts().length > 0) {
                        System.out.print("Number of email and phone contacts: ");
                        System.out
                                .println(faculty.getStudent(rindex)
                                        .getEmailContacts().length
                                        + " "
                                        + faculty.getStudent(rindex)
                                        .getPhoneContacts().length);

                        System.out.print("Comparing dates: ");
                        int posEmail = rvalue
                                % faculty.getStudent(rindex).getEmailContacts().length;
                        int posPhone = rvalue
                                % faculty.getStudent(rindex).getPhoneContacts().length;

                        System.out.println(faculty.getStudent(rindex)
                                .getEmailContacts()[posEmail].isNewerThan(faculty
                                .getStudent(rindex).getPhoneContacts()[posPhone]));
                    }

                    break;
                }

                case "PRINT_FACULTY_METHODS": {
                    System.out.println("Faculty: " + faculty.toString());
                    System.out.println("Student with most contacts: "
                            + faculty.getStudentWithMostContacts().toString());
                    break;
                }

            }

        }

        scanner.close();
    }
}