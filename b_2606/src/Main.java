import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int totalCom;
        int edge;
        List<Integer>[] computer;
        boolean visited[];
        int answer=-1;

        totalCom = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());
        computer = new List[totalCom+1];
        visited = new boolean[totalCom+1];

        for(int i=1; i<=totalCom; i++){
            computer[i] = new ArrayList<>();
        }

        for(int i=1; i<=edge; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            computer[from].add(to);
            computer[to].add(from);
        }

        Stack<Integer> stack = new Stack<>();

        stack.push(1);

        while (!stack.isEmpty()){
            int next = stack.pop();
            if(visited[next]) continue;
            visited[next]=true;
            answer++;
            for(int i=0; i<computer[next].size(); i++){
                stack.push(computer[next].get(i));
            }
        }

        System.out.println(answer);

    }
}