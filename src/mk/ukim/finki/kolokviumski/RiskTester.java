package mk.ukim.finki.kolokviumski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Round {
    List<Integer> attacker;
    List<Integer> defender;

    public Round(List<Integer> attacker, List<Integer> defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public boolean hasWon() {
        for (int i = 0; i < attacker.size(); i++) {
            if (attacker.get(i) <= defender.get(i)) {
                return false;
            }
        }
        return true;
    }
}

class Risk {
    List<Round> rounds;

    public Risk() {
        this.rounds = new ArrayList<>();
    }


    Round roundFactory(String line) {
        String[] parts = line.split(";");
        String[] attacker = parts[0].split("\\s+");
        String[] defender = parts[1].split("\\s+");
        List<Integer> attack = new ArrayList<>();
        List<Integer> deff = new ArrayList<>();
        Arrays.stream(attacker).forEach(a -> attack.add(Integer.parseInt(a)));
        Arrays.stream(defender).forEach(a -> deff.add(Integer.parseInt(a)));
        Collections.sort(attack);
        Collections.sort(deff);
        return new Round(attack, deff);
    }

    int processAttacksData(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        br.lines().forEach(l -> rounds.add(roundFactory(l)));
        return (int) rounds.stream().filter(r -> r.hasWon()).count();
    }
}

public class RiskTester {
    public static void main(String[] args) {

        Risk risk = new Risk();

        System.out.println(risk.processAttacksData(System.in));

    }
}