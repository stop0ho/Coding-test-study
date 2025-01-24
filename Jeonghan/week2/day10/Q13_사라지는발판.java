package week2.day10;

import java.util.*;
import java.util.function.*;

public class Q13_사라지는발판 {

    private static final int[] dx = { 1, -1, 0, 0 };
    private static final int[] dy = { 0, 0, 1, -1 };

    private static int n, m;
    private static int[][] map;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        map = board;
        return bt(0, aloc, bloc);
    }

    private int bt(int total, int[] a, int[] b) {
        // total 짝수인 경우 A 턴
        boolean turn = total % 2 == 0;
        int[] loc = turn ? a : b;

        // 탐색 시작 전에 발판이 깨져있으면 바로 결과 반환
        if (map[loc[0]][loc[1]] == 0) {
            return total;
        }

        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] nloc = new int[] { loc[0] + dx[i], loc[1] + dy[i] };

            if (0 <= nloc[0] && nloc[0] < n && 0 <= nloc[1] && nloc[1] < m && map[nloc[0]][nloc[1]] == 1) {
                map[loc[0]][loc[1]] = 0;
                // 해당 위치에서 이동 가능한 위치에 대한 결과 저장
                temp.add(turn ? bt(total + 1, nloc, b) : bt(total + 1, a, nloc));
                map[loc[0]][loc[1]] = 1;
            }
        }

        // 누구의 턴이냐에 따라 이기는 조건 상이
        Predicate<Integer> predicate = turn ? t -> t % 2 != 0 : t -> t % 2 == 0;

        // 해당 위치에서 자신이 이길 수 있다면 최소값을, 아니면 최대값을 반환
        return temp.stream().anyMatch(predicate)
            ? temp.stream().filter(predicate).min(Integer::compareTo).orElse(total)
            : temp.stream().max(Integer::compareTo).orElse(total);
    }
}
