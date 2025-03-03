import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        System.out.println(answer(X, Y));
    }

    public static long answer (long X, long Y) {
        long initWinRate = Y * 100/X;
        if(initWinRate>=99){
            return -1;
        }
        long start = 1;
        long end = (int)X;

        while (start<end) {
            long mid = (start+end)/2;
            long tmp_x = X+mid;
            long tmp_y = Y+mid;

            long nextRate = 100*tmp_y/tmp_x;

            if (nextRate>initWinRate) {
                end=mid;
            }
            else {
                start=mid+1;
            }
        }
        return end;
    }
}