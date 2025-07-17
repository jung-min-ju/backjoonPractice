import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int [] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        numbers = Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(numbers);

        int answer=0;
        boolean flag;

        for(int stdIdx=0; stdIdx<N; stdIdx++) {
            flag=false;
            for(int lIdx=0; lIdx<N; lIdx++) {
                if(lIdx==stdIdx) continue;
                for(int rIdx=findRIdx(numbers[stdIdx]); rIdx>lIdx; rIdx--) {
                    if(rIdx==stdIdx) continue;
                    if(numbers[lIdx]+numbers[rIdx] == numbers[stdIdx]) {
                        answer++;
                        flag=true;
                    }
                    if(flag) break;
                }
                if(flag) break;
            }
        }

        System.out.println(answer);

    }

    static int findRIdx(int stdVal) {
        int lIdx=0;
        int rIdx=N-1;
        int mIdx = 0;

        while (lIdx<rIdx) {
             mIdx = (rIdx+lIdx)/2;
            if(numbers[mIdx]>stdVal) rIdx=mIdx;
            else if (numbers[mIdx]<stdVal) lIdx=mIdx;
            else break;
        }

        return mIdx;
    }
}











