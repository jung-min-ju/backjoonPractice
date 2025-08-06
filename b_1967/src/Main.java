import java.io.*;
import java.util.*;

class Info {
    int node, dis;

    public Info(int n, int d){
        this.node = n;
        this.dis = d;
    }
}

public class Main {
    static int N, FIRST, MAX;
    static List<Info>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new List[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            tree[p].add(new Info(c,d));
            tree[c].add(new Info(p,d));
        }

        // 1차 DFS
        findMaxRoute(1, 0);

        // 2차 DFS
        Arrays.fill(visited, false);
        MAX = 0;
        findMaxRoute(FIRST, 0);

        System.out.println(MAX);
    }

    static void findMaxRoute(int now, int dis){
        visited[now] = true;

        if(dis > MAX){
            MAX = dis;
            FIRST = now;
        }

        for(Info next : tree[now]){
            if(!visited[next.node]){
                findMaxRoute(next.node, dis + next.dis);
            }
        }
    }
}
