import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Info {
    int to;
    int dis;

    public Info(int to, int dis){
        this.to=to;
        this.dis=dis;
    }
}

public class Main {
    static int N,M;
    static List<Info>[] graph;
    static boolean []visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            graph[from].add(new Info(to, dis));
            graph[to].add(new Info(from, dis));
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Arrays.fill(visited, false);
            System.out.println(findDfs(a,b,0));
        }

    }

    static int findDfs(int now, int arrival, int nowDis) {
        visited[now]=true;
        if(now==arrival) return nowDis;
        for(int i=0; i<graph[now].size(); i++) {
            Info next = graph[now].get(i);
            if(visited[next.to]) continue;
            int result = findDfs(next.to, arrival, next.dis+nowDis);
            if(result>0) return result;
        }
        return -1;
    }
}