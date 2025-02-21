package week05.day24;

import java.util.*;

public class Q27_입국심사 {
    public long solution(int n, int[] times) {
        long lb = 0, rb = 1_000_000_000_000_000L;
        while (lb < rb) {
            long mid = (lb + rb) / 2;
            if (calculateTotal(mid, times) < n) {
                lb = mid + 1;
            } else {
                rb = mid;
            }
        }
        return rb;
    }

    private long calculateTotal(long target, int[] times) {
        return Arrays.stream(times)
            .mapToLong(i -> target / i)
            .reduce(0L, Long::sum);
    }
}
