import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   public static void main(String args[]) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       int N = Integer.parseInt(br.readLine());
       int [] enemy = new int[N];
       int answer = 0;

       st = new StringTokenizer(br.readLine(), " ");

       for(int i=0; i<N ; i++){
           enemy[i] = Integer.parseInt(st.nextToken());
       }

       for(int i=0; i<N; i++){
           int count = 0;
           for(int j=i+1; j<N; j++){
               if(enemy[j]<enemy[i]) count++;
               else break;
           }
           answer = Math.max(answer, count);
       }
       System.out.println(answer);
   }
}