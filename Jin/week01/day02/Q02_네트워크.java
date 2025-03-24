package Jin.week01.day02;

public class Q02_네트워크 {
    // 방문 여부를 체크
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        // 생성 시 false니까 굳이 false로 처리하는 과정 필요 X
        visited = new boolean[computers.length];

        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                answer++;
                visited[i] = true;
                DFS(i, computers);
            }
        }

        return answer;
    }

    private void DFS(int node, int[][] computers) {
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                visited[node] = true;
                DFS(i, computers);
            }
        }
    }
}
