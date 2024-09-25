import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringBuilder sb1;
    static StringBuilder sb2;
    static int input[];
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N+1];
        dp = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int count = Integer.parseInt(br.readLine());

        int index=0;
        for(int i=0; i<count; i++){

        }


    }

}