import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Info  {
    int x;
    int y;
    int move;

    public Info(int x, int y, int move) {
        this.x=x;
        this.y=y;
        this.move=move;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [][] move = new int[N+2][N+2];
        char [][] map = new char[N+2][N+2];
        int answer = 0;
        int dirX[] = {0,0,1,-1};
        int dirY[] = {-1,1,0,0};
        Queue<Info> queue = new PriorityQueue<>(Comparator.comparingInt((Info o) -> o.move));

        Arrays.fill(map[0],'1');
        Arrays.fill(map[N+1],'1');

        for(int j=0; j<=N+1; j++) {
            map[0][j] = '1';
            map[N+1][j] = '1';
        }

        for(int i=0; i<N+2; i++){
            Arrays.fill(move[i], Integer.MAX_VALUE);
        }

        for(int i=1; i<=N; i++){
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = line.charAt(j-1);
                if(map[i][j]=='S') {
                    queue.add(new Info(i,j,0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Info now = queue.poll();
            move[now.x][now.y]=now.move;

            if(map[now.x][now.y]=='K') {
                M--;
                map[now.x][now.y]='0';
                answer+=now.move;
                now.move=0;
            }
            if(M==0) break;

            for(int i=0; i<4; i++){
                int nextX = now.x+dirX[i];
                int nextY = now.y+dirY[i];

                if(map[nextX][nextY]=='1') continue;
                if(move[nextX][nextY]< now.move+1) continue;
                queue.add(new Info(nextX, nextY,now.move+1));
            }
        }
        System.out.println(M==0 ? answer : -1);
    }
}


/*
* 1. 문제 요약
* - 로봇은 열쇠 M개를 찾아야 한다.
* - 로봇은 복제 가능하다.
*    - 열쇠가 있는 위치 및 로봇 출발 위치에서만 복제 가능하다.
* - 미로는 N*N 이고, 벽(1)과 움직일 수 있는 길(0)이 존재한다.
* - 로봇이 모든 열쇠를 다 찾는 최소의 움직임을 구하라. 불가능하다면 -1을 출력하라.
*
* 2. 조건
* - 로봇은 상하좌우 방향으로 움직인다.
* - 해당 위치에 도착해야만 열쇠를 찾은 것이다.
* - 로봇의 움직임 = 복제된 로봇들의 움직임의 합
* - 열쇠를 찾는 그 즉시 종료하면 됨.
* - 한 곳에 로봇이 다시 지나갈 수 있음
* - 하나의 칸에 동시에 여러 로봇이 존재 가능함
*
* 3. 입출력
* [입력]
* - N(4<=N<=50)
* - M(1<=M<=250)
*
* [출력]
* - 로봇이 모든 열쇠를 다 찾는 최소의 움직임을 구하라. 불가능하다면 -1을 출력하라.
*
* 4. 알고리즘
* : 우선순위큐를 이용해보면 될 것 같은데? -> bfs
*
* 1. 필요한 자료구조
* - move [][][] = new int[N][N][4] 배열 형성. -> 현재 위치 특정 방향에 도달 위한 최소 움직임 기록 위함
* - map [][] = new char[N+2][N+2] 배열 형성 -> map 기록 용
* - 움직임 순으로 오름차순 정렬된 우선순위 큐
*
* 1. 우선순위큐에서 아래 조건 만족 시, 현재 위치에서 4방향의 움직인 경우를 모두 넣음.
* - 다음 위치가 벽임 아님.
* -  현재 가려는 위치에 기록된 움직임 < 현재 나의 움직임 이면 못 감. >=면 움직이기 가능
* 2. k에 도달했다면,
*   - 해당 위치까지 오는 데 걸린 움직임을 answer에 추가함.
*   - 해당 K는 0으로 변경.
*   - 찾아야 하는 열쇠 개수--
*   - 로봇 복제 가능하기에 다음 4가지 방향의 움직임음 0으로 초기화 해서 pq에 넣음
* 3. 열쇠 개수가 --에 달했을 시, answer 출력, 만약 큐가 끝났음에도 열쇠 개수가 남았다면 -1 출력
*
*
*
* * */