import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> [] tree;
    public static void main(String[] args) throws IOException {

        int remove = init();
        dfs(remove);

        int answer = 0;
        for(int i=0; i<N; i++){
            if(tree[i].size()==0) {
                answer++;
            }
            if(tree[i].size()==1&&tree[i].get(0)==remove) answer++;
        }

        System.out.println(answer);
    }

    static void dfs(int idx) {
        tree[idx].add(-1); //본인에 -1 처리

        for(int i=0; i<tree[idx].size(); i++){
            int child = tree[idx].get(i);
            if(child==-1) break;
            dfs(child); //자식에 -1 처리
        }

    }

    static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new List[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            tree[i] = new LinkedList<>();
        }

        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            if(idx==-1) continue;
            tree[idx].add(i);
        }

        return Integer.parseInt(br.readLine());

    }
}