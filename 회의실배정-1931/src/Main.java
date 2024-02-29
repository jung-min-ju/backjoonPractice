import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int [][] officeArray;
    public static void main(String[] args)  throws IOException {
        init();

        Arrays.sort(officeArray, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0]; // endTime이 같을 경우 startTime을 기준으로 정렬
            } else {
                return a[1] - b[1]; // endTime을 기준으로 정렬
            }
        });

        int endTime=0, count=0;

        for(int i=0; i<officeArray.length; i++){
            if(officeArray[i][0]>=endTime){
                count++;
                endTime = officeArray[i][1];
            }
        }

        System.out.println(count);
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        officeArray = new int[N][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            officeArray[i][0] = Integer.parseInt(st.nextToken());
            officeArray[i][1] = Integer.parseInt(st.nextToken());
        }
    }

}

