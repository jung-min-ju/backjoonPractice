import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int [][][] dp;
    static char [][] house;
    static int [] dirX = {0,0,-1,1};
    static int [] dirY = {-1,1,0,0};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[4][N][N];
        house = new char[N][N];
        int startX=0;
        int startY=0;

        for(int i=0; i<N; i++){
            String s = br.readLine();
            house[i]=s.toCharArray();
            for(int j=0; j<N; j++){
                if(house[i][j]=='#'){
                    startX=i;
                    startY=j;
                }
            }
        }

        // DP 배열을 최댓값으로 초기화
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=0; i<4; i++){
            int nextX = startX+dirX[i];
            int nextY = startY+dirY[i];

            if(nextX<0||nextY<0||nextX>=N||nextY>=N)continue;
            if(house[nextX][nextY]=='*')continue;

            dp[i][nextX][nextY] = 0;
            queue.add(new int[]{nextX, nextY, i, 0}); // (x, y, 방향, 거울 개수)
        }

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowDir = now[2];
            int nowMirror = now[3];
            int reverseDir = (nowDir+2)%4;
            if (house[nowX][nowY] == '#') {
                answer = Math.min(answer, nowMirror);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dirX[i];
                int nextY = nowY + dirY[i];

                if(i==reverseDir) continue;
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                if (house[nextX][nextY] == '*') continue;
                if(house[nowX][nowY]=='!'){
                    if(i==nowDir) queue.add(new int[]{nextX, nextY, i, nowMirror}); // (x, y, 방향, 거울 개수)
                    else  queue.add(new int[]{nextX, nextY, i, nowMirror+1}); // (x, y, 방향, 거울 개수)
                }

                if(house[nowX][nowY]=='.'&&i==nowDir) {
                    queue.add(new int[]{nextX, nextY, i, nowMirror}); // (x, y, 방향, 거울 개수)
                }
            }
        }

        System.out.println(answer);
    }

}