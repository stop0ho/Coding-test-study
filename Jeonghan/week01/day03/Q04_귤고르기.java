package week01.day03;

import java.util.*;
import java.util.stream.Collectors;

public class Q04_귤고르기 {

    /**
     * stream 파이프 라인을 활용한 코드
     * 성능이 굉장히 안좋음
     */

    public int solution(int k, int[] tangerine) {
        return Arrays.stream(tangerine)
            .boxed()
            .collect(Collectors.groupingBy(t -> t, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
            .map(e -> new int[] { 0, e.getValue().intValue()})
            .reduce(new int[] { 0, 0 }, (acc1, acc2) -> acc1[0] < k ? new int[] { acc1[0] + acc2[1], acc1[1] + 1 } : acc1)[1];
    }
}
