package week02.day06;

import java.util.*;

public class Q07_배달 {

    public int solution(int N, int[][] road, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n[1]));
        Map<Integer, List<int[]>> map = new HashMap<>();
        int[] cost = new int[N + 1];

        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int[] r : road) {
            map.computeIfAbsent(r[0], k -> new ArrayList<>()).add(new int[] { r[1], r[2] });
            map.computeIfAbsent(r[1], k -> new ArrayList<>()).add(new int[] { r[0], r[2] });
        }

        pq.offer(new int[] { 1, 0 });
        cost[1] = 0;

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int index = node[0];
            int total = node[1];

            for (int[] next : map.get(index)) {
                int nextIndex = next[0];
                int nextCost = next[1];

                if (nextCost + total < cost[nextIndex]) {
                    cost[nextIndex] = nextCost + total;
                    pq.offer(new int[] { nextIndex, cost[nextIndex] });
                }
            }
        }

        return (int) Arrays.stream(cost).filter(c -> c <= K).count();
    }
}
