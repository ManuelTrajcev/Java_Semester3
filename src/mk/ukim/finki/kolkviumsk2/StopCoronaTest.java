package mk.ukim.finki.kolkviumsk2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}

interface ILocation {
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();

    boolean isDirectLocation(List<ILocation> locations);

    double euclideanDistance(ILocation other);

    double timeDifference(ILocation other);
}

class Location implements ILocation {
    double longitude;
    double latitude;
    LocalDateTime timestamp;

    public Location(double longitude, double latitude, LocalDateTime timestamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isDirectLocation(List<ILocation> locations) {
        List<ILocation> list = locations.stream()
                .filter(l -> l.euclideanDistance(this) <= 2 && l.timeDifference(this) < 5)
                .collect(Collectors.toList());
        return !list.isEmpty();
    }

    @Override
    public double euclideanDistance(ILocation other) {
        return Math.sqrt(Math.pow(this.latitude - other.getLatitude(), 2) + Math.pow(this.longitude - other.getLongitude(), 2));
    }

    @Override
    public double timeDifference(ILocation other) {
        return Math.abs(this.timestamp.getMinute() - other.getTimestamp().getMinute());
    }

}

class User {

    String name;
    String id;
    List<ILocation> locations;
    LocalDateTime gotVirusTimeStamp;
    TreeMap<Integer, User> directContactsOfThis;


    public User(String name, String id) {
        this.name = name;
        this.id = id;
        locations = new ArrayList<>();
        gotVirusTimeStamp = null;
        directContactsOfThis = new TreeMap<>();
    }

    public void addLocations(List<ILocation> locations) {
        this.locations = locations;
    }

//    public void checkContact(User other) {
//        locations
//                .stream()
//                .forEach(loc -> {
//                    loc.checkAll(other.locations)
//                });
//    }


    public boolean isDirect(User other) {
        List<ILocation> list = locations.stream()
                .filter(l -> l.isDirectLocation(other.locations))
                .collect(Collectors.toList());

        return !list.isEmpty();
    }

    public int numOfDirect(User other) {
        return (int) locations.stream()
                .filter(l -> l.isDirectLocation(other.locations))
                .count();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s %s %s\n", name, id, gotVirusTimeStamp));
        str.append("Direct contacts:").append("\n");
        directContactsOfThis
                .entrySet()
                .forEach(dir -> {
                    str.append(dir.getValue().name).append(" ").append(dir.getValue().id, 0, 5).append("***").append(" ").append(dir.getKey()).append("\n");
                });
        return str.toString();
    }
}


class StopCoronaApp {
    HashMap<String, User> users;

    public StopCoronaApp() {
        this.users = new HashMap<>();
    }

    public void addUser(String name, String id) throws UserAlreadyExistException {
        if (users.containsKey(id)) throw new UserAlreadyExistException(id);

        users.put(id, new User(name, id));
    }

    public void addLocations(String id, List<ILocation> locations) {
        users.get(id).addLocations(locations);
    }

    public void detectNewCase(String id, LocalDateTime timestamp) {
        users.get(id).gotVirusTimeStamp = timestamp;
    }

    public void getDirectForAll(User u) {
        users
                .values()
                .stream().filter(user -> user.isDirect(u))
                .forEach(i -> u.directContactsOfThis.put(i.numOfDirect(i), i));
    }

    public void createReport() {
        users.entrySet().stream().filter(i->i.getValue().gotVirusTimeStamp!=null).forEach(user -> getDirectForAll(user.getValue()));
        List<User> infected = users
                .values()
                .stream()
                .filter(user -> user.gotVirusTimeStamp != null)
                .sorted(Comparator.comparing(i -> i.gotVirusTimeStamp))
                .collect(Collectors.toList());
        infected.forEach(System.out::println);

    }

    public Map<User, Integer> getDirectContacts(User u) {
        Map<User, Integer> contacts = new HashMap<>();
        List<User> directContacts = users
                .values()
                .stream().filter(user -> user.isDirect(u))
                .collect(Collectors.toList());
        directContacts
                .forEach(dir -> {
                    contacts.put(dir, u.numOfDirect(dir));
                    u.directContactsOfThis.put(u.numOfDirect(dir), dir);
                });

        return contacts;
    }

    public Collection<User> getIndirectContacts(User u) {
        return null;
    }
}

public class StopCoronaTest {

    public static double timeBetweenInSeconds(ILocation location1, ILocation location2) {
        return Math.abs(Duration.between(location1.getTimestamp(), location2.getTimestamp()).getSeconds());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StopCoronaApp stopCoronaApp = new StopCoronaApp();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            switch (parts[0]) {
                case "REG": //register
                    String name = parts[1];
                    String id = parts[2];
                    try {
                        stopCoronaApp.addUser(name, id);
                    } catch (UserAlreadyExistException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "LOC": //add locations
                    id = parts[1];
                    List<ILocation> locations = new ArrayList<>();
                    for (int i = 2; i < parts.length; i += 3) {
                        locations.add(createLocationObject(parts[i], parts[i + 1], parts[i + 2]));
                    }
                    stopCoronaApp.addLocations(id, locations);

                    break;
                case "DET": //detect new cases
                    id = parts[1];
                    LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                    stopCoronaApp.detectNewCase(id, timestamp);

                    break;
                case "REP": //print report
                    stopCoronaApp.createReport();
                    break;
                default:
                    break;
            }
        }
    }

    private static ILocation createLocationObject(String lon, String lat, String timestamp) {
        double longitude = Double.parseDouble(lon);
        double latitude = Double.parseDouble(lat);
        LocalDateTime time = LocalDateTime.parse(timestamp);

        return new Location(longitude, latitude, time);
    }
}
