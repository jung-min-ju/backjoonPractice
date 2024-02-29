import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            BigInteger a = new BigInteger(st.nextToken(),2);
            BigInteger b = new BigInteger(st.nextToken(),2);

            BigInteger sum = a.add(b);

            System.out.println(sum.toString(2));
        }
    }
}

