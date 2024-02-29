import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for(int i=0;i<testCase; i++){
            int total = 0;
            int count = 1;
            String s = br.readLine();
            for(int j=0; j<s.length(); j++){
                if(s.charAt(j)=='O') {
                    total+=count++;
                }
                else {
                    count=1;
                }
             }
            sb.append(total+"\n");
        }
        System.out.println(sb);
    }
}