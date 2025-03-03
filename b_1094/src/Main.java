import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int answer [];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int stNum = Integer.parseInt(br.readLine());
        int [][] classInfo = new int[stNum][5];
        answer = new int[stNum];

        for(int i=0; i<stNum; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                classInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //구현
        int max = 0;
        int leader = 0;

        for(int i=0; i<stNum; i++){
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j<5; j++) {
                for (int k = 0; k < stNum; k++) {
                    if (classInfo[i][j] == classInfo[k][j] && i != k) {
                        set.add(k);
                    }
                }
            }
            if(set.size()>max) {
                leader = i;
                max = set.size();
            }
        }

        System.out.println(leader + 1);
    }
}