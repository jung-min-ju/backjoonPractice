import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 100000;
        int N = Integer.parseInt(br.readLine());

        int dist[][] = new int[N+1][N+1];

        //첫 배열 초기화
        for(int i=1; i<=N; i++){
            String s = br.readLine();
            for(int j=1; j<=N; j++){
                int value = s.charAt(j-1) == 'Y' ? 1 : max ;
                dist[i][j] = value;
            }
        }
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(i==j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        int count = 0;

        for (int i = 1; i <= N ; i++) {
            int cnt = 0;
            for (int j = 1; j <= N ; j++) {
                if(dist[i][j] == 2 || dist[i][j] == 1){
                    cnt++;
                }
            }
            count = Math.max(count,cnt);
        }

        System.out.println(count);

    }
}