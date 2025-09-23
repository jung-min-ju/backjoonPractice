import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int dp [][][];
    static int N;
    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][10][1<<10]; //[order][orderNum][history]

        //숫자의 첫 번째 자릿수가 0이 되지 않도록 초기화.
        for(int orderNum = 1; orderNum<=9; orderNum++) dp[1][orderNum][1<<orderNum] = 1;

        //숫자의 두 번째 자릿수부터 계산 시작
        for(int order=2; order<=N; order++){
            for(int orderNum = 0; orderNum<=9; orderNum++){
                for(int history = 0; history<(1<<10); history++) {
                    int currH = history | (1<<orderNum);
                    if(orderNum == 0) dp[order][orderNum][currH] += dp[order-1][orderNum+1][history]%MOD;
                    else if(orderNum==9) dp[order][orderNum][currH] += dp[order-1][orderNum-1][history]%MOD;
                    else dp[order][orderNum][currH] += (dp[order-1][orderNum-1][history]%MOD+dp[order-1][orderNum+1][history]%MOD);

                    dp[order][orderNum][currH]%=MOD;
                }
            }
        }

        long ans = 0;

        for(int orderNum=0; orderNum<=9; orderNum++){
            ans+=dp[N][orderNum][1024-1] %MOD;
            ans%=MOD;
        }

        System.out.println(ans);


    }
}

/*
1. 문제 요약
- 45656-> 인접한 모든 자리 차이가 1임 -> 이런 걸 "계단 수" 라고 한다.
[계단 수 조건]
- 길이가 N이어야 함
- 0부터 9까지 숫자가 모두 등장해아 함
- 0으로 시작한다면 계단 수 X

2. 입출력
[입력]
- N (1<=N<=100) 즉, 최대 수 10억

3. 테스트케이스
10
1
-> 9876543210 (딱 1개만 존재. 1을 10억으로 나눈 나머지는 1)

4. 알고리즘
3차원 배열 및 비트마스킹기법이 필요
- dp[i][j][k] : i 번째 숫자는 j이다. 현재까지 방문된 0~9까지의 기록은 k와 같다.
- 이때 k는 비트마스킹을 통해 기록한다. 예를 들어, 11000000은 0과 1을 방문한 상태이다.
- i의 범위는 입력값으로 주어지는 N과 범위가 동일하다. k의 개수는 0~9를 나타내기 위해 10개의 bit로 표현되며, 이는 1024와 같다.
- 문제 조건 중, 0이 먼저 오는 경우는 발생해선 안되기에, dp[1][j][1<<j]=1로 초기화 시킨다. j는 0을 제외한 1부터 9까지만 삽입한다.

- 모든 비트 연산이 끝난 후, dp[N][x][1024]의 모든 값을 더하면 된다. x는 당연히 0~9까지 범위 전체이다.
*/