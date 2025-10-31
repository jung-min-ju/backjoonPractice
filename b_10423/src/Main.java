import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Info implements Comparable<Info> {
    int from;
    int to;
    int dis;

    public Info(int from, int to, int dis) {
        this.from=from;
        this.to=to;
        this.dis=dis;
    }

    @Override
    public int compareTo(Info o){
        return this.dis-o.dis;
    }
}
public class Main {
    static int N, M, K;
    static Queue<Info> pq = new PriorityQueue<>();
    static int P[];
    static Set<Integer> checkE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Info> cities [];
        int E [];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = new int[N+1];
        cities = new List[N+1];
        E = new int[K];
        checkE = new HashSet<>();

        Arrays.fill(P, -1);
        for(int i=0; i<=N; i++){
            cities[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int e = Integer.parseInt(st.nextToken());
            E[i] = e;
            checkE.add(e);
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            cities[from].add(new Info(from, to, dis));
            cities[to].add(new Info(to, from, dis));
        }

        //발전소와 연결된 케이블만 찾아서 pq에 넣음.
        for(int i=0; i<K; i++) {
            int e = E[i];
            for(int j=0; j<cities[e].size(); j++) {
                pq.add(cities[e].get(j));
            }
        }

        int cnt=0;
        int answer = 0;

        while (true) {
            Info leastEdge = pq.poll();
            if(isCircle(leastEdge.from, leastEdge.to)) continue;
            answer+=leastEdge.dis;
            cnt++;
            for(int j=0; j<cities[leastEdge.to].size(); j++) {
                pq.add(cities[leastEdge.to].get(j));
            }
            if(cnt==N-K) break;
        }

        System.out.println(answer);
    }

    static boolean isCircle(int u, int v){
        u = find(u);
        v = find(v);

        if(u==v) return true;

        if(P[u] > P[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        if(P[u]==P[v]) P[u]--;
        P[v] = u;
        return false;
    }

    static int find(int x){
        if(P[x] < 0) return x;
        return P[x] = find(P[x]);
    }
}

/*
1. 문제 요약
- 발전소는 특정 도시에 건설되어 있다.
- 케이블 설치비를 최소한으로 아껴 모든 도시에 전기 공급을 해야한다.
- 케이블이 연결되어 있는 도시에는 발전소가 반드시 하나만 존재해야 한다.

2. 입력
- 도시의 개수 N(1<=N<=100), 설치 가능한 케이블의 수 M(1<=M1<=100000), 발전소의 개수 K(1<=K<=N)
- 발전소가 설치된 도시의 번호들
- 케이블의 정보 도시, 도시, 비용 : u,v,w (w<=10000 양의 정수)

3. 알고리즘
- 다익스트라 알고리즘을 쓰되, 거리 갱신을 w로 해야 함(축적이 아니기 때문)
* */


