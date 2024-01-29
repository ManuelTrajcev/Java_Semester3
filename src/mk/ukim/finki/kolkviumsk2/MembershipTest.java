//package mk.ukim.finki.kolkviumsk2;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//class PersonalInformation {
//    String country;
//    Integer age;
//
//    public PersonalInformation(String country, Integer age) {
//        this.country = country;
//        this.age = age;
//    }
//}
//
//class User implements Comparable<User>{
//    private static long ID = 1L;
//    Long id;
//    String userName;
//    String password;
//    String email;
//    PersonalInformation info;
//
//
//    public User(String userName, String password, String email, String country, Integer age) {
//        this.id = ID++;
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//        this.info = info;
//        this.info = new PersonalInformation(country, age);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("{%d: %s, %s", id, userName, email);
//    }
//
//    public static long getID() {
//        return ID;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public PersonalInformation getInfo() {
//        return info;
//    }
//
//    @Override
//    public int compareTo(User o) {
//        return Comparator.comparing(User::getEmail).compare(this, o);
//    }
//}
//
//class Membership {
//    private static List<User> users = null;
//
//    public Membership(List<User> users) {
//        this.users = users;
//    }
//
//    public static void createUser(String userName, String password, String email, String country, Integer age) {
//        users.add(new User(userName, password, email, country, age));
//    }
//
//    public static boolean deleteUser(int id) {
//        return users.removeIf(u -> u.getId() == id);
//    }
//
//    public static List<User> getUserOrderedByEmail() {
//        return users.stream().sorted().collect(Collectors.toList());
//    }
//
//    public static Map<String, Integer> getNDifferentUsersByEmail() {
//        Map<String, Integer> usersByEmail = new TreeMap<>();        //groupingById
//        ListIterator<User> userListIterator = users.listIterator();
//        while (userListIterator.hasNext()) {
//            User u = userListIterator.next();
//            if (!usersByEmail.containsKey(u.getEmail())) {
//                usersByEmail.put(u.getEmail(), 1);
//            } else {
//                usersByEmail.put(u.getEmail(), usersByEmail.get(u.getEmail() + 1));
//            }
//        }
//        return usersByEmail;
//    }
//}
//
//public class MembershipTest {
//}
