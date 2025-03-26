import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long pivot [] = new long[N+1];

        pivot[1]=1;

        for(int i=2; i<=N; i++) {
            pivot[i] = (pivot[i-1]+pivot[i-2]);
        }

        System.out.println(pivot[N]);
    }
}