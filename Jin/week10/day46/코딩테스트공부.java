package Jin.week10.day46;

import java.util.*;

public class 코딩테스트공부 {
    static final int INF = Integer.MAX_VALUE;
    int maxAlp = Integer.MIN_VALUE, maxCop = Integer.MIN_VALUE;

    public int solution(int alp, int cop, int[][] problems) {
        for (int[] problem : problems) {
            // 알고력과 코딩력의 최댓값 찾기
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // max 값 크기에 맞춰서 dp 배열 생성
        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        // 초기 알고력과 코딩력이 max 값보다 큰 경우가 들어올 수도 있음...... (으악)
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        // 최솟값으로 업데이트해줘야 해서, 큰 값으로 채워두지 않으면 min 값 다 0이 됨..
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // 최초 알고력과 코딩력을 0으로 시작
        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 알고리즘을 공부해서 알고력을 1 높이는 경우
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                // 코딩을 공부해서 코딩력을 1 높이는 경우
                if (j + 1<= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                // 풀 수 있는 문제 중에 하나를 선택해서 알고력과 코딩력을 함께 높이는 경우
                for (int[] problem : problems) {
                    if (problem[0] <= i && problem[1] <= j) {
                        int nextAlp = Math.min(maxAlp, i + problem[2]);
                        int nextCop = Math.min(maxCop, j + problem[3]);

                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
