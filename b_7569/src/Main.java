
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class TOMATO {
        int x;
        int y;
        int h;

        public TOMATO(int x, int y, int h){
            this.x=x;
            this.y=y;
            this.h=h;
        }
    }

    static int dirX[] = {0,0,-1,1};
    static int dirY[] = {-1,1,0,0};
    static int dirH[] = {-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); //y
        int N = Integer.parseInt(st.nextToken()); //x
        int H = Integer.parseInt(st.nextToken()); //h

        int shyTomato=0;
        int notShyTomato = 0;

        int pan [][][] = new int[N][M][H];
        boolean visited [][][] = new boolean[N][M][H];
        Queue<TOMATO> queue = new ArrayDeque<>();
        int answer = 0;

        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                for (int k=0; k<M; k++) {
                    pan[j][k][i] = Integer.parseInt(st.nextToken());
                    if(pan[j][k][i]==0) {
                        shyTomato++;
                    }
                    if(pan[j][k][i]==1){
                        queue.add(new TOMATO(j,k,i));
                    }
                }
            }
        }

        int exDayCount = queue.size();
        while (!queue.isEmpty()) {
            while (exDayCount-->0) {
                TOMATO tomato = queue.poll();
                visited[tomato.x][tomato.y][tomato.h] = true;
                pan[tomato.x][tomato.y][tomato.h] = 1;
                for(int i=0; i<4; i++) {
                    int nextX = tomato.x + dirX[i];
                    int nextY = tomato.y + dirY[i];
                    if( nextY<0 || nextX<0 || nextX>=N || nextY>=M ) continue;
                    if(visited[tomato.x][tomato.y][tomato.h]){
                        notShyTomato++;
                        continue;
                    }
                    if(pan[nextX][nextY][tomato.h]==0) {
                        visited[nextX][nextY][tomato.h] = true;
                        notShyTomato++;
                        queue.add(new TOMATO(nextX, nextY, tomato.h));
                    }
                }
                for(int i=0; i<2; i++){
                    int nextH = tomato.h+dirH[i];
                    if(nextH<0 || nextH>=H) continue;
                    if (visited[tomato.x][tomato.y][nextH]){
                        notShyTomato++;
                        continue;
                    }
                    if(pan[tomato.x][tomato.y][nextH]==0){
                        visited[tomato.x][tomato.y][tomato.h] = true;
                        notShyTomato++;
                        queue.add(new TOMATO(tomato.x, tomato.y, nextH));
                    }
                }
            }
            answer++;
            exDayCount = queue.size();
        }

        System.out.println("notShyTomato = " + notShyTomato);
        System.out.println("shyTomato = " + shyTomato);
        System.out.println("answer = " + answer);
        System.out.println(shyTomato==notShyTomato ?  answer-1 : -1);
    }

}