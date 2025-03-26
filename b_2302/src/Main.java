import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static int chairDp[];
    static boolean VIP[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        chairDp = new int[N+1];
        VIP = new boolean[N+1];

        for(int i=0; i<M; i++) {
            int vip = Integer.parseInt(br.readLine());
            VIP[vip] = true;
        }

        chairDp[0]=1;
        chairDp[1]=1;

        for(int i=2; i<=N; i++) {
            if(VIP[i] || VIP[i-1]) {
                chairDp[i]=chairDp[i-1];
                continue;
            }
            chairDp[i] = chairDp[i-1]+chairDp[i-2];
        }
        System.out.println(chairDp[N]);


    }
}