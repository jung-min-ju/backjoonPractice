import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<Integer> queue[] ;
    static boolean visited [];
    static int order[];
    static int step=1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        queue = new Queue[N+1];
        visited = new boolean[N+1];
        order = new int[N+1];

        for(int i=0; i<=N; i++){
            queue[i]=new PriorityQueue<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            queue[from].add(to);
            queue[to].add(from);
        }

        dfs(R);

        for(int i=1; i<=N; i++){
            System.out.println(order[i]);
        }
    }

    static void dfs(int now){
        visited[now]=true;
        order[now]=step++;

        int size=queue[now].size();

        for(int i=0; i<size; i++){
            int next = queue[now].poll();
            if(visited[next]) continue;
            dfs(next);
        }

    }
}