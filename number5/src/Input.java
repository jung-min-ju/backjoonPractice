import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Input {
    static BufferedReader br;
    static StringTokenizer st;
    public static Graph readGraph() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCases = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(vertexCount); //vertex들의 입력값 저장해줄 그래프 객체 생성

        for (int i = 0; i < edgeCases; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph.addEdge(v, w, distance); //v는 현재 vertex, w는 아직 선택되지 않은 vertex
        }
        return graph;
    }

    public static int[] readPath() throws IOException {
        Output.printPath();
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int[] pathInfo = new int[3];
        for (int i = 0; i < pathInfo.length; i++) {
            pathInfo[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
        return pathInfo;
    }

}