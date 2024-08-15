import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int totalTime;
    static int dp [];
    static int studyTime [];
    static int point [];

    public static void main(String[] args) throws IOException{
        init();

        for(int i=1; i<=n; i++) {
            for(int j=totalTime; j>=studyTime[i]; j--){
                dp[j] = Math.max(dp[j], (dp[j-studyTime[i]] + point[i]));
            }
        }

        System.out.println(dp[totalTime]);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        totalTime = Integer.parseInt(st.nextToken());

        dp = new int[totalTime+1];
        studyTime = new int [n+1];
        point = new int[n+1];

        for(int i=1; i<=n; i++){
            st=new StringTokenizer(br.readLine(), " ");
            studyTime[i] = Integer.parseInt(st.nextToken());
            point[i] = Integer.parseInt(st.nextToken());
        }

    }
}