package week03.day13;

import java.util.*;
import java.util.stream.IntStream;

public class Q16_섬연결하기 {
    private static int[] parents;
    private static final PriorityQueue<int[]> pq = new PriorityQueue<>(
        Comparator.comparingInt(a -> a[2])
    );

    public int solution(int n, int[][] costs) {
        int answer = 0;
        int cnt = 0;
        parents = IntStream.range(0, n).toArray();
        Arrays.stream(costs).forEach(pq::offer);

        while (!pq.isEmpty() && cnt != n - 1) {
            int[] edge = pq.poll();
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            if (!find(a, b)) {
                answer += cost;
                union(a, b);
                cnt++;
            }
        }
        return answer;
    }

    private int getParent(int node) {
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = getParent(parents[node]);
    }

    private void union(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);

        if (aParent > bParent) {
            parents[aParent] = bParent;
        } else {
            parents[bParent] = aParent;
        }
    }

    private boolean find(int a, int b) {
        return getParent(a) == getParent(b);
    }
}
