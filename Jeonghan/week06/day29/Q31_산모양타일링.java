package week06.day29;

public class Q31_산모양타일링 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/258705
     */
    public int solution(int n, int[] tops) {
        int[] dp = new int[2 * n + 2];
        dp[1] = 1;
        dp[2] = tops[0] == 1 ? 3 : 2;

        for (int i = 3; i < 2 * n + 2; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            if (i % 2 == 0 && tops[(i / 2) - 1] == 1) {
                dp[i] += dp[i - 1];
            }
            dp[i] %= 10007;
        }
        return dp[2 * n + 1];
    }
}
