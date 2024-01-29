package algoritmi.labs.lab3;

import java.util.Arrays;
import java.util.Scanner;

public class MiceHoles {

    //TODO: implement function

    public static int minTime(int mice[], int holes[]) {
//        int[] in = new int[mice.length];
//        int min = 0;
//        int maxSwap = 0;
//        boolean swaps = true;
//
//        for (int i = 0; i < in.length; i++) {
//            in[i] = 99999;
//        }
//
//        //in - niza kaj so sekoj glusec e na tocna dupka po index
//        Arrays.sort(mice);
//        Arrays.sort(holes);
//        for (int i = 0; i < mice.length; i++) {
//            int closest = 9999;
//            int closestIndex = 0;
//            for (int j = 0; j < mice.length; j++) {
//                if (Math.abs(mice[i] - holes[j]) < closest && in[j] == 99999) {
//                    closest = holes[i];
//                    closestIndex = i;
//                }
//            }
//            in[closestIndex] = closest;
//
//        }
//        for (int j = 0; j < mice.length; j++) {
//            if (Math.abs(mice[j] - in[j]) > maxSwap) {
//                maxSwap = Math.abs(mice[j] - in[j]);
//            }
//        }

        int maxSwap = 0;
        Arrays.sort(mice);
        Arrays.sort(holes);

        for (int i = 0; i < mice.length; i++) {
            if (Math.abs(mice[i] - holes[i]) > maxSwap) {
                maxSwap = Math.abs(mice[i] - holes[i]);
            }
        }



        return maxSwap;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String line = input.nextLine();
        String parts[] = line.split(" ");
        int mice[] = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            mice[i] = Integer.parseInt(parts[i]);
        }

        line = input.nextLine();
        parts = line.split(" ");
        int holes[] = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            holes[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(minTime(mice, holes));
    }
}

