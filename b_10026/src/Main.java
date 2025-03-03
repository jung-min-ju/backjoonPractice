import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N;
    static int [] answer = new int[3]; //R==0, G==1, B==2
    static char [][] colorPan;
    static Queue<Info> queue = new ArrayDeque<>();
    static int [] dirX = {0,0,1,-1};
    static int [] dirY = {-1,1,0,0};

    static class Info {
        int x;
        int y;
        int colorDex;
        char color;

        public Info(int x, int y, char color){
            this.x=x;
            this.y=y;
            this.color=color;
            this.colorDex =changeColor(color);
        }

        private int changeColor(char color){
            if(color=='R') return 0;
            else if(color=='G') return 1;
            else return 2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        colorPan = new char[N][N];

        for(int i=0; i<N ; i++) {
            String s = br.readLine();
            for(int j=0; j<N; j++){
                colorPan[i][j] = s.charAt(j);
            }
        }


        queue.add(new Info(0,0, colorPan[0][0]));

        while (!queue.isEmpty()){
            Info next = queue.poll();
            answer[next.colorDex]++;
            dfs(next.x, next.y, next.color);
        }

        System.out.println("정답?");
        System.out.println(answer[0]);
        System.out.println(answer[1]);
        System.out.println(answer[2]);
    }

    static void dfs(int x, int y, char color){
        for(int i=0; i<4; i++){
            int nextX = x+dirX[i];
            int nextY = y+dirY[i];
            if(nextX>=0 && nextX<N && nextY>=0 && nextY<N) {
                if(colorPan[nextX][nextY]!=color && colorPan[nextX][nextY]!='0' && colorPan[nextX][nextY]!='1' ) queue.add(new Info(nextX, nextY, colorPan[nextX][nextY]));
                else {
                    if(color=='R'||color=='G') colorPan[nextX][nextY]='0';
                    else colorPan[nextX][nextY]='1';
                    dfs(nextX, nextY, color);
                }
            }
        }
    }
}