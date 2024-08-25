import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int value = Integer.parseInt(br.readLine());

            dp(value-1);
            dp(value-2);
            dp(value-3);

            System.out.println(count);
            count=0;
        }

    }

    static void dp(int num) { //num 에는 남은 수가 들어옴. 이제 dp 배열로 찾아줄 것
        if(num<0) return;
        if(num==0) {
            count++;
            return;
        }

        for(int i=1; i<=3; i++){
            if(num-i >= 0) {
                dp(num-i);
            }
            else break;
        }
    }
}