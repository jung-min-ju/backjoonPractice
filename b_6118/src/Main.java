import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean visited [] = new boolean[N+1];
        int distance [] = new int[N+1];

        List<Integer>[] way = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            way[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            way[from].add(to);
            way[to].add(from);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        int maxDistance=0;
        visited[1]=true;

        while (!queue.isEmpty()){
            int next = queue.poll();
            maxDistance = Math.max(maxDistance, distance[next]);

            for(int i=0; i<way[next].size(); i++){
                int value = way[next].get(i);
                if(!visited[value]) {
                    queue.add(value);
                    distance[value] = distance[next]+1;
                    visited[value] = true;
                }
            }
        }

        int count=0;
        int min=Integer.MAX_VALUE;

        for(int i=1; i<=N; i++){
            if(distance[i]==maxDistance){
                count++;
                min = Math.min(min, i);
            }
        }

        System.out.println(min+" "+maxDistance+" "+count);
    }
}