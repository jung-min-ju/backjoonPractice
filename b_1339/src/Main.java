import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Info implements Comparable<Info>{
    int cDex;
    int f;

    public Info(int cDex, int f){
        this.cDex=cDex;
        this.f =f;
    }

    public int compareTo(Info o){
        return o.f-this.f;
    }

}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String [] input = new String[N];

        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();
        }

        //가중치 계산
        int [] weight = new int[27];
        boolean [] check = new boolean[27];
        Arrays.fill(weight, -1);

        for (int i = 0; i < N; i++) {
            int location = 0;
            for(int j=input[i].length()-1; j>=0; j--) {
                char c = input[i].charAt(location++);
                weight[c - 'A'] += Math.pow(10, j);
            }
        }

        Queue<Info> alphaWeight = new PriorityQueue<>();

        for(int i=0; i<27; i++) {
            alphaWeight.add(new Info(i, weight[i]));
        }

        //알파벳 넘버링
        int Num = 9;
        while (!alphaWeight.isEmpty()) {
            Info now = alphaWeight.poll();
            if(check[now.cDex]) continue;
            weight[now.cDex] = Num--;
            check[now.cDex]=true;
        }

        //계산
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int location = 0;
            for(int j=input[i].length()-1; j>=0; j--) {
                char c = input[i].charAt(location++);
                answer+=weight[c-'A']*(int) Math.pow(10, j);
            }
        }

        System.out.println(answer);
    }
}