import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int arrival;
        long distance;

        public Node(int arrival, long distance) {
            this.arrival = arrival;
            this.distance = distance;
        }
    }

    static List<Node>[] tree;
    static boolean[] visited;
    static int MaxPoint;
    static long MaxDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                long distance = Long.parseLong(st.nextToken());
                tree[from].add(new Node(to, distance));
            }
        }

        // 첫 번째 DFS: 임의의 노드에서 가장 먼 노드 찾기
        dfs(1, 0); //임의의 한 노드에서 시작
        // 두 번째 DFS: 가장 먼 노드에서 시작하여 트리의 지름 계산
        Arrays.fill(visited, false);  // 방문 배열 초기화
        MaxDistance=0;
        dfs(MaxPoint, 0);
        System.out.println(MaxDistance);
    }

    static void dfs(int start, long distance) {
        visited[start] = true;
        if(distance>MaxDistance){
            MaxDistance=distance;
            MaxPoint=start;
        }

        for(Node next : tree[start]) {
            if(!visited[next.arrival]) {
                dfs(next.arrival, distance+ next.distance);
            }
        }
    }

}
