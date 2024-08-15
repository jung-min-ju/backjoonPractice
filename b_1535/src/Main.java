import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [] hp;
    static int [] joy;
    static int N;
    static int dp [];

    public static void main(String[] args) throws IOException {
        init();

        for(int i=1; i<=N; i++){ //사람 수 체크
            for(int j=99; j>=0; j--){
                //1. 현재 j가 봐야할 체력수보다 큰 경우
                if(j>=hp[i]) {
                    dp[j] = Math.max(dp[j], dp[j-hp[i]] + joy[i]); // 현재 체력 뺀 경우 vs 안 뺀 경우
                }
            }
        }
        System.out.println(dp[99]);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stHP, stJOY;

        N = Integer.parseInt(br.readLine());
        hp = new int[N+1];
        joy = new int[N+1];
        dp = new int [100];

        stHP = new StringTokenizer(br.readLine(), " ");
        stJOY = new StringTokenizer(br.readLine(), " ");

        for(int i=1; i<=N; i++){
            hp[i] = Integer.parseInt(stHP.nextToken());
            joy[i] = Integer.parseInt(stJOY.nextToken());
        }

    }

}