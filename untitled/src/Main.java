import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long N = Long.parseLong(br.readLine());

        long count = 0;
        long sum = 1;
        long answer=0;

        while(answer<N){
            count++;
            answer+=sum++;
        }

        if(answer==N) System.out.println(count);
        else System.out.println(count-1);
   }
}