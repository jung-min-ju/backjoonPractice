import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            //동전개수
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                coins[j] = Integer.parseInt(st.nextToken());
            }

            //금액 M
            int M = Integer.parseInt(br.readLine());
            int [] dp = new int[M+1];
            dp[0]=1;

            for(int j=0; j<N; j++){
                for(int k = coins[j]; k<=M; k++) {
                    dp[k] = dp[k] + dp[k-coins[j]];
                }
            }
            System.out.println(dp[M]);
        }

    }

}