package week08.day38;

import java.util.*;

public class Q45_거리두기확인하기 {
    private static final int[] dx = { 0, 0, 1, -1 };
    private static final int[] dy = { 1, -1, 0, 0 };
    private static char[][] map;
    private static boolean[][] check;
    private static boolean finish;

    public int[] solution(String[][] places) {
        int[] answer = { 1, 1, 1, 1, 1 };

        for (int i = 0; i < places.length; i++) {
            map = new char[5][5];
            check = new boolean[5][5];
            finish = false;
            List<int[]> ps = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                char[] temp = places[i][j].toCharArray();
                for (int k = 0; k < 5; k++) {
                    map[j][k] = temp[k];
                    if (map[j][k] == 'P') {
                        ps.add(new int[] { j, k });
                    }
                }
            }

            for (int[] p : ps) {
                check[p[0]][p[1]] = true;
                bt(p[0], p[1], 0);
                check[p[0]][p[1]] = false;
                if (finish) {
                    answer[i] = 0;
                    break;
                }
            }
        }
        return answer;
    }

    private void bt(int x, int y, int d) {
        if (d == 3) {
            return;
        }
        if (d != 0 && map[x][y] == 'P') {
            finish = true;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && !finish && !check[nx][ny] && map[nx][ny] != 'X') {
                check[nx][ny] = true;
                bt(nx, ny, d + 1);
                check[nx][ny] = false;
            }
        }
    }
}
