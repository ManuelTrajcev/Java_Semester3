package mk.ukim.finki.av3.CardGame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Deck {
    private PlayingCard[] cards;
    private int total;
    private boolean[] picked;
    public Deck() {
        picked = new boolean[52];
        total = 0;
        cards = new PlayingCard[52];
        for (int i = 0; i < PlayingCardType.values().length; i++) {
            for (int j = 0; j < 13; j++) {
                cards[j + (13*i)] = new PlayingCard(PlayingCardType.values()[i], j+1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            stringBuilder.append(cards[i]).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Arrays.equals(this.cards, ((Deck) o).cards);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(total);
        result = 31 * result + Arrays.hashCode(cards);
        return result;
    }

    boolean hasCardsLeft() {
        if (cards.length - total > 0)
            return true;
        else
            return false;
    }

    public PlayingCard dealCard() {
        if (!hasCardsLeft()){
            return null;
        }
        if (total == 52) return null;
        int card = (int) (Math.random()*52);
        if (!picked[card]){
            total++;
            picked[card] = true;
            return cards[card];
        }
        return dealCard();
    }

    public PlayingCard[] shuffle() {
        List<PlayingCard> playingCardList = Arrays.asList(cards);
        Collections.shuffle(playingCardList);
        return cards;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        deck.dealCard();
    }
}