import java.io.*;
import java.util.*;

class Info {
    int m;
    int p;
    public Info(int m, int p) {
        this.m = m;
        this.p = p;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            infos.add(new Info(m, p));
        }

        int MAX = C + 100*100; // 넉넉히
        int[] dp = new int[MAX];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        dp[0] = 0;

        for (int i = 0; i < MAX; i++) {
            for (Info now : infos) {
                if (i + now.p < MAX) {
                    dp[i + now.p] = Math.min(dp[i + now.p], dp[i] + now.m);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = C; i < MAX; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
