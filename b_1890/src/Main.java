import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [][] pan = new int[N][N];
        long [][][] d = new long[2][N+1][N+1]; //0은 오른쪽 1은 아래

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                pan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        d[0][0][0] = 1;

        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0});

        while (!queue.isEmpty()){
            int [] now = queue.poll();
            int i = now[0];
            int j = now[1];
            int next = pan[i][j];
            if(pan[i][j]==0) continue;

            if(i+next<N) {
                d[1][i+next][j] += (d[0][i][j]+d[1][i][j]);
                queue.add(new int[]{i+next,j});
            }
            if(j+next<N) {
                d[0][i][j+next] += (d[0][i][j]+d[1][i][j]);
                queue.add(new int[]{i,j+next});
            }
        }
        System.out.println(d[0][N-1][N-1]+d[1][N-1][N-1]);
    }
}