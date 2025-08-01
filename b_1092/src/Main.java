import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N,M;
    public static Integer[] crain, box;
    public static int answer =0 ;
    public static boolean[] visited;
    public static int[] crain_positions;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        crain = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            crain[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        box = new Integer[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }

        //내림차순으로 정렬하여야, 큰것들부터 처리합니다. (큰 크레인이 작은것들을 처리하는 일이 없어야만 합니다.)
        Arrays.sort(crain, Collections.reverseOrder());
        Arrays.sort(box, Collections.reverseOrder());

        if(crain[0] < box[0]) {
            System.out.println("-1");
            return;
        }

        crain_positions = new int[N];
        visited = new boolean[M];
        //박스를 모두 옮기기전까지 작동
        while(M > 0) {

            //각 크레인이 순회
            for(int i=0;i<N;i++) {
                if(M == 0) break;

                for(int j=crain_positions[i];j<box.length;j++) {
                    if(visited[j] == true) continue;
                    if(crain[i] < box[j]) {
                        crain_positions[i]++;
                    }
                    else if(crain[i] >= box[j]) {
                        visited[j] = true;
                        M--;
                        break;
                    }
                }

            }
            answer++;
        }
        System.out.println(answer);

    }
}