import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb;
    static int [] input;
    static int [] arr;
    static boolean []visited;
    static HashSet<String> hashSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        input = new int[N];
        arr = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        dfs(0);
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if(depth==M) {
            StringBuilder sb2 = new StringBuilder();
            for(int value : arr){
                sb2.append(value+" ");
            }
            String check = sb2.toString();
            if(hashSet.contains(check)) return;
            hashSet.add(check);
            sb.append(check+"\n");
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] =true;
                arr[depth] = input[i];
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }


}