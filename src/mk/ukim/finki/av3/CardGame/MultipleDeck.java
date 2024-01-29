package mk.ukim.finki.av3.CardGame;


import java.util.Arrays;

public class MultipleDeck {
    private Deck[] decks;

    public MultipleDeck(int n) {
        decks = new Deck[n];
        for (int i = 0; i < n; i++) {
            decks[i] = new Deck();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < decks.length; i++) {
            res.append(decks[i]).append("\n\n");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        MultipleDeck md = new MultipleDeck(3);
        System.out.println(md);
    }
}