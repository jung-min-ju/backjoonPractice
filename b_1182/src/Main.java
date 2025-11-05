import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] suyeol;
    static int[] count;
    static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        String s[] = br.readLine().split(" ");
        suyeol = Arrays.stream(s)
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        count = new int[N];
        findS(N-1, 0);
        int answer = Arrays.stream(count).sum();

        System.out.println(answer);
    }

    static void findS(int idx, int value) {
        if(idx==-1) return;
        if(value + suyeol[idx] == S) {
            count[idx]++;
        }
        //위에서부터 전달 -> 본인 포함 O
        findS(idx-1, value+suyeol[idx]);
        //위에서부터 전달 -> 본인 포함 X
        findS(idx-1, value);
    }
}
