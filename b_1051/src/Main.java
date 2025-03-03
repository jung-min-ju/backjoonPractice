import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] pan = new char[N][M];

        // ⚠️ N번 반복해야 함
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            pan[i] = s.toCharArray();
        }

        int size = 1; // 최소한 1x1 크기의 정사각형이 가능함
        for (int k = 0; k < Math.min(N, M); k++) {
            for (int i = 0; i < N - k; i++) { // 범위 초과 방지
                for (int j = 0; j < M - k; j++) { // 범위 초과 방지
                    if (pan[i][j] == pan[i + k][j + k] &&
                            pan[i + k][j] == pan[i][j + k] &&
                            pan[i][j] == pan[i + k][j]) {
                        // 정사각형 넓이 계산 (한 변의 길이 = k + 1)
                        size = Math.max(size, (k + 1) * (k + 1));
                    }
                }
            }
        }

        System.out.println(size);
    }
}
