import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Info implements Comparable<Info> {
    int weight;
    int price;

    public Info (int w, int p){
        this.weight=w;
        this.price=p;
    }

    public int compareTo(Info o){
        if(this.weight==o.weight) return o.price-this.price;
        return this.weight-o.weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Info [] jewel = new Info[N];
        int [] bags = new int[K];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            jewel[i] = new Info(weight, price);
        }

        for(int i=0; i<K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags); //가방은 오름차순 정렬
        Arrays.sort(jewel); //보석은 무게에 대해 오름차순, 가격에 대해선 내림차순 정렬되어 있음.

        Queue<Info> queue = new PriorityQueue<>(Comparator.comparingInt((Info o) -> o.price).reversed());

        int j=0;
        long answer = 0;
        for(int i=0; i<K; i++){
            for(; j<N; j++){
                if(jewel[j].weight<=bags[i]) queue.add(jewel[j]);
                else break;
            }
            if(!queue.isEmpty()) {
                answer+=queue.poll().price;
            }
        }
        System.out.println(answer);

    }
}

/*
1. 문제 요약
- 각 보석의 무게는 Mi, 가격 Vi, 가방 K개, 각 가방의 무게는 Ci.
- 가방에는 최대 한 개의 보석만 넣을 수 있다.
- 훔칠 수 있는 보석의 최대 가격은?

2. 입출력
[입력]
- 보석 N개와 가방 K개 (1 ≤ N, K ≤ 300,000)
- 보석의 무게 Mi와 가격 Vi. (0 ≤ Mi, Vi ≤ 1,000,000)
- 가방에 담을 수 있는 최대 무게 Ci (1 ≤ Ci ≤ 100,000,000)

[출력]
- 상덕이가 훔칠 수 있는 보석 가격 합의 최대값

3. 테스트 케이스
3 2
1 65
5 23
2 99
10
2

4. 알고리즘
가방에는 최대 1개의 보석만 넣을 수 있다!! 가 키포인트
즉 이 문제는 정렬과 그리디를 활용하는 문제였다.

1. 가방 크기가 적힌 배열-> 오름차순 정렬
   보석 정보 담는 배열 -> 무게를 기준으로 오름차순 정렬, 무게가 같다면 가격을 기준으로 내림차순 정렬
   우선순위 큐 -> 내림차순 정렬

2. 가방 배열을 전체적으로 돌며 다음 단계 진행
2.1 현재 가방 무게보다 가볍거나 같은 보석이 있다면 우선순위 큐에 넣기
2.2 현재 가방 무게보다 큰 보석 차례시 보석 탐색 stop
2.3 우선순위큐가 비지 않았다면, 가장 첫 번째 보석의 price를 answer에 더함

* */