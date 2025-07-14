import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;

public class Main {
    static Long[] dp = new Long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Arrays.fill(dp, Long.MAX_VALUE);

        dp[2] = 1L;
        dp[3] = 7L;
        dp[4] = 4L;
        dp[5] = 2L;
        dp[6] = 0L;
        dp[7] = 8L;

        findMinNum();

        for(int i=0; i<T; i++) {
            int value = Integer.parseInt(br.readLine());
            long minValue = dp[value];
            if(value==6) minValue=6;
            System.out.println(minValue+" "+findMaxNum(value));
        }
    }

    static String findMaxNum(int value){
        StringBuilder s= new StringBuilder();
        if(value%2!=0) {
            s.append(7);
            value-=3;
        }

        int mok = value/2;
        while (mok-->0) {
            s.append("1");
        }

        return s.toString();
    }

    static void findMinNum() {
        for(int i=8; i<101; i++) {//value 전체 범위
            for(int j=2; j<i-1; j++) { //실제 value가 조합 찾아야 할 범위
                String s="";

                //조합 찾기
                int a=j;
                int b=i-j; //i를 만들 수 있는 모든 조합이 a와 b에 들어감

                if(a==6) s+="6"; //맨 앞에 0이 올 수 없음. 최소값 찾으니까 0다음으로 작은 수 6 넣기
                else s+=dp[a];
                s+=dp[b];

                long nowMinValue = Long.parseLong(s);

                dp[i]=Math.min(dp[i], nowMinValue);
            }

        }
    }


}
