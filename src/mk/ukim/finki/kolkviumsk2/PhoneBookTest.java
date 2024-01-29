package mk.ukim.finki.kolkviumsk2;

import java.util.*;


class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String message) {
        super(message);
    }
}

class PhoneBook {
    Map<String, String> contacts;

    public PhoneBook() {
        this.contacts = new TreeMap<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (contacts.containsKey(number)) {
            throw new DuplicateNumberException(String.format("Duplicate number: %s", number));
        } else {
            contacts.put(number, name);
        }
    }

    public void contactsByNumber(String number) {
        if (contacts.entrySet().stream().noneMatch(c -> c.getKey().contains(number))) {
            System.out.println("NOT FOUND");
        } else
            contacts.entrySet()
                    .stream()
                    .filter(c -> c.getKey().contains(number))
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(i -> System.out.println(i.getValue() + " " + i.getKey()));
    }


    public void contactsByName(String name) {
        if (contacts.values().stream().noneMatch(c -> c.equals(name))) {
            System.out.println("NOT FOUND");
        } else
            contacts.entrySet()
                    .stream()
                    .filter(c -> c.getValue().equals(name))
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .forEach(i -> System.out.println(name + " " + i.getKey()));
    }
}

public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}

