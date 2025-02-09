package Jin.week3.day15;

class 양과늑대 {
    static int maxSheepCnt = 0;

    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        DFS(0, visited, 0, 0, info, edges);

        return maxSheepCnt;
    }

    private static void DFS(int idx, boolean[] visited, int sheepCnt, int wolfCnt, int[] info, int[][] edges) {
        visited[idx] = true;

        if (info[idx] == 0) {
            // 양일 경우, 최대치를 갱신해준다.
            sheepCnt++;
            maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);
        } else {
            // 늑대일 경우
            wolfCnt++;
        }

        // 늑대와 양의 수가 같아지는 순간부터 잡아먹으니까 더 이상 탐색하지 않아도 됨.
        if (sheepCnt == wolfCnt) return;

        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisited = visited.clone();
                DFS(edge[1], newVisited, sheepCnt, wolfCnt, info, edges);
            }
        }
    }
}