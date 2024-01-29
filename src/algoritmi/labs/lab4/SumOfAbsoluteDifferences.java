package algoritmi.labs.lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    static int solve(int numbers[], int n, int k) {
        int[][] tmp = new int[n][k];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < k; j++) {
                for (int l = 0; l < i; l++) {
                    if (tmp[l][j - 1] + Math.abs(numbers[i] - numbers[l]) > tmp[i][j])
                        tmp[i][j] = tmp[l][j - 1] + Math.abs(numbers[i] - numbers[l]);
                }
                if (tmp[i][j] > max)
                    max = tmp[i][j];
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}