import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int [] DP;
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        DP = new int[N+1];
        DP[0] = -1;
        DP[1] = 1;
        DP[2] = 2;
         System.out.println(findDp(N);
    }
    public static int findDp(int n){
        if(DP[n]==0){
            DP[n] = findDp(n-2) + findDp(n-1);
        }
        return DP[n];
    }

}