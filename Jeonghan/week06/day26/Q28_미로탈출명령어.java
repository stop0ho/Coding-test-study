package week06.day26;

import java.util.*;

public class Q28_미로탈출명령어 {

    private static final String IMP = "impossible";
    private static final String MOVE = "dlru";

    private static final int[] dx = { 1, 0, 0, -1 };
    private static final int[] dy = { 0, -1, 1, 0 };

    private static StringBuilder[][][] dp;
    private int n, m, r, c;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        dp = new StringBuilder[k + 1][n][m];
        this.n = n;
        this.m = m;
        this.r = r - 1;
        this.c = c - 1;
        return dfs(x - 1, y - 1, k).toString();
    }

    private StringBuilder dfs(int x, int y, int cnt) {
        if (!Objects.isNull(dp[cnt][x][y]) && dp[cnt][x][y].toString().length() != 0) {
            return dp[cnt][x][y];
        }
        dp[cnt][x][y] = new StringBuilder();
        if (cnt == 0) {
            return dp[cnt][x][y].append(x == r && y == c ? "" : IMP);
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                String temp = dfs(nx, ny, cnt - 1).toString();
                if (!temp.equals(IMP)) {
                    return dp[cnt][x][y].append(MOVE.charAt(i)).append(temp);
                }
            }
        }
        return dp[cnt][x][y].append(IMP);
    }
}
