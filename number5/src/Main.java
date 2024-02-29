import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //1. 그래프 입력
        Graph graph = Input.readGraph();

        //2. 출발지, 도착지, 경유지 입력
        Output.printPath();
        int [] path = Input.readPath();

        //3. 계산 후 출력
        Dfs dfs = new Dfs(graph, path);
        Output.printPath(dfs.getMinDistance(), dfs.getStart(), dfs.getEnd());
    }

}