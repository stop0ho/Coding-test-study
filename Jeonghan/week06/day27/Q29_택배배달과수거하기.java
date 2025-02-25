package week06.day27;

import java.util.*;
import java.util.stream.*;

public class Q29_택배배달과수거하기 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        PriorityQueue<int[]> d = init(deliveries);
        PriorityQueue<int[]> p = init(pickups);

        while (!d.isEmpty() || !p.isEmpty()) {
            answer += 2L * Math.max(
                d.isEmpty() ? 0 : d.peek()[0],
                p.isEmpty() ? 0 : p.peek()[0]
            );
            apply(cap, d);
            apply(cap, p);
        }
        return answer;
    }

    private PriorityQueue<int[]> init(int[] target) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        IntStream.range(0, target.length)
            .filter(i -> target[i] != 0)
            .forEach(i -> pq.offer(new int[]{ i + 1, target[i] }));
        return pq;
    }

    private void apply(int cap, PriorityQueue<int[]> pq) {
        int cnt = cap;
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            cnt -= temp[1];

            if (cnt < 0) {
                pq.offer(new int[]{ temp[0], -cnt });
                break;
            }
        }
    }
}
