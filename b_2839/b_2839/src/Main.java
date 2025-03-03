import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [] sugarDP = new int[N+1];
        Arrays.fill(sugarDP, 50001);

        if(N<=5) {
            if(N==5 || N==3) System.out.println(1);
            else System.out.println(-1);
        }
        else {
            sugarDP[3] = 1;
            sugarDP[5] = 1;

            for(int i=6; i<=N; i++){
                sugarDP[i] = Math.min(sugarDP[i-5]+1, sugarDP[i-3]+1);
            }

            if(sugarDP[N] >= 5001) System.out.println(-1);
            else System.out.println(sugarDP[N]);

        }

    }
}