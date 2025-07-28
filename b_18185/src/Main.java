import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int answer=0;
    static int [] factory;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        factory = new int[N+2];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            factory[i]= Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (factory[i + 1] > factory[i + 2]) {
                buyTwo(factory[i], factory[i+1]-factory[i+2], i);  // 예외적으로 먼저 두 공장 처리
            }
            buyThree(i);    // 가장 우선적으로 싸게 처리
            buyTwo(factory[i], factory[i+1], i);      // 남은 두 공장 처리
            buyOne(i);      // 마지막 단독 처리
        }

        System.out.println(answer);
    }

    static void buyThree(int i) {
        int minus = Math.min(factory[i+1], factory[i+2]);
        minus = Math.min(factory[i], minus);

        factory[i] -= minus;
        factory[i+1] -= minus;
        factory[i+2] -= minus;

        answer += (minus* 7);
    }

    static void buyTwo (int value1, int value2, int i) {
        int minus = Math.min(value1, value2);
        factory[i] -= minus;
        factory[i+1] -= minus;
        answer += (minus* 5);
    }

    static void buyOne(int i) {
        answer += (factory[i]* 3);
        factory[i]=0;
    }
}