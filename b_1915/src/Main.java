import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [][] dp = new int[N+1][M+1];
        int [][] arr = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            String s = br.readLine();
            for(int j=1; j<=M; j++) {
                char c = s.charAt(j-1);
                arr[i][j]=Integer.parseInt(String.valueOf(c));
            }
        }

        int answer = 0;

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(arr[i][j]==0) {
                    dp[i][j]=0;
                    continue;
                }
                dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]))+1;
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer*answer);
    }
}