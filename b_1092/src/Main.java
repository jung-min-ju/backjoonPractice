import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> CRANE=new ArrayList<>();
    static List<Integer> BOX=new ArrayList<>();
    static boolean [] USED_CRANE = new boolean[50];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            CRANE.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            BOX.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(CRANE);
        Collections.sort(BOX);

        int time = 0, boxIdx=0;
        boolean impossible=false;

        //현재 박스가 몇 번째 크레인에 들어가야 하는지 확인하기.
        for(int i=boxIdx; i<M;) {
            int boxCount=0;
            for(int j=boxIdx; j<boxIdx+N; j++) {
                if(BOX.get(boxIdx) > CRANE.get(N-1) || BOX.get(boxIdx) < CRANE.get(0)) {
                    impossible = true;
                    break;
                }
                if(binarySearch(BOX.get(boxIdx))) boxCount++;
            }
            if(impossible) break;
            i+=boxCount;
            time++;
        }

        System.out.println(impossible ? -1 : time);
    }

    //현재 인덱스로 들어온 박스 무게를, crain list에서 lower bound로 찾기
    static boolean binarySearch(int value) {
        int st = 0, ed = N - 1, result = -1;

        while (st <= ed) {
            int mid = (st + ed) / 2;

            if (CRANE.get(mid) >= value) {
                result = mid;
                st = mid + 1; // 내림차순 정렬이라 st 증가
            } else {
                ed = mid - 1;
            }
        }

        if (result == -1) return false;

        // result부터 왼쪽으로 가면서 아직 안 쓴 크레인 찾기
        while (result >= 0) {
            if (!USED_CRANE[result]) {
                USED_CRANE[result] = true;
                return true;
            }
            result--;
        }

        return false; // 사용할 수 있는 크레인이 없음
    }


}