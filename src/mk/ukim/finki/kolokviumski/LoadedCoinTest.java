package mk.ukim.finki.kolokviumski;

import java.util.Random;
import java.util.Scanner;


public class LoadedCoinTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int probability = scanner.nextInt();
        Coin c = new Coin();
        int heads = 0;
        int n = 1000;
        for (int i = 0; i < n; i++) {
            SIDE side = c.flip();
            if (side == SIDE.HEAD) {
                heads++;
            }
        }
        if (heads > 450 && heads < 550) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        c = new LoadedCoin(probability);
        heads = 0;
        for (int i = 0; i < n; i++) {
            SIDE side = c.flip();
            if (side == SIDE.HEAD) {
                heads++;
            }
        }
        if (heads > probability * 10 - 50 && heads < probability * 10 + 50) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

enum SIDE {
    HEAD, TAIL
}

class Coin {

    SIDE side;

    public SIDE flip() {
        Random random = new Random();
        boolean isHead = random.nextBoolean();
        if (isHead) {
            return SIDE.HEAD;
        } else {
            return SIDE.TAIL;
        }
    }
}

class LoadedCoin extends Coin {
    int prob;

    public LoadedCoin(int prob) {
        this.prob = prob;
    }

    @Override
    public SIDE flip() {
        Random random = new Random();
        boolean isHead = random.nextInt(100) < prob;
        if (isHead) {
            return SIDE.HEAD;
        } else {
            return SIDE.TAIL;
        }
    }
}
