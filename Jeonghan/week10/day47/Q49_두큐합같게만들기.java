package week10.day47;

import java.util.*;

public class Q49_두큐합같게만들기 {

    private static final Queue<Integer> q1 = new LinkedList<>();
    private static final Queue<Integer> q2 = new LinkedList<>();

    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        int max = Integer.MIN_VALUE;
        long sum1 = Arrays.stream(queue1).mapToLong(Long::valueOf).reduce(0L, Long::sum);
        long sum2 = Arrays.stream(queue2).mapToLong(Long::valueOf).reduce(0L, Long::sum);

        for (int i = 0; i < n; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            max = Math.max(max, queue1[i]);
            max = Math.max(max, queue2[i]);
        }

        long total = sum1 + sum2;
        if (total % 2 != 0 || max > total / 2) {
            return -1;
        }

        int cnt = 0;
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                int poll = q1.poll();
                q2.add(poll);
                sum1 -= poll;
                sum2 += poll;
            } else {
                int poll = q2.poll();
                q1.add(poll);
                sum1 += poll;
                sum2 -= poll;
            }
            cnt++;
            if (cnt > 3 * n) {
                return -1;
            }
        }
        return cnt;
    }
}
