package Jin.week2.day07;

import java.io.*;
import java.util.*;

public class 미로만들기 {
    static int INF = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];
        int[][] dist = new int[n][n];

        // 지나갈 수 있는 곳(1)은 가중치가 0
        // 벽이 있는 곳(0)은 가중치가 1이 될 수 있도록 초기화
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                boolean isWall = (line.charAt(j) == '0');
                graph[i][j] = (isWall ? 1 : 0);
            }
        }

        // 최단 거리 저장 전 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
        }

        dijkstra(dist, graph);
        System.out.println(dist[n - 1][n - 1]);
    }

    private static void dijkstra(int[][] dist, int[][] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node o) -> o.cost)
                .thenComparingInt(o -> o.x)
                .thenComparingInt(o -> o.y));

        pq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;

        // 우선순위 큐가 빌 때까지 계속 진행
        while (!pq.isEmpty()) {
            // 우선순위 큐에서 가중치가 최솟값인 지점을 꺼내옴
            Node now = pq.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowCost = now.cost;

            if (nowCost > dist[nowX][nowY]) continue;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                // 범위를 벗어나는 경우
                if (!isValid(nextX, nextY, graph.length)) continue;

                int newCost = nowCost + graph[nextX][nextY];
                if (newCost < dist[nextX][nextY]) {
                    dist[nextX][nextY] = newCost;
                    pq.offer(new Node(nextX, nextY, newCost));
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int size) {
        return (x >= 0 && y >= 0 && x < size && y < size);
    }
}

class Node {
    int x;
    int y;
    int cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}