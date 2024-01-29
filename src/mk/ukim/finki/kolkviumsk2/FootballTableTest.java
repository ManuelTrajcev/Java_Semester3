package mk.ukim.finki.kolkviumsk2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */

class Team implements Comparable<Team> {
    String name;
    int goalsScored;
    int goalsTaken;
    int wins;
    int loses;
    int drafts;
    int points;

    public Team(String name) {
        this.name = name;
        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.wins = 0;
        this.loses = 0;
        this.drafts = 0;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }

    public int getGoalsTaken() {
        return goalsTaken;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        this.points = wins * 3 + drafts;
    }

    public void setGoalsTaken(int goalsTaken) {
        this.goalsTaken += goalsTaken;
    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        this.wins++;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses() {
        this.loses++;
    }

    public int getDrafts() {
        return drafts;
    }

    public void setDrafts() {
        this.drafts++;
    }

    public int goalDifference() {
        return goalsScored - goalsTaken;
    }

    public static Comparator<Team> getComparator() {
        return Comparator.comparing(Team::getPoints).thenComparing(Team::goalDifference).reversed().thenComparing(Team::getName);
    }

    @Override
    public int compareTo(Team o) {
        return getComparator().compare(this, o);
    }

    public int getTotal() {
        return wins + loses + drafts;
    }
}

class FootballTable {
    LinkedHashMap<String, Team> teams;

    public FootballTable() {
        this.teams = new LinkedHashMap<>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        if (teams.containsKey(homeTeam)) {
            Team home = teams.get(homeTeam);
            home.setGoalsScored(homeGoals);
            home.setGoalsTaken(awayGoals);
            if (homeGoals > awayGoals) home.setWins();
            else if (homeGoals < awayGoals) home.setLoses();
            else home.setDrafts();
            home.setPoints();
        } else {
            Team home = new Team(homeTeam);
            home.setGoalsScored(homeGoals);
            home.setGoalsTaken(awayGoals);
            if (homeGoals > awayGoals) home.setWins();
            else if (homeGoals < awayGoals) home.setLoses();
            else home.setDrafts();
            home.setPoints();
            teams.put(homeTeam, home);
        }

        if (teams.containsKey(awayTeam)) {
            Team away = teams.get(awayTeam);
            away.setGoalsScored(awayGoals);
            away.setGoalsTaken(homeGoals);
            if (homeGoals < awayGoals) away.setWins();
            else if (homeGoals > awayGoals) away.setLoses();
            else away.setDrafts();
            away.setPoints();
        } else {
            Team away = new Team(awayTeam);
            away.setGoalsScored(awayGoals);
            away.setGoalsTaken(homeGoals);
            if (homeGoals < awayGoals) away.setWins();
            else if (homeGoals > awayGoals) away.setLoses();
            else away.setDrafts();
            away.setPoints();
            teams.put(awayTeam, away);
        }

    }

    public void printTable() {
        List<Team> sorted = teams.values().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (int i = 0; i < sorted.size(); i++) {
            Team curr = sorted.get(i);
            System.out.printf("%2d. %-15s%5d%5d%5d%5d%5d\n", i + 1, curr.name, curr.getTotal(), curr.getWins(), curr.getDrafts(), curr.getLoses(), curr.getPoints());
        }
    }
}

public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here

