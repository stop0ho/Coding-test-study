package Jin;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 - 게임 맵 최단거리 문제
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
 */
public class Q01_Game {
    static int[][] visit;
    static int n, m;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;

        visit = new int[n][m];
        BFS(0, 0, maps);

        if (visit[n-1][m-1] == 0) return -1;
        else return visit[n-1][m-1];
    }

    private void BFS(int x, int y, int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        // 이동한 칸 개수를 세야 하므로 시작 노드를 1로 해야 함
        visit[x][y] = 1;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위를 벗어난다면 탐색하지 않도록 함
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                
                // 범위 내이면서 벽(0)이 아닌 노드라면 방문
                if (maps[nx][ny] == 1) {
                    // 재방문하지 않도록 0으로 변경
                    maps[nx][ny] = 0;
                    queue.offer(new Point(nx, ny));
                    
                    // 방문 거리 저장
                    visit[nx][ny] = visit[p.x][p.y] + 1;
                }
            }
        }
    }
}

class Point {
    public int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}