import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int [] group;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        group = new int[N+1];

        Arrays.fill(group, -1);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(check==1) {
                if(find(a)==find(b)) System.out.println("YES");
                else System.out.println("NO");
                continue;
            }
            union(a, b);
        }
    }

    static int find(int x) {
        if(group[x]<0) return x;
        return group[x] = find(group[x]); //최적화1 : 경로 압축
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u==v) return;

        if(group[v]<group[u]){
            int temp = u;
            u=v;
            v=temp;
        }

        if(group[u]==group[v]) { //트리 높이가 같으면 트리 높이가 하나 더 늘게 됨.
            group[u]--;
        }

        group[v]=u;
    }
}