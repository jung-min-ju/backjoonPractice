import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N;
        int K;
        int answer[];
        int time[];
        int importance[];

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        answer = new int[N+1];
        importance = new int[K];
        time = new int[K];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            importance[i] = Integer.parseInt(st.nextToken());
            time[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<K; i++) {
            for(int j=1; j<=N; j++) {
                if(j-time[i] < 0) continue;
                answer[j] = Math.max(answer[j], answer[j-time[i]]+importance[i]);
            }
        }

        System.out.println(answer[N]);
    }
}