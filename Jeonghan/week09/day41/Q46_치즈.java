package week09.day41;

import java.io.*;
import java.util.*;

public class Q46_치즈 {
    private static final int[] dx = { 0, 0, 1, -1 };
    private static final int[] dy = { 1, -1, 0, 0 };

    private static int n, m;
    private static int[][] map;
    private static int[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][m];
        check = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                check[i][j] = map[i][j];
            }
        }
        sb.append(solution());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[] { 0, 0, 0 });
        check[0][0]--;
        int result = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int depth = cur[2];
            result = Math.max(result, depth);

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && check[nx][ny] >= 0) {
                    check[nx][ny]--;
                    if (check[nx][ny] < 0) {
                        pq.offer(new int[] { nx, ny, map[nx][ny] == 1 ? depth + 1 : depth });
                    }
                }
            }
        }
        return result;
    }
}
