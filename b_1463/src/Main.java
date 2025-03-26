import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int dp [] = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N]=0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);

        while (true){
            int value = queue.poll();
            if(value==1)break;
            if(value%3==0) {
                queue.add(value/3);
                dp[value/3] = Math.min(dp[value]+1, dp[value/3]);
                if(value/3==1) break;
            }
            if(value%2==0) {
                queue.add(value/2);
                dp[value/2] = Math.min(dp[value]+1,  dp[value/2]);
                if(value/2==1) break;
            }
            queue.add(value-1);
            dp[value-1] = Math.min(dp[value]+1, dp[value-1]);
            if(value-1==1) break;
        }

        System.out.println(dp[1]);
    }
}