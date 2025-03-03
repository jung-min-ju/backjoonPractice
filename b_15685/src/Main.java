import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class DragonCurve {
        int x, y, g; //드래곤 커브 시작점 & 세대 정보 저장

        public DragonCurve(int x, int y, int g) {
            this.x = x;
            this.y = y;
            this.g = g;
        }

        public void setLastPoint(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    public static boolean pan[][]=new boolean[101][101];
    public static int dirY[] = {0, -1, 0, 1};
    public static int dirX[] = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int y,x,d,g;
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()); //시작점
            y = Integer.parseInt(st.nextToken()); //시작점
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            DragonCurve dragonCurve = new DragonCurve(x,y,g);

            //0세대 드래곤에 속하는 위치 true로 변경
            pan[y][x]=true;
            pan[y+ dirY[d]][x+ dirX[d]]=true;
            dragonCurve.setLastPoint(y+ dirY[d],x+ dirX[d]); //끝점 저장 -> 다음 계산시 해당 끝점=시작점이 됨.

            ArrayList<Integer> dirList = new ArrayList<>();
            dirList.add(d);
            findDragon(dragonCurve,dirList,1);
        }
        System.out.println(findSquare());
    }

    static void findDragon(DragonCurve dragonCurve, ArrayList<Integer> dirList, int gStep){
        if(dragonCurve.g<gStep) return;
        int exG_Count = (int)Math.pow(2,gStep-1); //이전 드래곤 세대의 선분 개수 구하는 코드

        for(int i=exG_Count-1; i>=0; i--) {
            int nextDir = (dirList.get(i)+1)%4;
            int nextY = dragonCurve.y+dirY[nextDir];
            int nextX = dragonCurve.x+dirX[nextDir];

            dragonCurve.setLastPoint(nextY, nextX);
            dirList.add(nextDir);

            pan[nextY][nextX]=true;
        }
        findDragon(dragonCurve, dirList, gStep+1);
    }

    static int findSquare() {
        int count = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++){
                if(pan[i][j]&&pan[i+1][j]&&pan[i][j+1]&&pan[i+1][j+1]){
                    count++;
                }
            }
        }
        return count;
    }

}