 package mk.ukim.finki.kolokviumski;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class NonExistingItemException extends Exception {
    public NonExistingItemException(String message) {
        super(message);
    }
}

class Archive {
    int id;
    LocalDate dateArchived;
    boolean locked;
    int currOpen;

    public Archive(int id) {
        this.id = id;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateArchived() {
        return dateArchived;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setCurrOpen(int currOpen) {
        this.currOpen = currOpen;
    }

    public int getCurrOpen() {
        return currOpen;
    }
}

class LockedArchive extends Archive {
    LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
        locked = true;
    }

    public LocalDate getDateToOpen() {
        return dateToOpen;
    }


}

class SpecialArchive extends Archive {
    int maxOpen;


    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        locked = false;
        currOpen = maxOpen;
    }

    public int getMaxOpen() {
        return maxOpen;
    }


}

class ArchiveStore {
    List<Archive> archiveList;
    StringBuilder log;

    public ArchiveStore() {
        archiveList = new ArrayList<>();
        log = new StringBuilder();
    }

    void archiveItem(Archive item, LocalDate date) {
        item.setDateArchived(date);
        archiveList.add(item);
        String str = String.format("Item %d archived at ", item.getId()) + date + "\n";
        log.append(str);
    }

    void openItem(int id, LocalDate date) throws NonExistingItemException {
        Archive ar = null;
        for (Archive a : archiveList) {
            if (a.getId() == id) {
                ar = a;
            }
        }
        if (ar == null) {
            throw new NonExistingItemException(String.format("Item with id %d doesn't exist", id));
        }
        if (ar.isLocked()) {
            LockedArchive la = (LockedArchive) ar;
            if (la.dateToOpen.isAfter(date)) {
                log.append(String.format("Item %d cannot be opened before %s\n", id, la.dateToOpen));
            } else {
                log.append(String.format("Item %d opened at %s\n", id, date));
            }
        } else {
            SpecialArchive sr = (SpecialArchive) ar;
            if (ar.getCurrOpen() == 0) {
                log.append(String.format("Item %d cannot be opened more than %d times\n", id, sr.getMaxOpen()));
            } else {
                log.append(String.format("Item %d opened at %s\n", id, date));
                ar.setCurrOpen(ar.getCurrOpen() - 1);
            }
        }


    }

    String getLog() {
        return log.toString();
    }
}

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while (scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch (NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}