import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class StarDistance implements Comparable<StarDistance> {
    int from;
    int to;
    double dis;

    public StarDistance(Star from, Star to) {
        this.from = from.num;
        this.to = to.num;
        calculateDis(from, to);
    }

    private void calculateDis(Star from, Star to){
        double dx = from.x-to.x;
        double dy = from.y-to.y;
        this.dis = Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public int compareTo(StarDistance o){
        return (int) (this.dis-o.dis);
    }
}

class Star {
    int num;
    double x;
    double y;

    public Star(int num, double x, double y){
        this.num=num;
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static int N;
    static List<Star> stars;
    static int[] P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        stars = new ArrayList<>();
        Queue<StarDistance> disPq = new PriorityQueue<>();
        P = new int[N];
        Arrays.fill(P, -1);

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            double x = Float.parseFloat(st.nextToken());
            double y = Float.parseFloat(st.nextToken());
            stars.add(new Star(i,x,y));
        }

        for(int i=0; i<stars.size()-1; i++){
            Star from = stars.get(i);
            for(int j=i+1; j<stars.size(); j++){
                Star to = stars.get(j);
                disPq.add(new StarDistance(from, to));
            }
        }

        double answer = 0;
        int cnt = 0;
        while (true) {
            StarDistance now = disPq.poll();
            System.out.println(now.to+" "+now.from);
            if(isCircle(now.from, now.to)) continue;
            answer+=now.dis;
            cnt++;
            if(cnt==N-1) break;
        }

        String result = String.format("%.2f", answer);
        System.out.println(result);
    }

    static boolean isCircle(int u, int v){
        u = find(u);
        v = find(v);

        if(u==v) return true;

        if(P[u] < P[v]) { //부모가 더 tree depth가 커야 함!
            int temp = u;
            u = v;
            v = temp;
        }

        if(P[u]==P[v]) P[u]--;
        P[v] = u;
        return false;
    }

    static int find(int x){
        if(P[x] < 0) return x;
        return P[x] = find(P[x]);
    }
}
