import com.sun.jdi.IntegerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> queue [] = new PriorityQueue[N+1];
        boolean visited[] = new boolean[N+1];
        int order [] = new int[N+1];

        for(int i=1; i<=N; i++){
            queue[i]=new PriorityQueue<>(Comparator.reverseOrder());
        }

        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            queue[to].add(from);
            queue[from].add(to);
        }

        int step=1;
        Queue<Integer> go = new ArrayDeque<>();
        go.add(R);

        while (!go.isEmpty()){
            int now = go.poll();
            if(visited[now]) continue;
            visited[now]=true;
            order[now] = step++;

            int size = queue[now].size();
            for(int i=0; i<size; i++){
                int next = queue[now].poll();
                if(visited[next]) continue;
                go.add(next);
            }
        }

        for(int i=1; i<=N; i++){
            System.out.println(order[i]);
        }
    }
}