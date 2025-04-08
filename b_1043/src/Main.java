import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Set<Integer> truthPeople;
    static Queue<Integer> newTruthPerson;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        truthPeople = new HashSet<>();
        Set<Integer> party[] = new Set[M];

        for(int i=0; i<M; i++) {
            party[i]=new HashSet<>();
        }

        st = new StringTokenizer(br.readLine());
        int knowTruth = Integer.parseInt(st.nextToken());

        for(int i=0; i<knowTruth; i++){
            truthPeople.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyPeople = Integer.parseInt(st.nextToken());
            for(int j=0; j<partyPeople; j++){
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        //1. for문 쭉 돌면서 구라 치면 안되는 사람 찾기
        boolean flag = true;

        while (flag) {
            flag = false;
            for(int i=0; i<M; i++) {
                if( !canLiar(party[i])) {
                    for(int now : party[i]) {
                        if(!truthPeople.contains(now)) flag=true;
                        truthPeople.add(now);
                    }
                }
            }
        }

        int answer = 0;
        //2. 모든 유저가 구라 ok인 party 카운팅
        for(int i=0; i<M; i++) {
            if( canLiar(party[i])) answer++;
        }

        System.out.println(answer);

    }

    static boolean canLiar(Set<Integer> list){
        for (int tp : truthPeople) {
            if(list.contains(tp)) return false;
        }
        return true;
    }
}