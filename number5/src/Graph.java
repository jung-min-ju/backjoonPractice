import java.util.*;

public class Graph { //그래프를 저장해줄 repository
    private int vertexCount;
    private List<int[]>[] graph;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.graph = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            this.graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w, int distance) { //v는 현재 vertex, w는 아직 선택되지 않은 vertex
        this.graph[v].add(new int[]{w, distance});
    }

    public List<int[]>[] getGraph() {
        return this.graph;
    }

    public int getVertexCount(){
        return vertexCount;
    }
}