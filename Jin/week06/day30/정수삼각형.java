package Jin.week06.day30;

public class 정수삼각형 {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        int[][] dp = new int[height][height];

        dp[0][0] = triangle[0][0];
        for (int i = 1; i < height; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 가장 왼쪽의 경우
                    dp[i][j] = triangle[i][j] + dp[i - 1][j];
                } else if (j == i) {
                    // 가장 오른쪽의 경우
                    dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
                } else {
                    // 나머지 경우
                    dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }

        int MAX_VALUE = 0;
        for (int i = 0; i < height; i++) {
            MAX_VALUE = Math.max(MAX_VALUE, dp[height - 1][i]);
        }

        return MAX_VALUE;
    }
}
