package week06.day30;

import java.util.*;

public class Q34_등산코스정하기 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/118669
     */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = { Integer.MAX_VALUE, Integer.MAX_VALUE };
        Set<Integer> gs = new HashSet<>();
        Set<Integer> ss = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int g : gates) {
            gs.add(g);
        }
        for (int s : summits) {
            ss.add(s);
        }

        for (int[] p : paths) {
            map.computeIfAbsent(p[0], (k -> new ArrayList<>())).add(new int[] { p[1], p[2] });
            map.computeIfAbsent(p[1], (k -> new ArrayList<>())).add(new int[] { p[0], p[2] });
        }

        for (int g : gates) {
            boolean[] check = new boolean[n + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            });

            pq.offer(new int[] { 0, g });
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (ss.contains(node) && cost <= answer[1]) {
                    answer = cost < answer[1]
                        ? new int[] { node, cost }
                        : new int[] { Math.min(answer[0], node), cost };
                    break;
                }
                if (answer[1] < cost) {
                    break;
                }
                for (int[] p : map.get(node)) {
                    int next = p[0];
                    int nextCost = p[1];

                    if (!check[next] && !gs.contains(next)) {
                        check[node] = true;
                        pq.offer(new int[] { Math.max(cost, nextCost), next });
                    }
                }
            }
        }
        return answer;
    }
}

/**
 * 우선순위 큐에서 미리 정렬해야 시간초과 발생 안함
 * 이후 산봉우리 탐색시 바로 탐색 종료
 *
 * 시간 초과 코드
 * PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
 *
 * while (!pq.isEmpty()) {
 *     ...
 *     if (ss.contains(node) && cost <= answer[1]) {
 *         answer = cost < answer[1]
 *             ? new int[] { node, cost }
 *             : new int[] { Math.min(answer[0], node), cost };
 *         continue;
 *     }
 *     ...
 * }
 */
