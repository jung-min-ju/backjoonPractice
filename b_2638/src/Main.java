import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
    int x;
    int y;

    public Info(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int[][] pan;
    static boolean[][] visited;
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pan = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                pan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][M];
            markOutsideAir();

            Queue<Info> meltQueue = findMeltCheese();
            if (meltQueue.isEmpty()) break;

            while (!meltQueue.isEmpty()) {
                Info now = meltQueue.poll();
                pan[now.x][now.y] = 0;
            }
            time++;
        }

        System.out.println(time);
    }

    static void markOutsideAir() {
        Queue<Info> q = new ArrayDeque<>();
        q.add(new Info(0, 0));
        visited[0][0] = true;
        pan[0][0] = 2;

        while (!q.isEmpty()) {
            Info now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dirX[d];
                int ny = now.y + dirY[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (!visited[nx][ny] && pan[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    pan[nx][ny] = 2;
                    q.add(new Info(nx, ny));
                }
            }
        }
    }

    static Queue<Info> findMeltCheese() {
        Queue<Info> meltQueue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (pan[i][j] == 1 && canMelt(i, j)) {
                    meltQueue.add(new Info(i, j));
                }
            }
        }
        return meltQueue;
    }

    static boolean canMelt(int x, int y) {
        int airCnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirX[d];
            int ny = y + dirY[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (pan[nx][ny] == 2) airCnt++;
        }
        return airCnt >= 2;
    }
}
