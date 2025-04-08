import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [] T = new int[N+1];
        int [] P = new int[N+1];
        int [] stackP = new int[N+1];
        int answer = 0;

        List<Integer> [] list = new List[N+1];

        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());

            if(i+T[i]-1<N) list[i+T[i]-1].add(i);
        }

        for(int i=1; i<=N; i++) {
            int maxExP = 0;

            for(int ex : list[i]){
                maxExP = Math.max(stackP[ex], maxExP);
            }

            if(list[i].size()==0) maxExP = answer;

            stackP[i] = maxExP;

            //if(list[i].size()==0) P[i] = answer;

            answer = Math.max(P[i], answer);
        }
        //System.out.println(answer);
    }
}