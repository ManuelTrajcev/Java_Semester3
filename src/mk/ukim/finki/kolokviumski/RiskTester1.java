package mk.ukim.finki.kolokviumski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RiskTester1 {

    public static class Round {
        List<Integer> attacker;
        List<Integer> defender;

        public Round(String line) {
            String[] parts = line.split(";");
            this.attacker = parseDice(parts[0]);
            this.defender = parseDice(parts[1]);

//            Collections.sort(this.attacker);
//            Collections.sort(this.defender);
        }

        private List<Integer> parseDice(String input) {
            return Arrays.stream(input.split("\\s+"))
                    .map(dice -> Integer.parseInt(dice))
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Round{" +
                    "attacker=" + attacker +
                    ", defender=" + defender +
                    '}';
        }

        public boolean hasWon() {
            return IntStream.range(0, attacker.size())
                    .allMatch(i -> attacker.get(i) > defender.get(i));

//            for (int i = 0; i < 3; i++) {
//                if (attacker.get(i) <= defender.get(i)) {
//                    return false;
//                }
//            }
//            return true;
        }
    }

    public static class Risk {

        public Risk() {
        }

        public int processAttacksData(InputStream is) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            List<Round> rounds = br.lines()
                    .map(line -> new Round(line))
                    .collect(Collectors.toList());


            br.close();

            return (int) rounds.stream()
                    .filter(round -> round.hasWon())
                    .count();

        }

    }

    public static void main(String[] args) {

        Risk risk = new Risk();

        try {
            System.out.println(risk.processAttacksData(System.in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
