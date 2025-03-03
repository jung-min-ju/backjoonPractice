import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long A, B, answer=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        change(A,1);
        if(answer==Integer.MAX_VALUE) answer=-1;
        System.out.println(answer);
    }

    static void change(long number, int depth) {
        if(number>B) return;
        if(number==B) {
            answer=Math.min(answer, depth);
        }

        change(number*2, depth+1);
        change((number*10)+1, depth+1);
    }
}