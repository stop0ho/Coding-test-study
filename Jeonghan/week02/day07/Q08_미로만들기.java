package week02.day07;

import java.io.*;
import java.util.*;

public class Q08_미로만들기 {

    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };

    private static int n;
    private static int[][] map;
    private static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        check = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().chars()
                .map(Character::getNumericValue)
                .toArray();
        }

        sb.append(bfs());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs() {
        int result = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        check[0][0] = true;

        int[][] map1 = new int[n][n];

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.x == n - 1 && now.y == n - 1) {
                result = now.depth;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || n <= nx || ny < 0 || n <= ny || check[nx][ny]) {
                    continue;
                }

                check[nx][ny] = true;
                if (map[now.x][now.y] == map[nx][ny]) {
                    pq.offer(new Node(nx, ny, map[now.x][now.y] == 1 ? now.depth : now.depth + 1));
                } else {
                    pq.offer(new Node(nx, ny, map[now.x][now.y] == 1 ? now.depth + 1 : now.depth));
                }
            }
        }
        return result;
    }

    private static class Node implements Comparable<Node>{
        private int x;
        private int y;
        private int depth;

        private Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.depth, o.depth);
        }
    }
}
