package Jin.week04.day16;

import java.util.*;

class 땅따먹기 {
    int solution(int[][] land) {
        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                int max = 0;
                for (int k = 0; k < land[0].length; k++) {
                    if (j == k) continue;
                    max = Math.max(max, land[i - 1][k]);
                }
                land[i][j] += max;
            }
        }

        return Arrays.stream(land[land.length - 1]).max().getAsInt();
    }
}