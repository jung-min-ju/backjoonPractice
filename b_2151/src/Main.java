import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Info {
    int x;
    int y;
    int mirror;
    int dir;

    public Info(int x, int y, int mirror, int dir) {
        this.x=x;
        this.y=y;
        this.mirror=mirror;
        this.dir=dir;
    }
}


public class Main {

    static boolean in(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int [] dirX = {0,1,0,-1}; //방향 : 우,하,좌,상
        int [] dirY = {1,0,-1,0};

        int n = Integer.parseInt(br.readLine());
        char [][] map = new char[n][n];

        // dist[x][y][dir] = (x,y,dir)로 도달할 때 최소 거울 수
        final int INF = Integer.MAX_VALUE;
        int [][][] dist = new int[n][n][4];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) Arrays.fill(dist[i][j], INF);

        Queue<Info> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.mirror));

        boolean flag = false;
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<n; j++){
                if(map[i][j]=='#'&&!flag) {
                    map[i][j] = '*';
                    flag=true;
                    for(int k=0; k<4; k++){
                        int nextX = i+dirX[k];
                        int nextY = j+dirY[k];
                        if(in(nextX, nextY, n) && map[nextX][nextY]!='*') {
                            queue.add(new Info(nextX, nextY, 0, k));
                            dist[nextX][nextY][k] = 0;
                        }
                    }
                }
            }
        }

        int answer=0;
        while (!queue.isEmpty()){
            Info now = queue.poll();

            // 도착이면 최소 거울 수 보장
            if (map[now.x][now.y] == '#') {
                answer = now.mirror;
                break;
            }
            if (map[now.x][now.y] == '*') continue;

            int nextX = now.x+dirX[now.dir];
            int nextY = now.y+dirY[now.dir];
            if(in(nextX, nextY, n) && map[nextX][nextY]!='*' && dist[nextX][nextY][now.dir] > now.mirror) {
                queue.add(new Info(nextX, nextY, now.mirror, now.dir));
                dist[nextX][nextY][now.dir] = now.mirror;
            }

            if(map[now.x][now.y]=='!') {
                //거울 설치가 가능한 경우2 : 현재 방향에서 90도 방향
                int nextDir = (now.dir+1)%4; //0 -> 1 // 1->2 //
                nextX = now.x+dirX[nextDir];
                nextY = now.y+dirY[nextDir];

                if(in(nextX, nextY, n) && map[nextX][nextY]!='*' && dist[nextX][nextY][nextDir] > now.mirror+1) {
                    queue.add(new Info(nextX, nextY, now.mirror+1, nextDir));
                    dist[nextX][nextY][nextDir] = now.mirror+1;
                }

                //거울 설치가 가능한 경우3 : 경우2에서 꺾은 90도의 반대 방향
                nextDir = (now.dir + 3) % 4; //1->3
                nextX = now.x+dirX[nextDir];
                nextY = now.y+dirY[nextDir];

                if(in(nextX, nextY, n) && map[nextX][nextY]!='*' && dist[nextX][nextY][nextDir] > now.mirror+1) {
                    queue.add(new Info(nextX, nextY, now.mirror+1, nextDir));
                    dist[nextX][nextY][nextDir] = now.mirror+1;
                }
            }


        }

        System.out.println(answer);

    }

}

/*
1. 문제요약
   - 집의 크기 N*N이 주어지고, 문이 2개 있다.
   - 한 쪽 문에서 다른 쪽 문을 볼 수 있도록 거울을 설치한다.
   - 거울은 45도 기울어진 대각선 방향으로 설치한다. (즉 90도 꺾인채 빛이 굴절됨)
   - 거울은 양면이기에 양 쪽 모두에서 반사가 생긴다. (거울은 무제한)

2. 입출력
   [입력]
   - 집의 크기 N (2 <= N <= 50)
   - #은 문 설치 위치, .은 빈 공간, !은 거울 설치 가능 위치, *은 벽

   [출력]
   - 설치해야 하는 거울의 최소 개수

3. 테스트케이스
   5
   ***#*
   *.!.*
   *!.!*
   *.!.*
   *#***

    4. 알고리즘
       - bfs로 문 한 곳에서 출발. 도착지는 또 다른 #에 도달할 때까지
       - 3차원 배열 필요 (동, 서, 남, 북 방향 표시)
       - Info Class 생성 : 현재 설치된 거울 개수, 현재 x, 현재 y, 현재 방향 d

       [while 문 내부]
       - map[x][y][d] = true 부터 함.
       - 현재 도착지가 '*' 이라면 넘어감
       - 현재 도착지가 '.' 이라면 일자로 뻗은 다음 위치의 x,y,dir 큐에 넣고 넘어감.
       - 현재 도착지가 '!' 이라면
           -> 현재 방향으로 간 적 있는지 확인 (있다면 return)
           -> 현재 방향으로 간 적 없다면
              map[x][y][90도 방향], map[x][y][90도 꺾은 거 반대 방향] 큐에 넣어줌
           -> 거울 설치하지 않고 그냥 일직선으로 직진하는 경우도 큐에 넣어줌.
       - 현재 도착지가 '#' 이라면
           -> 현재 Info 속 거울 개수를 정답으로 등록 후 while 문 종료
    */

/*
 *     상(3)
 * 좌(2)   우(0)
 *     하(1)
 *
 *
 *
 * */