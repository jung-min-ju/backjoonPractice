import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int [] group;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        group=new int[N+1];
        Arrays.fill(group, -1);

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                int connect = Integer.parseInt(st.nextToken());
                if(connect==1) union(i,j);
            }
        }

        if(M==1) System.out.println("YES");
        else{
            boolean answer=true;

            st = new StringTokenizer(br.readLine());
            int exTarget = Integer.parseInt(st.nextToken());
            for(int i=1; i<M; i++) {
                int newTarget = Integer.parseInt(st.nextToken());
                if(find(exTarget)==find(newTarget)) {
                    exTarget=newTarget;
                    continue;
                }
                answer=false;
                break;
            }

            System.out.println(answer ? "YES" : "NO");
        }
    }
    static int find(int x) {
        if(group[x]<0) return x;
        return group[x] = find(group[x]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u==v) return;
        if(group[u]==group[v]) {
            group[u]--;
        }
        if(group[v]<group[u]){
            int temp = u;
            u=v;
            v=temp;
        }

        group[v]=u;
    }
}