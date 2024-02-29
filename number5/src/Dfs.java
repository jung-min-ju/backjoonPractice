public class Dfs {
    private static Graph graph;
    private static boolean[] visited;
    private static int MINDISTANCE = Integer.MAX_VALUE; // 최단 거리를 저장할 변수
    private static int START; //출발점
    private static int END; //도착점
    private static int PATHCOUNT; //경유지의 개수

    public Dfs(Graph graph, int[] path) {
        this.graph = graph;
        visited = new boolean[graph.getVertexCount()]; // 방문처리에 사용 할 배열선언
        START= path[0];
        END = path[1];
        PATHCOUNT = path[2];
        dfs(START, 0, -1); // 시작점, 경로의 거리, 경유지의 수
    }

     private void dfs(int nodeIndex, int distance, int count) {
        // 방문 처리
        visited[nodeIndex] = true;

        // 경유지의 수가 pathCount를 초과하면 탐색 중지 -> 경로가 없는 경우를 거르기 위함
        if (count > PATHCOUNT) {
            visited[nodeIndex] = false;
            return;
        }

        // 도착점에 도달하면 거리를 확인하고 최단 거리를 업데이트
        if (nodeIndex == END) {
            MINDISTANCE = Math.min(MINDISTANCE, distance);
            visited[nodeIndex] = false;
            return;
        }

        // 방문한 노드에 인접한 노드 찾기
        for (int[] node : graph.getGraph()[nodeIndex]) {
            // 인접한 노드가 방문한 적이 없다면 DFS 수행
            if (!visited[node[0]]) {
                dfs(node[0], distance + node[1], count + 1);
            }
        }

        // 재귀 호출이 끝나면 방문 처리를 취소
         visited[nodeIndex] = false;
    }

    public static int getMinDistance() {
        return MINDISTANCE == Integer.MAX_VALUE ? -1 : MINDISTANCE;
    }

    public static int getStart(){
        return START;
    }

    public static int getEnd(){
        return END;
    }
}
