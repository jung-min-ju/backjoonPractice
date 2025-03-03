import javax.naming.PartialResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int to, from, way=1, time;
    static int route[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        visited=new boolean[100000+1];
        route = new int[100000+1];

        //bfs 진행으로 최소 시간 찾기
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(from);
        visited[from]=true;

        while (!queue.isEmpty()){
            int next = queue.poll();

            if(next==to&&!visited[next]) {
                time=route[to];
                visited[next]=true;
            }

            if(next==to&&visited[next]&&route[next]==time) {
                way++;
            }

            if(next+1<=100000&&!visited[next+1]) {
                visited[next+1]=true;
                route[next+1]=route[next]+1;
                queue.add(next+1);
            }
            if(next*2<=100000&&!visited[next*2]) {
                visited[next*2]=true;
                route[next*2]=route[next]+1;
                queue.add(next*2);
            }
            if(next-1>=0&&!visited[next-1]){
                visited[next-1]=true;
                route[next-1]=route[next]+1;
                queue.add(next-1);
            }
        }

        System.out.println(route[to]);
        System.out.println(way);
    }

}