import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


class Info {
    int x;
    int y;
    int num;
    int idx;

    public Info(int x, int y, int num, int idx) {
        this.x = x;
        this.y = y;
        this.num = num;
        this.idx=idx;
    }
}

public class Main {
    static int N, M;
    static List<Info> cctvList;
    static int[] dirX = {-1, 0, 1, 0}; //상, 우, 하, 좌
    static int[] dirY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvList = new LinkedList<>();
        int [][] pan = new int[N][M];
        //입력값을 받으며, 1~5 번호가 들어오면 cctvList에 넣기.
        int wallCnt = 0;
        int infoIdx = 1;
        for (int i = 0; i < N; i++) {
            String s[] = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(s[j]);
                if (value == 0) {
                    continue;
                }
                if (value == 6) {
                    pan[i][j] = -1;
                    wallCnt++;
                    continue;
                }
                pan[i][j] = infoIdx;
                cctvList.add(new Info(i, j, value, infoIdx++));
            }
        }

        int maxSeeingCnt = cctv(pan, 0);
        int answer = (N*M) - wallCnt - cctvList.size() - maxSeeingCnt;

        System.out.println(answer);
    }

    static int cctv(int[][] pan, int listIdx) {
        //listIdx가 cctvList 사이즈라면 재귀 종료.
        if (listIdx == cctvList.size()) {
            return 0;
        }

        //현재 cctvList[cctvList] 로 현재 cctv 정보 x,y,num 정보 가져옴.
        Info now = cctvList.get(listIdx);

        int[][] newPan = new int[N][M];
        for (int i = 0; i < N; i++) {
            newPan[i] = Arrays.copyOf(pan[i], M);
        }

        //num 별로 cctv 돌리는 경우의 수만큼 방향 돌림
        //주어진 num에 맞게, 현재 몇 개의 칸을 가릴 수 있는지 계산
        //listIdx++, 인자값으로 받은 hide + 현재 숨긴 hide 재귀함수로 넘김
        int maxHide = 0;
        if (now.num == 1) {
            for (int i = 0; i < 4; i++) {
                int nowHide = drawing(newPan, now, i);
                maxHide = Math.max(cctv(newPan, listIdx + 1)+nowHide, maxHide);
                erasing(newPan, now, i);
            }
        } else if (now.num == 2) {
            for (int i = 0; i < 2; i++) {
                int nowHide = drawing(newPan, now, i);
                nowHide += drawing(newPan, now, i + 2);
                maxHide = Math.max(cctv(newPan, listIdx + 1)+nowHide, maxHide);
                erasing(newPan, now, i);
                erasing(newPan, now, i + 2);
            }
        } else if (now.num == 3) {
            for (int i = 0; i < 4; i++) {
                int nowHide = drawing(newPan, now, i);
                nowHide += drawing(newPan, now, (i + 1)%4);
                maxHide = Math.max(cctv(newPan, listIdx + 1)+nowHide, maxHide);
                erasing(newPan, now, i);
                erasing(newPan, now, (i + 1)%4);
            }
        } else if (now.num == 4) {
            for (int i = 0; i < 4; i++) {
                int nowHide = drawing(newPan, now, (i % 4));
                nowHide += drawing(newPan, now, (i + 1) % 4);
                nowHide += drawing(newPan, now, (i + 3) % 4);
                maxHide = Math.max(cctv(newPan, listIdx + 1)+nowHide, maxHide);
                erasing(newPan, now, (i % 4));
                erasing(newPan, now, (i + 1) % 4);
                erasing(newPan, now, (i + 3) % 4);
            }
        } else {
            int nowHide = drawing(newPan, now, 0);
            nowHide += drawing(newPan, now, 1);
            nowHide += drawing(newPan, now, 2);
            nowHide += drawing(newPan, now, 3);
            maxHide = Math.max(cctv(newPan, listIdx + 1)+nowHide, maxHide);
        }

        //최종 가장 많은 hide 값을 return 시킴.
        return maxHide;
    }

    static int drawing(int[][] newPan, Info now, int dir) { //0,1,2,3 = 상,우,하,좌
        int hide = 0;
        int x = now.x;
        int y = now.y;
        int idx = now.idx;

        while (true) {
            x = x + dirX[dir];
            y = y + dirY[dir];
            if (x >= N || y >= M || x < 0 || y < 0) {
                break;
            }
            if (newPan[x][y]==-1) {
                break;
            }
            if(newPan[x][y]==0) {
                newPan[x][y] = idx; //이전 거 덮어씌움 x -> erasing 시 불편함.
                hide++;
            }
        }
        return hide;
    }

    static void erasing(int[][] newPan, Info now, int dir) {
        int x = now.x;
        int y = now.y;
        int idx = now.idx;

        while (true) {
            x = x + dirX[dir];
            y = y + dirY[dir];
            if (x >= N || y >= M || x < 0 || y < 0) {
                break;
            }
            if (newPan[x][y]==-1) {
                break;
            }
            if(newPan[x][y]==idx) {
                newPan[x][y] = 0; //이전 거 지움 x
            }
        }

    }
}
