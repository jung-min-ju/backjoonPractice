import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Shark {
        private int dir; //1(상), 2(하), 3(우), 4(좌)
        private int speed;
        private int row;
        private int col;
        private int size;
        private boolean isExiting;
        private boolean isMove;

        public Shark(int r, int c, int s, int d, int z) {
            this.row = r;
            this.col = c;
            this.speed = s;
            this.dir = d;
            this.size = z;
            isExiting = true;
            isMove=false;
        }

        public int goStart() { //출발점으로 얼마나 땡겨지면 되는지 계산해주기
            //역방향즈
            if( this.dir==1||this.dir==4 )  return this.dir==1 ? R-(this.row+1) : C-(this.col+1);
            //정방향즈
            else  return this.dir==2 ? this.row : this.col;
        }

        public int goEnd() {
            //역방향즈
            if( this.dir==1||this.dir==4 )  return this.dir==1 ? this.row : this.col;
            //정방향즈
            else  return this.dir==2 ? R-(this.row+1) : C-(this.col+1);
        }

    }

    static Shark [][] place;
    static int R, C, M, total;
    static Queue<Shark> sharks;

    public static void main(String[] args) throws IOException {
        init();

        for(int i=0; i<C; i++) {
            fishingShark(i);
            changeShark();
        }
        System.out.println(total);

    }

    static void fishingShark (int personC) {
        for(int i=0; i<R; i++) {
            if(place[i][personC]!=null) {
                Shark fishedShark = place[i][personC];
                if(!fishedShark.isExiting) continue;
                fishedShark.isExiting = false;
                total+=fishedShark.size;
                place[i][personC]=null;
                break;
            }
        }
    }

    static void changeShark() {
        //상어 이동 시키기
        int sharkNum = sharks.size();

        for(int i=0; i<sharkNum; i++) {
            Shark nowShark = sharks.poll();
            Shark exPlaceShark = place[nowShark.row][nowShark.col];

            if(exPlaceShark==nowShark) place[nowShark.row][nowShark.col]=null;
            if(nowShark.isMove || !nowShark.isExiting) continue; //현재 상어의 이동이 이미 끝났거나, 존재하지 않으면 pass

            //상어 움직이기 전 기본세팅
            boolean isStraight = ( nowShark.dir==2 || nowShark.dir==3 ) ? true : false; //정방향 (하,우) - true / 역방향 (상, 좌) - false
            boolean isUpDown = nowShark.dir<=2 ? true : false; //상하 (true) 좌우(false)
            int totalRange = isUpDown ? R : C;
            int goStart = nowShark.goStart();

            int wallCount=0;
            int isWall = nowShark.speed-nowShark.goEnd();
            if(isWall > 0) { //무조건 상어가 벽에 부딪힐 때
                wallCount = (isWall/(totalRange-1)) + 1;
                if(isWall%(totalRange-1)==0) wallCount--;
            }

            boolean isChangeDir = wallCount%2==0 ? false : true;
            if(isChangeDir){
                if(nowShark.dir<=2)  nowShark.dir = nowShark.dir==1?2:1;
                else nowShark.dir = nowShark.dir==3?4:3;
            }

            //상어 위치 인덱스 계산하기
            if(wallCount==0) {
                int direction = isStraight ? 1 : -1;
                if (isUpDown) {
                    nowShark.row += direction * nowShark.speed;
                } else {
                    nowShark.col += direction * nowShark.speed;
                }
            } else {
                int nextDex = (((nowShark.speed+wallCount)%totalRange)+goStart)%totalRange;
                if(nowShark.dir==2||nowShark.dir==3) { //최종적으로 상어가 정방향을 바라볼 때
                    if(isUpDown){ //상하
                        nowShark.row = nextDex;
                    } else { //좌우
                        nowShark.col = nextDex;
                    }
                } else { //최종적으로 상어가 역방향을 바라볼 때
                    if(isUpDown){ //상하
                        nowShark.row = totalRange-nextDex-1;
                    } else { //좌우
                        nowShark.col = totalRange-nextDex-1;
                    }
                }
            }

            //상어 잡아먹기
            if(place[nowShark.row][nowShark.col]!=null) { //해당 인덱스에 죽지 않은 상어가 있는 상황
                Shark nextPlaceShark = place[nowShark.row][nowShark.col];
                if(nextPlaceShark.isMove==false) { //해당 상어가 이동 전이면
                    place[nowShark.row][nowShark.col] = nowShark; //크기 무시하고 일단 할당(어차피 해당 상어 이동 때 계산됨)
                }
                else { //기존 상어가 방문처리 된 상어라면?
                    if(nextPlaceShark.size < nowShark.size) { //새로운 상어가 더 큰 경우
                        place[nowShark.row][nowShark.col] = nowShark;
                        nextPlaceShark.isExiting=false;
                    } else nowShark.isExiting=false; //기존 상어가 더 큰 경우
                }
            } else place[nowShark.row][nowShark.col] = nowShark;

            //큐에 상어 다시 넣기
            sharks.add(nowShark);
            //해당 상어 방문 처리
            nowShark.isMove=true;
        }

        //상어들 다시 move = false 처리
        int sharkSize= sharks.size();
        for(int i=0; i<sharkSize; i++){
            Shark nowShark = sharks.poll();
            if(!nowShark.isExiting) continue;
            nowShark.isMove=false;
            sharks.add(nowShark);
        }

    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        place = new Shark[R][C];

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++) {
                place[i][j] = null;
            }
        }

        sharks=new ArrayDeque<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(r-1,c-1,s,d,z);
            sharks.add(shark); //상어 한 번에 찾기 위함
            place[r-1][c-1] = shark; //나중에 가까운 상어 + 큰 상어 계산 하기 위함
        }
    }
}