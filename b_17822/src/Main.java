
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] onepan;
    static int total=0,count=0;
    static int N, T, M;
    static int [] dirx = {0,0,1,-1};
    static int [] diry = {-1,1,0,0};
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken()); //원판 개수
        M=Integer.parseInt(st.nextToken()); //원판에 적힌 수의 개수
        T=Integer.parseInt(st.nextToken()); //회전 수

        onepan=new int[N][M];
        count=N*M;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int num = Integer.parseInt(st.nextToken());
                onepan[i][j] = num;
                total+=num;
            }
        }

        for(int i=0; i<T; i++){
            flag=false;
            st = new StringTokenizer(br.readLine());
            int xi = Integer.parseInt(st.nextToken()); //원판 배열 배수
            int di = Integer.parseInt(st.nextToken()); //방향
            int ki = Integer.parseInt(st.nextToken()); //칸 이동 수

            turnOnePan(xi, di, ki);
            if(!flag) {  //만약 값이 바뀌지 않았다면
                calAver();
            }

        }

        System.out.println(total);

    }

    static void turnOnePan(int xi, int di, int ki) {
        for(int i=xi; i<=N; i+=xi) { //배수만큼 원판 돌리기
            int copy[] = onepan[i-1].clone(); //깊은 복사
            for(int j=0; j<M; j++) {
                int nextDex = di == 0 ? (j+ki)%M :((j - ki) % M + M) % M; //방향 설정
                onepan[i-1][nextDex] = copy[j]; //배열 이동
            }
        }

        //원판 다돌렸으면 dfs 탐색
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                dfs(i,j, false);
            }
        }
    }

    static void dfs(int x, int y, boolean dup) {
        boolean isDup=false;
        int nowNum=onepan[x][y];

        for(int i=0; i<4; i++) {
            int nextX = x+dirx[i];
            int nextY = y+diry[i];

            if(nextX<0 || nextX>=N || nextY<-1 || nextY>M) continue; //범위 체크
            if(nextY==-1) nextY=M-1;
            if(nextY==M) nextY=0;

            if(nowNum == onepan[nextX][nextY] && nowNum!=0) {
                onepan[x][y]=0;
                if(!dup) {
                    isDup=true;
                    flag=true;
                }
                dfs(nextX, nextY, true);
            }
        }

        if(isDup || dup) {
            onepan[x][y]=0;
            count--;
            total-=nowNum;
        }
    }

    static void calAver() {
        int newTotal=0;
        double nowAver = (double) total / (double)count;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(onepan[i][j]==0)continue;
                if(onepan[i][j]<nowAver) onepan[i][j]+=1;
                else if(onepan[i][j]>nowAver) onepan[i][j]-=1;
                newTotal+=onepan[i][j];
            }
        }
        total=newTotal;
    }

}