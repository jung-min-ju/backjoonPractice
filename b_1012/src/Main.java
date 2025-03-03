import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] field;
    static int [] dirX = {1, -1, 0, 0};
    static int [] dirY = {0, 0, 1, -1};
    static int M,N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        field=new int[50][50];

        int T = Integer.parseInt(br.readLine());
        for(int i=1; i<=T; i++){
            st=new StringTokenizer(br.readLine());
            M=Integer.parseInt(st.nextToken());
            N=Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            for(int j=0; j<K; j++){
                st=new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                field[x][y] = i;
            }
            System.out.println(wormCounter(i));
        }
    }

    static int wormCounter(int T){
        int count=0;
        for(int i=0;i<N; i++){
            for(int j=0; j<M; j++){
                if(field[i][j]==T){
                    bfs(i, j, T);
                    count++;
                }
            }
        }
        return count;
    }

    static void bfs(int x, int y, int T) {
        if(y<0||x<0||x==N ||y==M||field[x][y]!=T) return;

        field[x][y]=-1;

        for(int i=0; i<4; i++){
            bfs(x+dirX[i], y+dirY[i], T);
        }
    }
}