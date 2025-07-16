import javax.print.attribute.standard.OrientationRequested;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int [] TIME;
    static List<Integer> [] ORDER;
    static long [] STACK_TIME;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-->0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            TIME = new int[N+1];
            ORDER = new List[N+1];
            STACK_TIME = new long[N+1];
            Arrays.fill(STACK_TIME, -1);

            st = new StringTokenizer(br.readLine());

            for(int i=1; i<=N; i++){
                TIME[i] = Integer.parseInt(st.nextToken());
                ORDER[i] = new ArrayList<>();
            }

            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());

                int preOrder = Integer.parseInt(st.nextToken());
                int postOrder = Integer.parseInt(st.nextToken());

                ORDER[postOrder].add(preOrder);
            }

            int target = Integer.parseInt(br.readLine());
            System.out.println(findPreOrder(target));
        }

    }

    static long findPreOrder(int target) {
        if (STACK_TIME[target] != -1) return STACK_TIME[target];

        long max = 0;
        for (int pre : ORDER[target]) {
            max = Math.max(max, findPreOrder(pre));
        }

        return STACK_TIME[target] = max + TIME[target];
    }


//    static long findPreOrder(int target) {
//        int max = 0;
//        long answer = 0;
//        for (int pre : ORDER[target]) {
//            max = Math.max(max, TIME[pre]);
//            if(STACK_TIME[pre]!=-1) {
//                answer = Math.max(STACK_TIME[pre], answer);
//            } else {
//                answer = Math.max(findPreOrder(pre), answer);
//            }
//        }
//
//        if(STACK_TIME[target]==-1) STACK_TIME[target] = answer+max;
//
//        return max+answer;
//    }

}