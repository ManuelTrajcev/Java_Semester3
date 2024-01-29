package algoritmi.prvKolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class card_trick {
    public static int count(int N) {
        Queue<Integer> spil = new ArrayDeque<>();
        Stack<Integer> tmp = new Stack<>();
        for (int i = 0; i < 51; i++) {
            spil.add(i + 1);
        }

        int br = 0;

        while (spil.peek() != N) {
            br++;
            for (int i = 0; i < 7; i++) {
                tmp.push(spil.peek());
                spil.remove();
            }
            for (int i = 0; i < 7; i++) {
                spil.add(tmp.pop());
                spil.add(spil.peek());
                spil.remove();
            }
        }

        return br;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}

