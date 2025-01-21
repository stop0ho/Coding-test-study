package week1.day03;

import java.util.*;

public class Q03_예산 {

    public int solution(int[] d, int budget) {

        /**
         * stream 파이프 라인을 활용한 코드
         * 성능이 굉장히 안좋음
         *
         * return Arrays.stream(d)
         *             .sorted()
         *             .mapToObj(cost -> new int[] {cost, 1})
         *             .reduce(new int[] {0, 0}, (acc, index) ->
         *                     acc[0] + index[0] <= budget ? new int[] {acc[0] + index[0], acc[1] + 1} : acc
         *             )[1];
         */

        Arrays.sort(d);
        int result = 0;
        for (int j : d) {
            if ((budget -= j) < 0) {
                break;
            }
            result++;
        }
        return result;
    }
}
