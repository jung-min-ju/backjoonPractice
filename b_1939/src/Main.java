import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Info>[] graph;
    static int[] factory;

    static class Info {
        int to;
        int weight;

        Info(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Info(B, C));
            graph[B].add(new Info(A, C));
        }

        st = new StringTokenizer(br.readLine());
        factory = new int[2];
        factory[0] = Integer.parseInt(st.nextToken());
        factory[1] = Integer.parseInt(st.nextToken());

        int answer = dijkstra(factory[0], factory[1]);
        System.out.println(answer);
    }

    static int dijkstra(int start, int end) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);
        pq.add(new Info(start, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Info current = pq.poll();
            int now = current.to;
            int weight = current.weight;

            if (visited[now]) continue;
            visited[now] = true;

            if (now == end) return weight;

            for (Info next : graph[now]) {
                if (!visited[next.to]) {
                    pq.add(new Info(next.to, Math.min(weight, next.weight)));
                }
            }
        }
        return 0;
    }

}
