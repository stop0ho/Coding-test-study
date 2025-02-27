package week06.day29;

public class Q32_코딩테스트공부 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/118668
     */
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int[][] dp = new int[200][200];
        int ab = Integer.MIN_VALUE;
        int cb = Integer.MIN_VALUE;

        for (int[] p : problems) {
            ab = Math.max(ab, p[0]);
            cb = Math.max(cb, p[1]);
        }

        alp = Math.min(alp, ab);
        cop = Math.min(cop, cb);

        for (int i = alp; i < 200; i++) {
            for (int j = cop; j < 200; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= ab; i++) {
            for (int j = cop; j <= cb; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] p : problems) {
                    if (i < p[0] || j < p[1]) {
                        continue;
                    }
                    int x = Math.min((i + p[2]), ab);
                    int y = Math.min((j + p[3]), cb);
                    dp[x][y] = Math.min(dp[x][y], dp[i][j] + p[4]);
                }
            }
        }
        return dp[ab][cb];
    }
}
