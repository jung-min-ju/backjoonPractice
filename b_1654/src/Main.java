import java.io.*;
import java.util.*;

public class Main {
    static int N,M,K;
    static int candies[];
    static int parents[];
    static int weight[];
    static int value[];
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //아이 수
        M = Integer.parseInt(st.nextToken()); //관계 수
        K = Integer.parseInt(st.nextToken()); //공명 최소 수
        ArrayList<CandyInfo> groups = new ArrayList<>(N+1);

        candies=new int[N+1];
        parents=new int[N+1];
        dp=new int[K+1];

        st = new StringTokenizer(br.readLine());
        groups.add(new CandyInfo(0, 0));
        for(int i=1;i<=N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
            groups.add(new CandyInfo(0, 0)); // 값을 추가
            parents[i]=i;
        }

        //1. 친구들 집합 찾기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            union(f1,f2);
        }

        //2. KnappSack 진행
        //2-1. value, weight 배열 만들기
        for(int i=0; i< parents.length; i++) {
            CandyInfo candyInfo = groups.get(find(parents[i]));
            //여기서 틀렸었음! 그냥 get(parents[i]로 했었는데 해당 부모 노드가 루트 노드일 것이란 보장이 없기 때문임!
            candyInfo.weight++;
            candyInfo.value+=candies[i];
        }

        // weight가 0인 항목 제거
        groups.removeIf(candyInfo -> candyInfo.weight == 0);
        // weight 기준으로 오름차순 정렬
        groups.sort(Comparator.comparingInt(o -> o.weight));

        weight=new int[groups.size()+1];
        value=new int[groups.size()+1];

        for(int i=0; i<groups.size(); i++) {
            CandyInfo candyInfo = groups.get(i);
            weight[i] = candyInfo.weight;
            value[i] = candyInfo.value;
        }

        //2-2. KnappSack 진행
        knappSack();
        System.out.println(dp[K-1]);
    }

    static void union(int f1, int f2){
        int find1=find(f1);
        int find2=find(f2);

        if(find1<=find2){
            parents[find2]=parents[find1];
        }
        else parents[find1]=parents[find2];
    }

    static int find(int f){
        if(parents[f]==f) return f;

        return parents[f] = find(parents[f]);
    }

    static void knappSack() {
        for(int i=0; i<weight.length; i++){
            for(int j=K; j>=weight[i]; j--){
                dp[j] = Math.max(dp[j-weight[i]]+value[i], dp[j]);
            }
        }
    }

    static class CandyInfo {
        int weight;//아이 인원 수
        int value;//아이들 사탕 총합

        public CandyInfo(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
