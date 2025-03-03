import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Dot implements Comparable<Dot> {
        int x;
        int y;

        public Dot(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public int compareTo(Dot o) {
            if(o.y==this.y){
                return this.x-o.x;
            }
            else return this.y-o.y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Dot> queue = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            queue.add(new Dot(x,y));
        }

        while (!queue.isEmpty()){
            Dot d = queue.poll();
            System.out.println(d.x+" "+d.y);
        }

    }
}