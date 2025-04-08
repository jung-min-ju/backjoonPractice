import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [] honey;
    static int [] original;
    static int maxDex=0, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        honey=new int[N];
        original=new int[N];
        st = new StringTokenizer(br.readLine());

        honey[0] = Integer.parseInt(st.nextToken());
        original[0] = honey[0];
        int max = 0;

        for(int i=1; i<N; i++) {
            original[i] =  Integer.parseInt(st.nextToken());
            honey[i] = honey[i-1]+original[i];
            if(i!=0 && i!=N-1){
                max = Math.max(max,  original[i]);
                if(max==original[i]) maxDex=i;
            }
        }

        int answer = 0;
        //꿀통이 왼쪽에 있는 경우
        answer = honeyHouseIsLeft();
        //꿀통이 오른쪽에 있는 경우
        answer = Math.max(honeyHouseIsRight(), answer);
        //꿀통이 중앙에 있는 경우(max = 꿀통 위치)
        answer = Math.max(honeyHouseIsMiddle(maxDex), answer);

        System.out.println(answer);
    }

    static int honeyHouseIsLeft() {
        int b1Honey = honey[N-2];
        int answer=0;
        for (int i=1; i<N-1; i++){
            int nowB1Honey = b1Honey-original[i];
            int nowB2Honey = honey[i-1];

            answer = Math.max(answer, nowB1Honey+nowB2Honey);
        }

        return answer;
    }

    static int honeyHouseIsRight() {
        int b1Honey = honey[N-1]-original[0];
        int answer = 0;
        for (int i=1; i<N-1; i++){
            int nowB1Honey = b1Honey-original[i];
            int nowB2Honey = honey[N-1]-honey[i];

            answer = Math.max(answer, nowB1Honey+nowB2Honey);
        }

        return answer;
    }

    static int honeyHouseIsMiddle(int honeyDex){
        int left = honey[honeyDex] - honey[0];
        int right = honey[N-2] - honey[honeyDex-1];

        return left+right;
    }
}