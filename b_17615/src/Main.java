import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char c [];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        c = new char[N];
        for(int i=0; i<N; i++){
            c[i]=s.charAt(i);
        }

        int red = findLeftMove('R','B');
        int blue = findLeftMove('B','R');

        int left = Math.min(red, blue);

        int red2 = findRightMove('R', 'B');
        int blue2 = findRightMove('B', 'R');

        int right = Math.min(red2, blue2);

        System.out.println(Math.min(left, right));
    }

    static int findLeftMove(char standard, char noneStandard) {
        char [] move = c.clone();
        int moveDex = 0;

        for(int i=N-1; i>=0; i--){
            if(c[i]==noneStandard) {
                moveDex=i;
                break;
            }
        }

        int next=moveDex-1;
        int count = 0;

        while (next>=0) {
            if(move[next]==standard) {
                move[moveDex]=standard;
                move[next]=noneStandard;
                count++;
                moveDex--;
            }
            next--;
        }
        return count;
    }

    static int findRightMove(char standard, char noneStandard) {
        char [] move = c.clone();
        int moveDex = 0;

        for(int i=0; i<N; i++){
            if(c[i]==noneStandard) {
                moveDex=i;
                break;
            }
        }

        int next=moveDex+1;
        int count = 0;

        while (next<N) {
            if(move[next]==standard) {
                move[moveDex]=standard;
                move[next]=noneStandard;
                count++;
                moveDex++;
            }
            next++;
        }
        return count;
    }


}