import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    static int [] preOrder = new int[10000];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int end = init();
        postOrder(0, end);
        System.out.println(sb);
    }

    private static void postOrder(int start, int end){
        if(start>end) return;
        int divideDex= start+1;

        while(divideDex<=end && preOrder[start]> preOrder[divideDex]) divideDex++;
        postOrder(start+1, divideDex-1);
        postOrder(divideDex, end);
        sb.append(preOrder[start]+"\n");
    }

    private static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int end=0;
        while ((s = br.readLine()) != null && !s.isEmpty()) {
            preOrder[end++]=Integer.parseInt(s);
        }
        return end-1;
    }
}