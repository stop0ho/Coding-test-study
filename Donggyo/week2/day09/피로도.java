class Solution {
    static boolean[] visited;
    static int max = -1;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        visited = new boolean[dungeons.length];
        dfs(k, 0, dungeons, visited);
        answer = max;
        return answer;
    }
    
    static void dfs(int hp, int count, int[][] dungeons, boolean[] visited){
        max = Math.max(count, max);
        for(int i = 0; i<dungeons.length;i++){
            if(visited[i]) continue;
            if(hp<dungeons[i][0]) continue;
            visited[i] = true;
            dfs(hp-dungeons[i][1], count+1, dungeons, visited);
            visited[i] = false;
        }
    }
}