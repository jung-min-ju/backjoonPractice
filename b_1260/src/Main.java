import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static int startV;
    static List<Integer>[] list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 양방향
            list[s].add(e);
            list[e].add(s);
        }

        // 각 리스트를 정렬
        for (int i = 1; i <= V; i++) {
            Collections.sort(list[i]);
        }

        dfs(startV);
        Arrays.fill(visited, false);
        sb.append("\n");
        bfs(startV);

        System.out.print(sb.toString());
    }

    static void dfs(int vertex) { // 스택, 재귀를 활용해 구현
        if (visited[vertex]) return;
        visited[vertex] = true;
        sb.append(vertex).append(" ");

        for (int nextVertex : list[vertex]) {
            if (!visited[nextVertex]) {
                dfs(nextVertex);
            }
        }
    }

    static void bfs(int vertex) { // 큐를 통해 구현
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(vertex);
        visited[vertex] = true;

        while (!queue.isEmpty()) {
            int nowV = queue.poll();
            sb.append(nowV).append(" ");

            for (int nextV : list[nowV]) {
                if (!visited[nextV]) {
                    queue.add(nextV);
                    visited[nextV] = true;
                }
            }
        }
    }
}
