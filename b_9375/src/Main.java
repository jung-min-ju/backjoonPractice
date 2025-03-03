import com.sun.jdi.CharType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-->0) {
            int totalCloth = Integer.parseInt(br.readLine());
            HashMap<String, Integer> cloth = new HashMap<>();

            for(int i=0; i<totalCloth; i++) {
                st=new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                cloth.put(type, cloth.getOrDefault(type,0)+1);
            }
            int answer = 1;

            for(int count : cloth.values()){
                answer*=(count+1);
            }

            System.out.println(answer-1);
        }

    }
}