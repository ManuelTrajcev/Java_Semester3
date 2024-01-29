package algoritmi.prvKolokvium;

import java.util.Map;
import java.util.Scanner;



//Колку најмалку пати треба да одземеме квадрат на цел број за некој број Х да стане 0?
//
//        Влез: Цел број Х од 1 до 10^5.
//
//        Излез: Бараниот резултат.
//
//        Примери:
//
//
//        Влез	Излез	Објаснување
//        13
//        2
//        13 - 2*2  = 9 ;   9 - 3*3  = 0. (одземавме 2 пати)
//        21
//        3
//        21 - 2*2  = 17;  17 - 4*4 = 1;  1 - 1*1 = 0. (одземавме 3 пати)
//        25
//        1
//        25 - 5*5 = 0 . (одземавме 1 пат)
//        32
//        16
//        32 - 4*4 = 16;  16 - 4*4 = 0. (одземавме 2 пати)
//

public class Task3 {

    public static int count(int x) {
        int copy = x;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < copy / 2; i++) {
            int counter = 0;
            x = copy;
            double first = Math.sqrt(copy);
            first = Math.floor(first);
            first -= i;

            while (x > 0) {
                if (first < 0) {
                    counter = Integer.MAX_VALUE;
                    break;
                }
                if (first * first > x) {
                    first--;
                    continue;
                }
                x -= (first * first);
                counter++;
                if ((first*first) == x){
                    continue;
                }
                if (first * first > x) {
                    continue;
                }
                first--;
                if (first == 0 && x > 0){
                    first = 1;
                }
            }
            if (counter == 1) {
                return 1;
            }
            if (counter < min) {
                min = counter;
            }
        }


        return min;

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int result;

        // vashiot kod ovde / your code goes here
        result = count(X);
        System.out.println(result);
    }
}
