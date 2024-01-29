package algoritmi.labs.lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        int max = 0;
        int counter = 1;
        for (int i = 0; i < a.length - 1; i++) {
            counter = 1;
            if ((a[i] > 0 && a[i + 1] < 0) || (a[i] < 0 && a[i + 1] > 0)) {
                counter++;
                while (true) {
                    i++;
                    if (i == a.length - 1) {
                        break;
                    }
                    if ((a[i] > 0 && a[i + 1] < 0) || (a[i] < 0 && a[i + 1] > 0)) {
                        counter++;
                    } else {
                        break;
                    }
                }
            }
            if (counter > max) {
                max = counter;
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
