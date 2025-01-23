package week2.day09;

import java.util.*;

public class Q11_NQueen {

    private static final Map<Integer, Integer> map = new HashMap<>();

    public int solution(int n) {
        return bt(0, n);
    }

    private int bt(int x, int n) {
        int answer = 0;
        for (int y = 0; y < n; y++) {
            if (check(x, y)) {
                map.put(x, y);
                answer += (x + 1) == n ? 1 : bt(x + 1, n);
                map.remove(x);
            }
        }
        return answer;
    }

    private boolean check(int x, int y) {
        return map.entrySet().stream()
            .allMatch(e -> {
                int qx = e.getKey();
                int qy = e.getValue();
                return qx != x && qy != y && Math.abs(qx - x) != Math.abs(qy - y);
            });
    }
}
