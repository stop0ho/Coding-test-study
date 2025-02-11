package week04.day17;

public class Q20_가장큰정사각형찾기 {
    private static final int[] dx = { 0, 1, 1 };
    private static final int[] dy = { 1, 1, 0 };

    private static int[][] dp, map;
    private static int n, m;

    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        m = board[0].length;
        map = board;
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    answer = Math.max(answer, dfs(i, j));
                }
            }
        }
        return (int)Math.pow(answer, 2);
    }

    private int dfs(int x, int y) {
        if (map[x][y] == 0 || dp[x][y] != 0) {
            return dp[x][y];
        }
        dp[x][y] = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 > nx || nx >= n || 0 > ny || ny >= m) {
                return dp[x][y] = 1;
            }
            dp[x][y] = Math.min(dp[x][y], dfs(nx, ny));
        }
        return dp[x][y] += 1;
    }
}
