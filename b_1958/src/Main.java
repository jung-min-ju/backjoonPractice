import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();

        int [][][]count = new int[101][101][101];

        // DP 테이블 갱신
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 1; k <= s3.length(); k++) {
                    // 세 문자열의 해당 문자가 모두 일치하는 경우
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) && s2.charAt(j - 1) == s3.charAt(k - 1)) {
                        count[i][j][k] = count[i - 1][j - 1][k - 1] + 1;
                    } else {
                        // 세 가지 방향 중 최댓값을 선택
                        count[i][j][k] = Math.max(Math.max(count[i - 1][j][k], count[i][j - 1][k]), Math.max(count[i][j][k - 1], count[i - 1][j - 1][k - 1]));
                    }
                }
            }
        }

        // 결과 출력: 세 문자열에서 가장 긴 공통 부분 수열의 길이
        System.out.println(count[s1.length()][s2.length()][s3.length()]);
    }
}
