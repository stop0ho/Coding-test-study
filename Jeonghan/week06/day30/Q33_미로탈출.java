package week06.day30;

import java.util.*;

public class Q33_미로탈출 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/81304
     */
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Map<Integer, List<int[]>> path = new HashMap<>();
        Map<Integer, Integer> trapMap = new HashMap<>();
        boolean[][] check = new boolean[n + 1][1 + 1 << traps.length];

        for (int i = 0; i < traps.length; i++) {
            trapMap.put(traps[i], i);
        }

        for (int[] r : roads) {
            path.computeIfAbsent(r[0], (k) -> new ArrayList<>()).add(new int[] { r[1], r[2], 1 });
            path.computeIfAbsent(r[1], (k) -> new ArrayList<>()).add(new int[] { r[0], r[2], 0 });
        }

        pq.offer(new int[] { 0, start, 0 });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (check[cur[1]][cur[2]]) {
                continue;
            }
            check[cur[1]][cur[2]] = true;

            if (cur[1] == end) {
                answer = cur[0];
                break;
            }
            int flag = trapMap.containsKey(cur[1]) ? cur[2] ^ (1 << trapMap.get(cur[1])) : cur[2];
            for (int[] p : path.get(cur[1])) {
                int next = p[0];
                int cost = p[1];

                if (check[next][flag]) {
                    continue;
                }

                boolean nextTrap = trapMap.containsKey(next) && (flag & (1 << trapMap.get(next))) != 0;
                boolean validPath = (flag > cur[2]) ? (p[2] == 0) : (p[2] == 1);

                if (nextTrap) validPath = !validPath;

                if (validPath) {
                    pq.offer(new int[] { cur[0] + cost, next, flag });
                }
            }
        }
        return answer;
    }
}

/**
 * 초기에 47 - 54 라인을 굉장히 복잡한 조건문으로 해결
 * 이후 gpt에게 최적화를 요청
 *
 * 기존 코드
 * if (flag > cur[2]) {
 *     if (trapMap.containsKey(next)) {
 *         if ((flag & (1 << trapMap.get(next))) != 0) {
 *             if (p[2] == 1) {
 *                 pq.offer(new int[] { cur[0] + cost, next, flag });
 *             }
 *         } else {
 *             if (p[2] == 0) {
 *                 pq.offer(new int[] { cur[0] + cost, next, flag });
 *             }
 *         }
 *     } else {
 *         if (p[2] == 0) {
 *             pq.offer(new int[] { cur[0] + cost, next, flag });
 *         }
 *     }
 * } else {
 *     if (trapMap.containsKey(next)) {
 *         if ((flag & (1 << trapMap.get(next))) != 0) {
 *             if (p[2] == 0) {
 *                 pq.offer(new int[] { cur[0] + cost, next, flag });
 *             }
 *         } else {
 *             if (p[2] == 1) {
 *                 pq.offer(new int[] { cur[0] + cost, next, flag });
 *             }
 *         }
 *     } else {
 *         if (p[2] == 1) {
 *             pq.offer(new int[] { cur[0] + cost, next, flag });
 *         }
 *     }
 * }
 */
