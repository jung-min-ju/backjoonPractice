import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int subin, sister;
    static int [] minDepth = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());
        Arrays.fill(minDepth, Integer.MAX_VALUE);
        minDepth[subin] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(subin);

        while (!queue.isEmpty()){
            int now = queue.poll();
            if(now==sister) break;
            if(now+1<=100000&&minDepth[now+1]==Integer.MAX_VALUE) {
                minDepth[now+1]=Math.min(minDepth[now+1], minDepth[now]+1);
                queue.add(now+1);
            }
            if(now-1>=0&&minDepth[now-1]==Integer.MAX_VALUE) {
                minDepth[now-1]=Math.min(minDepth[now-1], minDepth[now]+1);
                queue.add(now-1);
            }
            if(now*2<=100000&&minDepth[now*2]==Integer.MAX_VALUE) {
                minDepth[now*2]=Math.min(minDepth[now*2], minDepth[now]+1);
                queue.add(now*2);
            }
        }

        System.out.println(minDepth[sister]);
    }

}