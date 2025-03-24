package Jin.week03.day13;

import java.util.*;

class 섬연결하기 {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        // 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        int edge = 0;

        for (int i = 0; i < costs.length; i++) {
            // 정점 N개에서 신장 트리의 간선은 n - 1개이니까!
            if (edge == n - 1) break;

            if (find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
                edge++;
            }
        }
        return answer;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        parent[root2] = root1;
    }
}