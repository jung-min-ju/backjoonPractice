import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] suYol = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            suYol[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(suYol);

        int M = Integer.parseInt(br.readLine());

        int answer=0;

        for(int j=1; j<N; j++){
            for(int i=0; i<j; i++) {
                if(suYol[i]+suYol[j]==M) answer++;
                if(suYol[i]+suYol[j]>M) break;
            }
        }
        System.out.println(answer);
    }
}