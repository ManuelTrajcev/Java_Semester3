package mk.ukim.finki.av3.CardGame;

import java.util.Objects;

public class PlayingCard {
    private PlayingCardType type;
    private int number;


    public PlayingCard(PlayingCardType type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%d %s", number, type.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return number == that.number && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }
}