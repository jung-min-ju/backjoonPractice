import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int [][] time;
    static char [][] forest;
    static int [] dirX = {0,0,1,-1};
    static int [] dirY = {-1,1,0,0};
    static boolean find = false;

    static class Location {
        int x;
        int y;

        public Location(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        forest = new char[R][C];
        time = new int[R][C];

        Queue<Location> water = new ArrayDeque<>();

        int startR=0, startC=0, endR=0, endC=0;
        for(int i=0; i<R; i++) {
            String s = br.readLine();
            for(int j=0; j<C; j++) {
                char now = s.charAt(j);
                if(now=='S') {
                    startR=i;
                    startC=j;
                }
                if(now=='D') {
                    endR = i;
                    endC = j;
                }
                if(now=='*') {
                    forest[i][j]='X';
                    water.add(new Location(i, j));
                }
                forest[i][j] = now;
            }
        }

        Queue<Location> goseum = new ArrayDeque<>();
        goseum.add(new Location(startR, startC));

        while (!goseum.isEmpty()) {
            if(find) break;
            //물 흘려보내기
            int nowWaterCount = water.size();
            while (nowWaterCount --> 0) {
                Location nowWater = water.poll();
                for(int i=0; i<4; i++) {
                    int nextWaterX = nowWater.x + dirX[i];
                    int nextWaterY = nowWater.y + dirY[i];
                    if(canGo(nextWaterX, nextWaterY) && forest[nextWaterX][nextWaterY]!='D') {
                        forest[nextWaterX][nextWaterY] = 'X';
                        water.add(new Location(nextWaterX, nextWaterY));
                    }
                }
            }

            //고슴도치 이동시키기
            int nowGosuemount = goseum.size();
            while (nowGosuemount --> 0) {
                if(find) break;
                Location nowGosuem = goseum.poll();
                if(forest[nowGosuem.x][nowGosuem.y]=='G') continue;
                forest[nowGosuem.x][nowGosuem.y]='G';

                for(int i=0; i<4; i++) {
                    int nextGosuemX = nowGosuem.x + dirX[i];
                    int nextGosuemY = nowGosuem.y + dirY[i];

                    if(canGo(nextGosuemX, nextGosuemY)&&forest[nextGosuemX][nextGosuemY]!='G') {
                        time[nextGosuemX][nextGosuemY] = time[nowGosuem.x][nowGosuem.y]+1;
                        if(nextGosuemX==endR && nextGosuemY==endC) {
                            find=true;
                            break;
                        }
                        goseum.add(new Location(nextGosuemX, nextGosuemY));
                    }
                }
            }
        }

        System.out.println(time[endR][endC]!=0 ? time[endR][endC] : "KAKTUS");

    }

    static boolean canGo(int x, int y) {
        if(x<0||y<0||x>=R||y>=C) return false;
        if(forest[x][y]=='X') return false;
        return true;
    }
}