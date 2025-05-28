import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[]graph = new List[N+1];

        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[to].add(from);
            graph[from].add(to);
        }

        int answer[] = new int[N+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()){
            int now = queue.poll();

            for(int i=0; i<graph[now].size(); i++){
                int next = graph[now].get(i);
                if(answer[next]>0) continue;
                answer[next] = now;
                queue.add(next);
            }

        }

        for(int i=2; i<=N; i++){
            System.out.print(answer[i]+" ");
        }

    }
}