import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] a;
    static int[] dp;
    static int N;
    static int max = 1;  // 최소 수열 길이는 1이므로 초기값을 1로 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        a = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            max = Math.max(max, findLIS(i));
        }

        System.out.println(max);
    }

    static int findLIS(int n) {
        if (dp[n] > 0) {
            return dp[n];  // 이미 계산된 경우, 그 값을 반환
        }

        dp[n] = 1;  // 최소 길이는 1
        for (int i = 0; i < n; i++) {
            if (a[n] > a[i]) {
                dp[n] = Math.max(dp[n], findLIS(i) + 1);
            }
        }

        return dp[n];
    }
}
