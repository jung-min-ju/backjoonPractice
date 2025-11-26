import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Info {
    int North = 0;
    int South = 0;
    int West = 0;
    int East = 0;
    int val = 0;

    public Info(int east, int west, int north, int south, int val) {
        this.North = north;
        this.South = south;
        this.West = west;
        this.East = east;
        this.val = val;
    }

    public Info() {}

}

public class Main {
    static int N, M;
    static int map[][];
    static int dirX[] = {0, 0, 0, -1, 1}; // 동-1, 서-2, 북-3, 남-4
    static int dirY[] = {0, 1, -1, 0, 0};
    static Info[] dice;
    static int[] top = {0,6,5,4,3,2,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new Info[7];

        for (int i = 1; i <= 6; i++) {
            dice[i] = new Info();
        }

        //주사위 초기 상태
        dice[1] = new Info(3, 4, 2, 5, 0);

        for (int i = 0; i < N; i++) {
            String s[] = br.readLine().split(" ");

            map[i] = Arrays.stream(s)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int nowDiceNum = 1;

        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            int moveX = x + dirX[dir];
            int moveY = y + dirY[dir];

            if (!arrange(moveX, moveY)) {
                continue;
            }

            x = moveX;
            y = moveY;
            //다음 주사위 동서북남 계산
            int moveDiceNum = calCulNextDiceDir(nowDiceNum, dir);
            Info moveDice = dice[moveDiceNum];

            //굴린 주사위 val 갱신
            calculateMap(moveDice, x, y);

            //현재 짝궁 val 출력
            Info coupleDice = dice[top[moveDiceNum]];
            sb.append(coupleDice.val + "\n");
            nowDiceNum = moveDiceNum;
        }

        System.out.println(sb);
    }

    static void calculateMap(Info dice, int x, int y) {
        if (map[x][y] == 0) {
            map[x][y] = dice.val;
        } else {
            dice.val = map[x][y];
            map[x][y] = 0;
        }
    }

    static int calCulNextDiceDir(int nowDiceNum, int dir) {
        int east = 0;
        int west = 0;
        int north = 0;
        int south = 0;
        Info nowDice = dice[nowDiceNum];
        int nextDiceNum = 0;

        if (dir == 1 || dir == 2) {
            north = nowDice.North;
            south = nowDice.South;

            if (dir == 1) { //동
                east = top[nowDiceNum];
                west = nowDiceNum;
                nextDiceNum = nowDice.East;
            }
            if (dir == 2) { //서
                east = nowDiceNum;
                west = top[nowDiceNum];
                nextDiceNum = nowDice.West;
            }
        } else {
            east = nowDice.East;
            west = nowDice.West;

            if (dir == 3) { //북
                north = top[nowDiceNum];
                south = nowDiceNum;
                nextDiceNum = nowDice.North;
            }
            if (dir == 4) { //남
                north = nowDiceNum;
                south = top[nowDiceNum];
                nextDiceNum = nowDice.South;
            }
        }
        Info nextDice = dice[nextDiceNum];
        nextDice.East = east;
        nextDice.West = west;
        nextDice.North = north;
        nextDice.South = south;

        return nextDiceNum;
    }

    static boolean arrange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) {
            return false;
        }
        return true;
    }

}
