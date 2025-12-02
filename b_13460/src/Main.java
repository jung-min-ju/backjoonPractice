import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
    int rx;
    int ry;
    int bx;
    int by;
    boolean rHole;
    boolean bHole;
    int move;

    public Info() {
    }

    public Info(int rx, int ry, int bx, int by, boolean rHole, boolean bHole, int move) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.rHole = rHole;
        this.bHole = bHole;
        this.move = move;
    }
}

public class Main {
    static int N, M;
    static char pan[][];
    static boolean visited[][][][];
    static int[] dirX = {-1, 0, 1, 0}; //상,우,하,좌
    static int[] dirY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pan = new char[N][M];
        visited= new boolean[N][M][N][M];

        Info start = new Info();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                pan[i][j] = s.charAt(j);
                if (pan[i][j] == 'R') {
                    start.rx = i;
                    start.ry = j;
                }
                if (pan[i][j] == 'B') {
                    start.bx = i;
                    start.by = j;
                }
            }
        }

        Queue<Info> queue = new ArrayDeque<>();
        queue.add(start);
        int answer = -1;
        while (!queue.isEmpty()) {
            Info now = queue.poll();

            if (pan[now.rx][now.ry] == 'O') {
                if (now.rHole && !now.bHole) {
                    answer = now.move;
                    break;
                }
            }
            for (int dir = 0; dir < 4; dir++) { //상,우,하,좌
                //기본은 red가 선이동, blue가 선 이동하는 경우 따로 처리
                Info next = new Info();
                if (now.rx == now.bx) { //행이 같을때 -> 좌우 순서
                    if (dir == 1 && now.by > now.ry) {//오른쪽으로 움직이되 blue가 선
                        moveBlue(now, next, dir);
                        moveRed(now, next, dir);
                        next.move = now.move + 1;
                        if (next.bHole||visited[next.rx][next.ry][next.bx][next.by]) continue;
                        visited[next.rx][next.ry][next.bx][next.by] = true;
                        queue.add(next);
                        continue;
                    }
                    if (dir == 3 && now.by < now.ry) { //왼쪽으로 움직이되 blue가 선
                        moveBlue(now, next, dir);

                        moveRed(now, next, dir);
                        next.move = now.move + 1;
                        if (next.bHole||visited[next.rx][next.ry][next.bx][next.by]) continue;
                        visited[next.rx][next.ry][next.bx][next.by] = true;
                        queue.add(next);
                        continue;
                    }
                }
                if (now.ry == now.by) { //행이 같을때 -> 좌우 순서
                    if (dir == 0 && now.bx < now.rx) {//위로 움직이되 red가 선
                        moveBlue(now, next, dir);
                        moveRed(now, next, dir);
                        next.move = now.move + 1;
                        if (next.bHole||visited[next.rx][next.ry][next.bx][next.by]) continue;
                        visited[next.rx][next.ry][next.bx][next.by] = true;
                        queue.add(next);
                        continue;
                    }
                    if (dir == 2 && now.bx > now.rx) { //아래로 움직이되 blue가 선
                        moveBlue(now, next, dir);
                        moveRed(now, next, dir);
                        next.move = now.move + 1;
                        if (next.bHole||visited[next.rx][next.ry][next.bx][next.by]) continue;
                        visited[next.rx][next.ry][next.bx][next.by] = true;
                        queue.add(next);
                        continue;
                    }
                }
                moveRed(now, next, dir);
                moveBlue(now, next, dir);
                next.move = now.move + 1;
                if (next.bHole||visited[next.rx][next.ry][next.bx][next.by]) continue;
                visited[next.rx][next.ry][next.bx][next.by] = true;
                queue.add(next);
            }
        }
        System.out.println(answer>10?-1:answer);
    }

    static void moveRed(Info now, Info next, int dir) {
        int nowX = now.rx;
        int nowY = now.ry;
        int nextX;
        int nextY;
        while (true) {
            nextX = nowX + dirX[dir];
            nextY = nowY + dirY[dir];
            if (pan[nextX][nextY] == '#') {
                next.rx = nowX;
                next.ry = nowY;
                return;
            }

            if (pan[nextX][nextY] == 'O') {
                next.rHole = true;
                next.rx = nextX;
                next.ry = nextY;
                return;
            }
            if (nextX == next.bx && nextY == next.by) {  //blue가 holel에 들어가지 않았고, 다음 도착지에 blue가 이미 있다면
                next.rx = nowX;
                next.ry = nowY;
                return;
            }
            nowX = nextX;
            nowY = nextY;
        }
    }

    static void moveBlue(Info now, Info next, int dir) {
        int nowX = now.bx;
        int nowY = now.by;
        int nextX;
        int nextY;
        while (true) {
            nextX = nowX + dirX[dir];
            nextY = nowY + dirY[dir];
            if (pan[nextX][nextY] == '#') {
                next.bx = nowX;
                next.by = nowY;
                break;
            }
            if (pan[nextX][nextY] == 'O') {
                next.bHole = true;
                next.bx = nextX;
                next.by = nextY;
                break;
            }
            if (nextX == next.rx && nextY == next.ry) { //red가 holel에 들어가지 않았고, 다음 도착지에 red가 이미 있다면
                next.bx = nowX;
                next.by = nowY;
                break;
            }
            nowX = nextX;
            nowY = nextY;
        }
    }
}
