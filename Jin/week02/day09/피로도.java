package Jin.week02.day09;

class 피로도 {
    static boolean[] visited;
    static int answer = -1;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        DFS(0, k, dungeons);
        return answer;
    }

    private void DFS(int count, int k, int[][] dungeons) {
        answer = Math.max(answer, count);

        for (int i = 0; i < dungeons.length; i++) {
            // 이미 방문했거나 필요 피로도가 현재 피로도보다 높을 경우는 탐색하지 않음
            if (visited[i] || dungeons[i][0] > k) continue;

            visited[i] = true;
            DFS(count + 1, k - dungeons[i][1], dungeons);

            // 하나의 경우에 대해서 탐색이 모두 끝나면 다시 탐색해야 하므로 false로 변경
            visited[i] = false;
        }
    }
}