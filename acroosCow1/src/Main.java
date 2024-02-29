import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> cowInfo = new HashMap<String, String>();
        StringTokenizer st;
        int answer = 0;

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String num = st.nextToken();
            String location = st.nextToken();

            if (Objects.equals(cowInfo.get(num), null)) {
                cowInfo.put(num, location);
                continue;
            }

            String exLocation = cowInfo.get(num);
            if(!exLocation.equals(location)){
                cowInfo.put(num,location);
                answer++;
            }

        }
        System.out.println(answer);
    }
}