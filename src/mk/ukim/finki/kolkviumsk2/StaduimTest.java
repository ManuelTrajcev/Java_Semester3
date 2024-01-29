package mk.ukim.finki.kolkviumsk2;

import java.util.*;

class SeatNotAllowedException extends Exception {
    public SeatNotAllowedException() {
        super();
    }
}

class SeatTakenException extends Exception {
    public SeatTakenException() {
        super();
    }
}

class Sector implements Comparable<Sector> {
    String code;
    int capacity;
    boolean[] isTaken;
    Integer seatType;

    public Sector(String code, int capacity) {
        this.code = code;
        this.capacity = capacity;
        isTaken = new boolean[capacity];
        seatType = 0;
    }

    public int freeSeats() {
        int counter = 0;
        for (boolean b : isTaken) {
            if (!b) counter++;
        }
        return counter;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Sector o) {
        int result = Float.compare(this.getPercentage(), o.getPercentage());
        if (result == 0) {
            result = this.getCode().compareTo(o.getCode());
        }
        return result;
    }

    public float getPercentage() {
        int free = freeSeats();
        int total = capacity;
        float percent = (float) free / total * 100;
        return 100 - percent;
    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%", code, freeSeats(), isTaken.length, getPercentage());
    }
}


class Stadium {
    String name;
    TreeMap<String, Sector> sectors;
    TreeSet<Sector> sectorsSet;

    public Stadium(String name) {
        this.name = name;
        sectors = new TreeMap<>();
        sectorsSet = new TreeSet<>();
    }

    public void createSectors(String[] sectorNames, int[] sectorSizes) {
        for (int i = 0; i < sectorSizes.length; i++) {
            sectors.put(sectorNames[i], new Sector(sectorNames[i], sectorSizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        if (sectors.get(sectorName).isTaken[seat - 1]) {
            throw new SeatTakenException();
        }
        if (type == 0) {
            sectors.get(sectorName).isTaken[seat - 1] = true;
        } else if (sectors.get(sectorName).seatType == 0 || sectors.get(sectorName).seatType == type) {
            sectors.get(sectorName).isTaken[seat - 1] = true;
            sectors.get(sectorName).seatType = type;
        } else {
            throw new SeatNotAllowedException();
        }
    }

    public void showSectors() {
        sectors.entrySet().stream().forEach(s -> sectorsSet.add(s.getValue()));
        sectorsSet.stream().forEach(s -> System.out.println(s));
    }
}

public class StaduimTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}
