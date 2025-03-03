import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static char [][] pan;
    static int [] dirR = {0,0,1,-1};
    static int [] dirC = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        pan = new char[R][C];
        StringBuilder sb = new StringBuilder();
        StringBuilder allBomb = new StringBuilder();
        StringBuilder allNone = new StringBuilder();
        for(int i=0; i<R; i++) {
            String s = br.readLine();
            for(int j=0; j<C; j++){
                pan[i][j]=s.charAt(j);
                sb.append(pan[i][j]);
                allBomb.append("O");
                allNone.append(".");
            }
            sb.append("\n");
            allBomb.append("\n");
            allNone.append("\n");
        }

        boolean isTrick=false;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++){
                if(pan[i][j]=='.') isTrick = findTrick(i,j);
            }
        }

        if(isTrick) {
            if(N<=5 && N%5==3) sb = allNone;
            else if (N>5 && N%4==3) sb = allNone;
            else if(N<=5&&N%5==1) sb=sb;
            else sb=allBomb;
        }
        else if (N<=5) {
            N=N%5;
            if(N==2||N==4){
                sb=allBomb;
            }
            else if (N==3){
                sb=initBomb();
            }
        }
        else {
            N=(N-5)%4;
            if(N==1||N==3){
                sb=allBomb;
            }
            else if (N==2){
                sb=initBomb();
            }
        }
        System.out.println(sb);
    }

    static StringBuilder initBomb() {
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(pan[i][j]=='O'){
                    sb.append(".");
                    continue;
                }
                if(i<R-1&&pan[i+1][j]=='O'){
                    sb.append(".");
                    continue;
                }
                if(j<C-1&&pan[i][j+1]=='O') {
                    sb.append(".");
                    continue;
                }
                if(j>0&&pan[i][j-1]=='O') {
                    sb.append(".");
                    continue;
                }
                sb.append("O");
            }
            sb.append("\n");
        }
        return sb;
    }

    static boolean findTrick(int r, int c) {

        boolean isTrick=true;
        for(int i=0; i<4; i++){
            if(r+dirR[i]==-1 || c+dirC[i]==-1 || r+dirR[i]==R || c+dirC[i]==C) continue;
            if(pan[r+dirR[i]][c+dirC[i]]=='.') {
                isTrick = !isTrick;
                break;
            }
        }
        return isTrick;
    }
}