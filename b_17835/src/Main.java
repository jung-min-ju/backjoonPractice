import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Info implements Comparable<Info> {
    int num;
    long dis;

    public Info(int num, long dis) {
        this.num=num;
        this.dis=dis;
    }

    @Override
    public int compareTo(Info o){
        if (this.dis != o.dis) return this.dis < o.dis ? -1 : 1;
        return Integer.compare(this.num, o.num);
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Info> graph[] = new List[N+1];
        long DIJKSTRA[] = new long[N+1];
        int MEETING[] = new int[K];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            graph[to].add(new Info(from,dis));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            MEETING[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Info> queue = new PriorityQueue<>();
        Arrays.fill(DIJKSTRA, Long.MAX_VALUE);

        for(int i=0; i<K; i++) {
            queue.add(new Info(MEETING[i],0));
            DIJKSTRA[MEETING[i]]=0;
        }

        while (!queue.isEmpty()) {
            Info now = queue.poll();
            if (DIJKSTRA[now.num] < now.dis) continue;

            for(int i=0; i<graph[now.num].size(); i++){
                Info next = graph[now.num].get(i);

                if(DIJKSTRA[next.num] > DIJKSTRA[now.num]+next.dis) {
                    DIJKSTRA[next.num] = DIJKSTRA[now.num]+next.dis;
                    queue.add(new Info(next.num,  DIJKSTRA[next.num]));
                }
            }
        }

        int answerNum = 0;
        long answerDis = 0;

        for(int i=1; i<=N; i++) {
            if(DIJKSTRA[i] > answerDis) {
                answerNum=i;
                answerDis=DIJKSTRA[i];
            }
        }
        System.out.println(answerNum+" "+answerDis);
    }
}

/*
1. 문제 요약
- 면접자들은 서로 다른 N개의 도시에 거주
- N개의 도시 중 K개의 도시에 면접장 존재.
- 모든 면접자는 가장 가까운 면접장소를 가야 한다.
- 도로의 조건
    - 단방향 도로이며, 거리는 서로 다를 수 있다.
    - 도로가 없을수도, 여러개가 있을수도 있다.
    - 어떤 도시에서든 면접장으로 갈 수 있는길이 최소 1개 이상 존재한다.

- 면접장에서 거리가 가장 먼 도시와 그 거리를 구하라.

2. 입출력
[입력]
- 도시의 수 N(2 ≤ N ≤ 100,000), 도로의 수 M(1 ≤ M ≤ 500,000), 면접장의 수 K(1 ≤ K ≤ N)
- 도시 1, 도시 2, 가는 거리C (1 ≤ C ≤ 100,000) 가 주어진다.
- 면접장이 배치된 번호 K개 공백을 두고 주어진다.

[출력]
- 면접장에서 거리가 가장 먼 도시를 출력하라.
    - 여러곳이라면 가장 작은 번호를 출력하라.

3. 알고리즘
- 메모이제이션을 활용해보자.

1. 필요한 알고리즘
- 번호, 거리 담을 class 배열
    - 거리 순 정렬
        - 번호 순 정렬
- 도시마다의 가장 빠른 면접장소 기록할 1차원 배열 fast[] = new Info[N+1]
- 도시마다 거쳐온 이전 도시를 기록할 1차원 배열 history[] = new int[N+1]
- 다익스트라 배열 dijkstra[] = new int[N+1]


1. 다익스트라 알고리즘으로 모든 도시를 진행한다.
    - 다익스트라 갱신을 한다면, 꼭 이전 도시가 어디였는지도 같이 갱신한다.
    - 다음 위치가 면접장소면, 배열 갱신만 하고 우선순위큐에 넣지 않는다. (더 이상 갈 필요 X)

2. 다익스트라 끝났다면, 면접 장소 중 최단 거리 가진 번호 알아낸 후, fast 배열을 history를 따라 차례대로 갱신한다.
    - historty를 따라가며, <현재 최단 면접장소 번호, 해당 번호에서 면접장소까지의 거리> 를 넣어주면 된다.

3. 다익스트라를 진행해야 할 도시가, fast 배열이 채워져 있으면 pass 한다.
 */