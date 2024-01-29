package mk.ukim.finki.lab8;

import java.util.ArrayList;
import java.util.List;

interface ActionStrategy {
    void pressButton(List<Song> songs, int curr);
}

class PlaySong implements ActionStrategy {
    @Override
    public void pressButton(List<Song> songs, int curr) {
        System.out.printf("Song %d is playing\n", curr);
    }
}

class StopSong implements ActionStrategy {
    @Override
    public void pressButton(List<Song> songs, int curr) {
        System.out.printf("Song %d is paused\n", curr);
    }
}

class FWDSong implements ActionStrategy {
    @Override
    public void pressButton(List<Song> songs, int curr) {
        System.out.println("Forward...");
    }
}

class REWSong implements ActionStrategy {
    @Override
    public void pressButton(List<Song> songs, int curr) {
        System.out.println("Reward...");
    }
}

class Song {
    String name;
    String author;

    public Song(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title=" + name +
                ", artist=" + author +
                '}';
    }
}

class MP3Player {
    List<Song> songs;
    int curr;
    ActionStrategy actionStrategy;
    static boolean isStopped = false;

    public MP3Player(List<Song> songs) {
        this.songs = songs;
        this.curr = 0;
        this.actionStrategy = new StopSong();
    }

    public void pressPlay() {
        isStopped = false;
        if (actionStrategy instanceof PlaySong) {
            System.out.println("Song is already playing");
        } else {
            actionStrategy = new PlaySong();
            actionStrategy.pressButton(songs, curr);
        }
    }

    public void printCurrentSong() {
        System.out.println(songs.get(curr));
    }

    public void pressStop() {
        if (actionStrategy instanceof StopSong) {
            curr = 0;
            if (isStopped) {
                System.out.println("Songs are already stopped");
            } else {
                System.out.println("Songs are stopped");
                isStopped = true;
            }

        } else {
            actionStrategy = new StopSong();
            actionStrategy.pressButton(songs, curr);
        }
    }

    public void pressFWD() {
        if (curr + 1 == songs.size()) {
            curr = 0;
        } else {
            curr++;
        }
        actionStrategy = new FWDSong();
        actionStrategy.pressButton(songs, curr);
        actionStrategy = new StopSong();
    }

    public void pressREW() {
        if (curr == 0) {
            curr = songs.size() - 1;
        } else {
            curr--;
        }
        actionStrategy = new REWSong();
        actionStrategy.pressButton(songs, curr);
        actionStrategy = new StopSong();
    }

    @Override
    public String toString() {
        actionStrategy = new StopSong();
        return "MP3Player{" +
                "currentSong = " + curr +
                ", songList = " + songs +
                '}';
    }
}

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

//Vasiot kod ovde