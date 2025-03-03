import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char room [][];
    static int dirX[] = {-1,0,1,0}; //방향은 인덱스 1부터 계산
    static int dirY[] = {0,1,0,-1};
    static int answer = Integer.MAX_VALUE;
    static boolean [][][] minRoute;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        room = new char[N+2][N+2];
        minRoute = new boolean[4][N+2][N+2];

        int startX=0;
        int startY=0;
        for(int i=1; i<=N; i++) {
            String s = br.readLine();
            for(int j=1; j<=N; j++) {
                room[i][j] = s.charAt(j-1);
                if(room[i][j]=='#'){
                    startX=i;
                    startY=j;
                }
            }
        }

        room[startX][startY]='*';
        findMirror(startX, startY,0,-1);
        System.out.println(answer);
    }

    static void findMirror(int x, int y, int count, int exDir) { //# 찾았으면 해당 카운트 return, # 못 찾았으면 -1 return
        if(room[x][y]=='#') {
            answer = Math.min(answer, count);
        }

        for(int i=0; i<4; i++) {
            int nextX=x+dirX[i];
            int nextY=y+dirY[i];
            if(room[nextX][nextY]=='*'||room[nextX][nextY]=='\0'||minRoute[i][x][y]==true) continue;
            minRoute[i][x][y]=true;
            if(room[nextX][nextY]=='!') {
                if(i!=exDir) findMirror(nextX, nextY,count+1, i);
                else findMirror(nextX, nextY,count, i);
            }
            else if(exDir==-1||exDir==i) findMirror(nextX, nextY, count, i);
            minRoute[i][x][y]=false;
        }
    }
}