import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean [][] map;
    static int answer = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new boolean[N+2][N+2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N+2; j++) {
                if(j==0 || j==N+1) {
                    map[i][j] = true;
                    continue;
                }
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        Arrays.fill(map[N+1], true);
        Arrays.fill(map[0], true);

        //가로 : 0, 세로 : 1, 대각선 : 2
        pipeMove(1,2, 0);
        System.out.println(answer);

    }

    static void pipeMove(int x, int y, int dir) {
        if(map[x][y]) return;
        if(x==N && y==N) {
            answer++;
            return;
        }

        if(dir==0) { //가로면 가로, 대각선
            pipeMove(x, y+1, 0);
            if(!checkGo(x, y)) return;
            pipeMove(x+1, y+1, 2);
        }
        else if (dir==1) { // 세로면 세로, 대각선
            pipeMove(x+1, y, 1);
            if(!checkGo(x, y)) return;
            pipeMove(x+1, y+1, 2);
        }
        else { //대각선 이면 가로, 세로 ,대각선
            pipeMove(x, y+1, 0);
            pipeMove(x+1, y, 1);
            if(!checkGo(x, y)) return;
            pipeMove(x+1, y+1, 2);
        }

    }

    static boolean checkGo(int x, int y){
        return (map[x+1][y]==false && map[x][y+1]==false && map[x+1][y+1]==false);
    }


}