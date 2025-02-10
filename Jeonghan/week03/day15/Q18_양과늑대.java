package week03.day15;

import java.util.*;

public class Q18_양과늑대 {
    private static final Map<Integer, List<Integer>> tree = new HashMap<>();
    private static int answer;
    private static int[] map;

    public int solution(int[] info, int[][] edges) {
        answer = 0;
        map = info;
        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        dfs(0, 0, 0, 0);
        return answer;
    }

    private void dfs(int sheepCnt, int wolfCnt, int index, int flag) {
        if (map[index] == 0) {
            sheepCnt++;
            answer = Math.max(answer, sheepCnt);
        } else {
            wolfCnt++;
        }

        if (sheepCnt <= wolfCnt) {
            return;
        }

        // 자기 자신을 제외
        flag &= ~(1 << index);

        // 자신의 자식들 추가
        for (int child : tree.getOrDefault(index, Collections.emptyList())) {
            flag |= 1 << child;
        }

        // 순회 가능 인덱스 탐색
        int temp = flag;
        while (temp != 0) {
            int next = Integer.numberOfTrailingZeros(temp);
            dfs(sheepCnt, wolfCnt, next, flag);
            temp &= (temp - 1);
        }
    }
}
