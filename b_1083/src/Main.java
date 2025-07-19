import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N  = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int S = Integer.parseInt(br.readLine());

        for(int i=0; i<N-1 && S>0; i++) {
            int bigIdx = i;

            for(int j=i; j<=i+S && j<N; j++) {
                if(arr[bigIdx] > arr[j]) continue;
                bigIdx=j;
            }

            int used = bigIdx - i;
            S -= used;

            int big = arr[bigIdx];
            for (int j = bigIdx; j > i; j--) {
                arr[j] = arr[j - 1];
            }
            arr[i] = big;
        }

        for(int value : arr) {
            System.out.print(value+" ");
        }
    }
}