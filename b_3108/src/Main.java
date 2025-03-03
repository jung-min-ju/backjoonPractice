import java.io.*;
import java.util.*;

public class Main {
    public static class Square {
        int x1, x2, y1, y2;

        public Square(int x1, int x2, int y1, int y2) {
            this.x1 = (x1 < x2)? x1 : x2;
            this.x2 = (x1 < x2)? x2 : x1;
            this.y1 = (y1 < y2)? y1 : y2;
            this.y2 = (y1 < y2)? y2 : y1;
        }
    }

    //입력된 x가 속한 집합의 대표 원소(루트 노드)를 찾는 역할.
    public static int findSet(int x) {
        if (x == p[x]) return x; //본인이 부모면 반환
        return p[x] = findSet(p[x]);
    }

    public static void union(int x, int y) {
        //1. findSet(x)와 findSet(y)를 호출하여 각각의 대표 원소를 찾은 후
        //2. findSet(y)의 대표 원소를 findSet(x)의 대표 원소로 설정하여 두 집합을 하나로 만든다.
        p[findSet(y)] = findSet(x);
        //y의 루트 노드가 x의 루트 노드를 가리키도록 함으로써 두 집합을 병합하는 것
    }

    public static final int SIZE = 1001; //맵의 크기(1001x1001)를 정의합니다.
    public static final int PLUS = 500; //좌표를 0 이상으로 만들기 위해 500을 더하는 상수입니다.
    public static int N, num, ans; //N : 입력으로 주어지는 사각형의 개수입니다. // num: 현재 처리 중인 사각형의 번호입니다. //ans: 결과로 반환될 사각형 그룹의 수입니다.
    public static boolean zero; //(500, 500) 점이 적어도 하나의 사각형에 포함되는지를 나타내는 플래그입니다.
    public static int[][] map; //격자를 나타내는 2차원 배열로, 특정 좌표가 어떤 사각형에 속하는지를 저장합니다.
    public static int[] p; //Union-Find 알고리즘을 위한 부모 배열입니다.

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[SIZE][SIZE]; //직사각형 크기만큼 최적화 (음수 말고)
        p = new int[SIZE]; //직사각형의 개수만큼 초기화
        for (int i = 0; i < SIZE; i++) {
            p[i] = i;
        }

        ans = N;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())+PLUS;
            int y1 = Integer.parseInt(st.nextToken())+PLUS;
            int x2 = Integer.parseInt(st.nextToken())+PLUS;
            int y2 = Integer.parseInt(st.nextToken())+PLUS;
            num++; //현재 사각형 넘버링
            setSquare(new Square(x1, x2, y1, y2));
        }

        if (zero) ans--;
        System.out.println(ans);
    }

    public static void setSquare(Square square) {

        //세로 구하기
        for (int i = square.y1; i <= square.y2; i++) {
            //현재 사각형의 테두리 좌표가 이미 다른 사각형에 속하는지 확인.
            if (map[square.x1][i] != 0 && map[square.x1][i] != num) {
                //두 사각형이 서로 다른 그룹에 속해 있다면, 그룹을 합쳐 총 그룹의 수를 감소시킴.
                if (findSet(map[square.x1][i]) != findSet(num)) {
                    ans--;
                    union(map[square.x1][i], num);
                }
            }
            if (map[square.x2][i] != 0 && map[square.x2][i] != num) {
                if (findSet(map[square.x2][i]) != findSet(num)) {
                    ans--;
                    union(map[square.x2][i], num);
                }
            }
            map[square.x1][i] = num;
            map[square.x2][i] = num;

            zeroCheck(square.x1, i);
            zeroCheck(square.x2, i);
        }

        //가로 구하기
        for (int i = square.x1; i <= square.x2; i++) {
            if (map[i][square.y1] != 0 && map[i][square.y1] != num) {
                if (findSet(map[i][square.y1]) != findSet(num)) {
                    ans--;
                    union(map[i][square.y1], num);
                }
            }
            if (map[i][square.y2] != 0 && map[i][square.y2] != num) {
                if (findSet(map[i][square.y2]) != findSet(num)) {
                    ans--;
                    union(map[i][square.y2], num);
                }
            }
            map[i][square.y1] = num;
            map[i][square.y2] = num;

            zeroCheck(i, square.y1);
            zeroCheck(i, square.y2);
        }
    }

    public static void zeroCheck(int x, int y) {
        if (x == 500 && y == 500) zero = true;
    }
}