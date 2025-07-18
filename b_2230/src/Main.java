import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stn = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stn.nextToken());
        int M = Integer.parseInt(stn.nextToken());
        int [] arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int st= 0, en = 0, answer = Integer.MAX_VALUE;

        while(en<N) { //종료 조건 = en이 배열의 범위를 벗어남 or st가 끝 인덱스에 도달함

            if (arr[en]-arr[st] < M) {
                en++;
                continue;
            }

            if(arr[en]-arr[st]==M) {
                answer=M;
                break;
            }

            answer = Math.min(answer, arr[en]-arr[st]);
            st++;
        }
        System.out.println(answer);
    }
}