import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] indegree = new int[N+1];
        List<Integer> [] quizZip = new List[N+1];

        for (int i = 0; i <= N; i++) {
            quizZip[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            quizZip[pre].add(post);
            indegree[post]++;
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue <Integer> pq = new PriorityQueue<>();

        for(int i=1; i<=N; i++){
            if(indegree[i]==0){
                pq.add(i);
            }
        }

        while (!pq.isEmpty()){
            int now = pq.poll();
            sb.append(now+" ");

            for(int i=0; i<quizZip[now].size(); i++){
                int next = quizZip[now].get(i);
                indegree[next]--;
            }

        }

        System.out.println(sb);
    }

}