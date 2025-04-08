import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int [][] height;
    static long [][] roadCount;
    static int dirX [] = {0,0,1,-1};
    static int dirY [] = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        height = new int[N][M];
        roadCount = new long[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                height[i][j]=Integer.parseInt(st.nextToken());
                roadCount[i][j] = -1;
            }
        }

        roadCount[0][0]=dfs(0, 0);
        System.out.println(roadCount[0][0]);
    }

    static long dfs(int x, int y){
        if(x==N-1 && y==M-1) {
            return 1;
        }

        if (roadCount[x][y] != -1) return roadCount[x][y];
        roadCount[x][y] = 0;

        for(int i=0; i<4; i++){
            int nextX = x+dirX[i];
            int nextY = y+dirY[i];

            if(nextX<0 || nextY <0 || nextX>=N || nextY>=M) continue;
            if(height[nextX][nextY]>=height[x][y]) continue;
            roadCount[x][y]+=dfs(nextX, nextY);
        }

        return roadCount[x][y];
    }
}