import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String s=init();

        Set<String> answer = new HashSet<String>();

        for(int i = 0; i<s.length(); i++){
            for(int j=i+1; j<=s.length(); j++){
                answer.add(s.substring(i,j));
            }
        }

        System.out.println(answer.size());
    }

    private static String init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}