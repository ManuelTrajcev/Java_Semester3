package algoritmi.prvKolokvium;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {
    static int solve(int numbers[], int n, int k) {
        int[][] dp = new int[n][k + 1];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                for (int l = 0; l < i && j > 0; l++) {
                    dp[i][j] = Math.max(dp[i][j], dp[l][j - 1] + Math.abs(numbers[i] - numbers[l]));
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i-1][j];
                for (int l = 0; l < i && j > 0; l++) {
                    dp[i][j] = Math.max(dp[i][j], dp[l][j-1] + Math.abs(numbers[i]-numbers[i-1]));
                }
            }
        }

        return dp[n - 1][k - 1];
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
