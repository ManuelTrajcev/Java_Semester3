package algoritmi.prvKolokvium;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {


    public static int getMin(int N, int M) {
        int sum = N * 100;
        int c = M;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = -1;
        }
        while (c > 0) {
            for (int i = 0; i < N; i++) {
                arr[i]++;
                c--;
                if (c == 0)
                    break;
            }
        }
        for (int i = 0; i < N; i++) {
            if(arr[i]!=-1)
                sum += arr[i] * 100;
        }
        return sum;
    }

    public static int getMax(int N, int M) {
        int sum = N * 100;
        if(M != 0)
            sum += ((M - 1) * 100);
        return sum;
    }


    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        System.out.println(getMin(N, M));
        System.out.println(getMax(N, M));


    }

}
