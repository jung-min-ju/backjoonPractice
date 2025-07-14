import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int people[];
    static List<Integer>[] graph;
    static int answer = Integer.MAX_VALUE;
    static int total = 0;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        people = new int[N+1];
        graph = new List[N+1];
        visited = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            total+=people[i];
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int nextCount = Integer.parseInt(st.nextToken());
            if(nextCount==0) continue;
            for(int j=0; j<nextCount; j++)  {
                int next = Integer.parseInt(st.nextToken());
                graph[i].add(next);
            }
        }

        //현재 몇 구역으로 구성되어 있는지 찾기
        int set = 0;
        List<Integer> setStartIndex = new ArrayList<>();
        for(int i=1; i<=N; i++){
            if(visited[i]) continue;
            setStartIndex.add(i);
            set++;
            findSet(i);
        }

        if(set==1){
            Arrays.fill(visited, false);
            findArea(1, people[1], total-people[1]);
            System.out.println(answer);
        } else if (set==2) {
            Arrays.fill(visited, false);
            int a = findSetSub(setStartIndex.get(0));
            Arrays.fill(visited, false);
            int b = findSetSub(setStartIndex.get(1));
            System.out.println(Math.abs(a-b));
        } else {
            System.out.println(-1);
        }
    }

    static void findSet(int now) {
        visited[now]=true;
        for(int i=0; i<graph[now].size(); i++){
            int next = graph[now].get(i);
            if(visited[next]) continue;
            visited[next]=true;
            findSet(next);
        }
    }

    static int findSetSub(int now) {
        visited[now]=true;
        int answer = people[now];
        for(int i=0; i<graph[now].size(); i++) {
            int next = graph[now].get(i);
            if(visited[next]) continue;
            answer += findSetSub(next);
        }
        return answer;
    }

    static void findArea(int now, int sum, int total) {
        if(sum>=total) {
            answer = Math.min(answer, sum-total);
            return;
        }
        visited[now]=true;

        for(int i=0; i<graph[now].size(); i++){
            int next = graph[now].get(i);
            if(visited[next]) continue;
            findArea(next, sum+people[next], total-people[next]);
        }

        visited[now]=false;
    }
}