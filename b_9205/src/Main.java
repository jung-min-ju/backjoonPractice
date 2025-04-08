import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Info {
        int x;
        int y;
        int beerCount;
        int roadCount;

        public Info(int x, int y, int b, int r) {
            this.x=x;
            this.y=y;
            this.beerCount = b;
            this.roadCount = r;
        }
    }

    static Queue<Info> queue;
    static int [][] road;
    static int dirX [] = {0,0,-1,1};
    static int dirY [] = {-1,1,0,0};
    static int maxX=0, maxY=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            queue = new ArrayDeque<>();
            int cs = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            //상근이 집 세팅
            queue.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 20, 0));

            Queue<Info> csLocation = new ArrayDeque<>();
            for(int j=0; j<cs; j++){ //편의점 세팅 -> 편의점 == -1로 표기
                st = new StringTokenizer(br.readLine());
                int csX = Integer.parseInt(st.nextToken());
                int csY = Integer.parseInt(st.nextToken());
                maxX = Math.max(maxX, csX);
                maxY = Math.max(maxY, csY);

                csLocation.add(new Info(csX, csY, 0,0));
            }

            st = new StringTokenizer(br.readLine());
            //페스티벌 좌표 표시 -> 페스티벌 == -2 로 표기
            int fX = Integer.parseInt(st.nextToken());
            int fY = Integer.parseInt(st.nextToken());

            maxX = Math.max(maxX, fX);
            maxY = Math.max(maxY, fY);

            road = new int[maxX+1][maxY+1];

            for(int j=0; j<cs; j++){ //편의점 세팅 -> 편의점 == -1로 표기
                Info nowCs = csLocation.poll();

                road[nowCs.x][nowCs.y] = -1;
            }
            road[fX][fY] = -2;

            bfs();
            System.out.println(bfs()==true? "happy" : "sad");
        }
    }

    static boolean bfs() {
        boolean isArrive=false;

        while (!queue.isEmpty() || isArrive){
            Info now = queue.poll();

            //페스티벌 도착
            if(road[now.x][now.y]==-2) {
                isArrive=true;
                break;
            }

            int nextBeerCount = now.beerCount;
            //편의점 도착
            if(road[now.x][now.y]==-1){
                nextBeerCount=20;
            }

            for(int i=0; i<4; i++){
                int nextX = now.x+dirX[i];
                int nextY = now.y+dirY[i];

                if(nextX>maxX || nextY>maxY || nextX<0 || nextY<0) continue;
                if(now.roadCount==49) {
                    if(now.beerCount==0) continue;
                    nextBeerCount--;
                }
                queue.add(new Info(nextX, nextY, nextBeerCount, now.roadCount+1));

            }

        }

        return isArrive;
    }
}