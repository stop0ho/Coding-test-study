package week04.day16;

public class Q19_땅따먹기 {
    private static int[][] dp, map;
    private static int n, m;

    int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        map = land;
        dp = new int[n][m];
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dfs(0, i));
        }
        return answer;
    }

    private int dfs(int x, int y) {
        if (x == n) {
            return 0;
        }
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        for (int i = 0; i < m; i++) {
            if (i != y) {
                dp[x][y] = Math.max(dp[x][y], dfs(x + 1, i) + map[x][i]);
            }
        }
        return dp[x][y];
    }
}
