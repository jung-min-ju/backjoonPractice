import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [][] A = new int[N][M];
        int [][] B = new int[N][M];

        for(int i=0; i<N; i++){
            A[i]= Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for(int i=0; i<N; i++){
            B[i]= Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int answer = 0;
        boolean black = false;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                if(A[i][j]==B[i][j]) continue;
                if(j>M-3 || i>N-3) {
                    if(A[i][j]!=B[i][j]) black=true;
                    continue;
                }
                answer++;
                for(int changeI=i; changeI<i+3; changeI++){
                    for(int changeJ=j; changeJ<j+3; changeJ++) {
                        A[changeI][changeJ] = A[changeI][changeJ]==0 ? 1 : 0;
                    }
                }
            }
        }

        System.out.println(black ?-1:answer);
    }

}

/*
1. 문제요약
- 0과 1로 이루어진 행열 A, B 존재
- 행렬을 A->B 로 변환하는 연산 최솟값
- 행렬 변환 연산 = 3X3 크기의 부분행렬의 모든 원소를 뒤집는다.

2. 입출력
[입력]
- 행렬의 크기 N, M (1<=N,M, 50)
- 둘째줄~N까지 행렬A
- 나머지줄 행렬 B

[출력]
- 정답이 없다면 -1

3. 테스트케이스
3 4
0000
0010
0000

1001
1011
1001

1)
1110
1100
1110

2)
1001
1011
1001

4. 알고리즘
1. 0,0 부터, N, M 까지 하나씩 옮겨가며 B와 비교
2. B와 동일하면 pass, B와 다르면 3X3 범위 모두 바꿔줌
3. 3*3 영역으로 넓힐 수 없는 구간 (j>M-3 || i>N-3)인데 현재 A원소와 B가 다른 원소라면, -1 출력
*
* */