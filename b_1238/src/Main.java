import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Info implements Comparable<Info> {
    int to;
    int dis;

    public Info(int to, int dis){
        this.to=to;
        this.dis=dis;
    }

    public int compareTo(Info o){
        return this.dis-o.dis;
    }

}

public class Main {
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int[] go = new int[N+1];
        int[] back = new int[N+1];
        List<Info>[] out = new List[N+1];
        List<Info>[] in = new List[N+1];

        Arrays.fill(go, Integer.MAX_VALUE);
        Arrays.fill(back, Integer.MAX_VALUE);
        go[X] = 0;
        back[X] = 0;

        for(int i=0; i<=N; i++){
            out[i] = new ArrayList<>();
            in[i] = new ArrayList<>();
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            in[to].add(new Info(from, dis));
            out[from].add(new Info(to, dis));
        }

        dijkstra(go, in);
        dijkstra(back, out);

        int answer = 0;
        for(int i=1; i<=N; i++){
            answer = Math.max(answer, go[i]+back[i]);
        }

        System.out.println(answer);

    }


    static void dijkstra(int [] dij, List<Info> [] graph) {
        Queue<Info> queue = new PriorityQueue<>();
        queue.add(new Info(X, 0));

        while (!queue.isEmpty()) {
            Info now = queue.poll();

            for(Info next : graph[now.to]) {
                if(dij[next.to] > dij[now.to]+next.dis){
                    dij[next.to] = dij[now.to]+next.dis;
                    queue.add(next);
                }
            }

        }
    }
}

/*
1. 문제 요약
: n개의 마을에 학생이 각 1명씩 존재하며, x번째 마을에서 다같이 모여야 함
: 마을 간에는 m개의 단방향 도로가 있고, i번째 길 소요시간은 Ti이다
: 각 학생들은 무조건 다시 그들의 마을로 돌아간다. 오고 가는 길은 다를 수 있다.

2. 입출력
- N (1<=N<=1000)
- M (1<=M<=10000)
- 중복 간선 x

3. 테스트케이스
- N : 4, M : 8, X : 2

[들어오는 단방향 그래프]
1 : 2(1) 3(2)
2 : 1(4) 4(3)
3 : 1(2)
4 : 1(7) 3(4)

[갈 때 - 들어오는 그래프 사용]
1  2  3  4
무 0  무 무

1) now : 2
1  2  3  4
4  0  5  3

2) now : 4
1  2  3  4
4  0  5  3

3) now : 1
1  2  3  4
4  0  5  3

4) now : 3
1  2  3  4
4  0  5  3


4) now : 4

[나가는 단방향 그래프]
1 : 2(4) 3(2) 4(7)
2 : 1(1) 3(5)
3 : 1(2) 4(4)
4 : 2(3)

[돌아올 때 - 나가는 그래프 사용]
1  2  3  4
무 무  무 무

1) now : 2
1  2  3  4
1 0  5 무

2) now : 1
1  2  3  4
1  5  3  8

3) now : 3
1  2  3  4
1  5  3  7

4) now : 4
1  2  3  4
1  5  3  7

-------
[갈 때 최종 배열]
4  0  5  3

[돌아올 때 최종 배열]
1  5  3  7

4. 알고리즘
- 갈 때, 돌아올 때 2가지 경우의 무한대 배열 형성
- 갈 때, 돌아올 때 각각 다익스트라 알고리즘 수행해줌
- 최종으로 두 배열의 합이 가장 큰 수를 출력함.

* */