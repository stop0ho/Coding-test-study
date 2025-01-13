import java.util.*;

public class Q01_Game {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public int solution(int[][] maps) {
        int answer = -1;
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] check = new boolean[n][m];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));
        check[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                answer = now.depth;
                break;
            }

            for (int i = 0; i < 4; i ++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && !check[nx][ny] && maps[nx][ny] == 1) {
                    check[nx][ny] = true;
                    q.add(new Node(nx, ny, now.depth + 1));
                }
            }
        }

        return answer;
    }

    private static class Node {
        private int x;
        private int y;
        private int depth;

        private Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
