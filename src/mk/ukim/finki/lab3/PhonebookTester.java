package mk.ukim.finki.lab3;

import java.util.*;
import java.util.stream.Collectors;


class InvalidNameException extends Exception {
    public String name;


    public InvalidNameException(String name) {
        this.name = name;
    }
}

class InvalidNumberException extends Exception {
    public InvalidNumberException() {
    }
}

class MaximumSizeExceddedException extends Exception {
    public MaximumSizeExceddedException() {
    }
}

class InvalidFormatException extends Exception {
    public InvalidFormatException() {
    }
}


class Contact {
    String name;
    List<String> phoneNumbers;

    public Contact(String name, String[] numbers) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        if (name.length() <= 4 || name.length() > 10) throw new InvalidNameException(name);
        if (checkName(name)) throw new InvalidNameException(name);
        this.name = name;
        phoneNumbers = new ArrayList<>();
        addPhoneNumbers(numbers);
    }

    public Contact(String name) throws InvalidNameException {
        if (name.length() <= 4 || name.length() > 10) throw new InvalidNameException(name);
        if (checkName(name)) throw new InvalidNameException(name);
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
    }

    public Contact(String name, String randomLegitNumber, String randomLegitNumber1, String randomLegitNumber2) throws InvalidNameException, InvalidNumberException {
        if (name.length() <= 4 || name.length() > 10) throw new InvalidNameException(name);
        if (checkName(name)) throw new InvalidNameException(name);
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        addNumber(randomLegitNumber);
        addNumber(randomLegitNumber1);
        addNumber(randomLegitNumber2);
    }

    public Contact(String name, String number) throws InvalidNumberException, InvalidNameException {
        if (name.length() <= 4 || name.length() > 10) throw new InvalidNameException(name);
        if (checkName(name)) throw new InvalidNameException(name);
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        addNumber(number);
    }

    private boolean checkName(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkNumber(String number) throws InvalidNumberException {
        char[] numbers = number.toCharArray();
        if (numbers[0] != '0' || numbers[1] != '7' || numbers[2] == '9' || numbers[2] == '4' || numbers[2] == '3') {
            return true;
        }
        for (char c : numbers) {
            if (!Character.isDigit(c)) return true;
        }
        return false;
    }

    public void addNumber(String number) throws InvalidNumberException {
        if (number.length() != 9 || checkNumber(number)) {
            throw new InvalidNumberException();
        } else {
            phoneNumbers.add(number);
        }
    }

    public void addPhoneNumbers(String[] numbers) throws InvalidNumberException, MaximumSizeExceddedException {
        if (numbers.length > 5) throw new MaximumSizeExceddedException();

        for (String i : numbers) {
            if (i.length() != 9 || checkNumber(i)) {
                throw new InvalidNumberException();
            } else {
                phoneNumbers.add(i);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String[] getNumbers() {
        String[] numbers = phoneNumbers.stream()
                .sorted()
                .toArray(String[]::new);
        return numbers;
    }

    @Override
    public String toString() {
        Collections.sort(phoneNumbers);
        StringBuilder str = new StringBuilder();
        str.append(name).append("\n").append(phoneNumbers.size()).append("\n");
        phoneNumbers.stream().forEach(n -> str.append(n).append("\n"));
        return str.toString();
    }

    static Contact valueOf(String s) throws InvalidFormatException, InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        String[] parts = s.split("\n");
        if (parts[0].length() <= 4 || parts[0].length() > 10) throw new InvalidFormatException();
        if (Integer.parseInt(parts[1]) > 5) throw new InvalidFormatException();
        String numbers = null;
        for (int i = 2; i < parts.length; i++) {
            if (parts[i].length() != 9 || checkNumber(parts[i])) throw new InvalidFormatException();
            numbers += parts[i];
            numbers += " ";
        }
        return new Contact(parts[0], Arrays.copyOfRange(parts, 2, parts.length));
    }

}


class PhoneBook {
    TreeMap<String, Contact> contacts;
    static long MAX_SIZE = 250;

    public PhoneBook() {
        this.contacts = new TreeMap<>();
    }

    public static void saveAsTextFile(PhoneBook phonebook, String textFile) {

    }

    public static PhoneBook loadFromTextFile(String textFile) {
        return null;
    }


    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
        if (contacts.size() > MAX_SIZE) throw new MaximumSizeExceddedException();
        if (contacts.containsKey(contact.getName())) throw new InvalidNameException(contact.getName());
        contacts.put(contact.getName(), contact);
    }

    public int numberOfContacts() {
        return contacts.size();
    }

    public Contact getContactForName(String name) {
        Contact c = contacts.get(name);
        return c;
    }

    public Contact[] getContacts() {
        return contacts.values().toArray(new Contact[0]);
    }

    public boolean removeContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        contacts.entrySet().stream().forEach(c -> str.append(c.getValue()).append("\n"));
        return str.toString();
    }

    public Contact[] getContactsForNumber(String number) {
        List<Contact> filtered = contacts.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(contact -> contact.phoneNumbers.stream().anyMatch(n -> n.contains(number))).collect(Collectors.toList());
        return filtered.toArray(new Contact[0]);
    }
}


public class PhonebookTester {

    public static void main(String[] args) throws Exception {
        Scanner jin = new Scanner(System.in);
        String line = jin.nextLine();
        switch (line) {
            case "test_contact":
                testContact(jin);
                break;
            case "test_phonebook_exceptions":
                testPhonebookExceptions(jin);
                break;
            case "test_usage":
                testUsage(jin);
                break;
        }
    }

    private static void testFile(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while (jin.hasNextLine())
            phonebook.addContact(new Contact(jin.nextLine(), jin.nextLine().split("\\s++")));
        String text_file = "phonebook.txt";
        PhoneBook.saveAsTextFile(phonebook, text_file);
        PhoneBook pb = PhoneBook.loadFromTextFile(text_file);
        if (!pb.equals(phonebook)) System.out.println("Your file saving and loading doesn't seem to work right");
        else System.out.println("Your file saving and loading works great. Good job!");
    }

    private static void testUsage(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while (jin.hasNextLine()) {
            String command = jin.nextLine();
            switch (command) {
                case "add":
                    phonebook.addContact(new Contact(jin.nextLine(), jin.nextLine().split("\\s++")));
                    break;
                case "remove":
                    phonebook.removeContact(jin.nextLine());
                    break;
                case "print":
                    System.out.println(phonebook.numberOfContacts());
                    System.out.println(Arrays.toString(phonebook.getContacts()));
                    System.out.println(phonebook.toString());
                    break;
                case "get_name":
                    System.out.println(phonebook.getContactForName(jin.nextLine()));
                    break;
                case "get_number":
                    System.out.println(Arrays.toString(phonebook.getContactsForNumber(jin.nextLine())));
                    break;
            }
        }
    }

    private static void testPhonebookExceptions(Scanner jin) {
        PhoneBook phonebook = new PhoneBook();
        boolean exception_thrown = false;
        try {
            while (jin.hasNextLine()) {
                phonebook.addContact(new Contact(jin.nextLine()));
            }
        } catch (InvalidNameException e) {
            System.out.println(e.name);
            exception_thrown = true;
        } catch (Exception e) {
        }
        if (!exception_thrown) System.out.println("Your addContact method doesn't throw InvalidNameException");
        /*
		exception_thrown = false;
		try {
		phonebook.addContact(new Contact(jin.nextLine()));
		} catch ( MaximumSizeExceddedException e ) {
			exception_thrown = true;
		}
		catch ( Exception e ) {}
		if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw MaximumSizeExcededException");
        */
    }

    private static void testContact(Scanner jin) throws Exception {
        boolean exception_thrown = true;
        String names_to_test[] = {"And\nrej", "asd", "AAAAAAAAAAAAAAAAAAAAAA", "Ð�Ð½Ð´Ñ€ÐµÑ˜A123213", "Andrej#", "Andrej<3"};
        for (String name : names_to_test) {
            try {
                new Contact(name);
                exception_thrown = false;
            } catch (InvalidNameException e) {
                exception_thrown = true;
            }
            if (!exception_thrown) System.out.println("Your Contact constructor doesn't throw an InvalidNameException");
        }
        String numbers_to_test[] = {"+071718028", "number", "078asdasdasd", "070asdqwe", "070a56798", "07045678a", "123456789", "074456798", "073456798", "079456798"};
        for (String number : numbers_to_test) {
            try {
                new Contact("Andrej", number);
                exception_thrown = false;
            } catch (InvalidNumberException e) {
                exception_thrown = true;
            }
            if (!exception_thrown)
                System.out.println("Your Contact constructor doesn't throw an InvalidNumberException");
        }
        String nums[] = new String[10];
        for (int i = 0; i < nums.length; ++i) nums[i] = getRandomLegitNumber();
        try {
            new Contact("Andrej", nums);
            exception_thrown = false;
        } catch (MaximumSizeExceddedException e) {
            exception_thrown = true;
        }
        if (!exception_thrown)
            System.out.println("Your Contact constructor doesn't throw a MaximumSizeExceddedException");
        Random rnd = new Random(5);
        Contact contact = new Contact("Andrej", getRandomLegitNumber(rnd), getRandomLegitNumber(rnd), getRandomLegitNumber(rnd));
        System.out.println(contact.getName());
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
    }

    static String[] legit_prefixes = {"070", "071", "072", "075", "076", "077", "078"};
    static Random rnd = new Random();

    private static String getRandomLegitNumber() {
        return getRandomLegitNumber(rnd);
    }

    private static String getRandomLegitNumber(Random rnd) {
        StringBuilder sb = new StringBuilder(legit_prefixes[rnd.nextInt(legit_prefixes.length)]);
        for (int i = 3; i < 9; ++i)
            sb.append(rnd.nextInt(10));
        return sb.toString();
    }


}
