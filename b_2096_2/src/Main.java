import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] pan = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pan[i][0] = Integer.parseInt(st.nextToken());
            pan[i][1] = Integer.parseInt(st.nextToken());
            pan[i][2] = Integer.parseInt(st.nextToken());
        }

        int[] max = new int[3];
        int[] min = new int[3];

        for (int i = 0; i < 3; i++) {
            max[i] = pan[0][i];
            min[i] = pan[0][i];
        }

        if (N >= 2) {
            for (int i = 1; i < N; i++) {
                int prevMax0 = max[0], prevMax1 = max[1], prevMax2 = max[2];
                int prevMin0 = min[0], prevMin1 = min[1], prevMin2 = min[2];

                max[0] = pan[i][0] + Math.max(prevMax0, prevMax1);
                max[1] = pan[i][1] + Math.max(Math.max(prevMax0, prevMax1), prevMax2);
                max[2] = pan[i][2] + Math.max(prevMax1, prevMax2);

                min[0] = pan[i][0] + Math.min(prevMin0, prevMin1);
                min[1] = pan[i][1] + Math.min(Math.min(prevMin0, prevMin1), prevMin2);
                min[2] = pan[i][2] + Math.min(prevMin1, prevMin2);
            }
        }

        int maxAnswer = Math.max(Math.max(max[0], max[1]), max[2]);
        int minAnswer = Math.min(Math.min(min[0], min[1]), min[2]);

        System.out.println(maxAnswer + " " + minAnswer);
    }
}
