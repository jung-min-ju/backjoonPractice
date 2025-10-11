import java.io.*;
import java.util.*;

public class Main {
    static int INF = (int) -1e9;
    static int N, M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N + 1][101];
            for (int i = 0; i <= N; i++) {
                Arrays.fill(dp[i], INF);
            }

            int answer = dfs(N, 1);
            System.out.println(answer > 0 ? answer : -1);
        }
    }

    static int dfs(int pushUpCnt, int turn) {
        if (pushUpCnt < 0) return INF;
        if (pushUpCnt == 0) return 0;

        if (dp[pushUpCnt][turn] != INF) return dp[pushUpCnt][turn];

        int result = INF;
        for (int val : arr) {
            int temp = dfs(pushUpCnt - turn * val, turn + 1);
            result = Math.max(result, temp + val);
        }

        dp[pushUpCnt][turn] = result;
        return result;
    }
}


/*
1. 문제요약
- 팀의 ⭐⭐누적 득점⭐⭐에 따라 팔굽혀펴기 횟수가 정해진다.
- 최종 팔굽혀펴기 횟수를 보고 팀이 얻을 수 있는 최대 득점 점수를 구하라.

2. 입출력
[입력]
- 테스트 케이스의 개수(1<=T<=20)
- 팔굽혀펴기 횟수 N(1<=N<=5000), 득점의 종류 M (1<=M<=10)
- 팀의 한 번으로 득점 가능한 점수

[출력]
- 팀의 최대 득점 가능 점수
- 불가능하다면 -1 출력

3. 알고리즘
- 팔굽혀기펴기 횟수만큼 배열 생성
- 초기 0
- 만약 0에 도달할 수 있는 경우가 없다면 -1, 도달 했다면 최대 얻은 점수가 정답이 됨.


* */