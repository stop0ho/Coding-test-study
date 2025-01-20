package Jin.week2.day06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q07_배달 {
    static int[] dist;
    static PriorityQueue<Node> pq;
    static int[][] graph;

    static int INF = Integer.MAX_VALUE;
    static int start = 1; // 시작점은 1번 마을

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;

        graph = makeGraph(N, road);
        dijkstra(N, start, graph);

        for (int d : dist) {
            if (d > K) continue;
            answer++;
        }

        return answer;
    }

    // 간선 연결 정보를 저장
    private static int[][] makeGraph(int N, int[][] road) {
        int[][] graph = new int[N + 1][N + 1];

        // 연결 정보 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = INF;
            }
        }

        // 실제 연결된 노드 사이의 가중치 저장
        // 방향이 없으므로 양방향으로 저장
        for (int[] ints : road) {
            int a = ints[0];
            int b = ints[1];
            int cost = ints[2];

            if (graph[a][b] < cost) continue;

            graph[a][b] = cost;
            graph[b][a] = cost;
        }

        return graph;
    }

    private static void dijkstra(int N, int start, int[][] graph) {
        dist = new int[N + 1];
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        // 최초 상태는 전부 INF, 출발지만 0
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        // 우선순위 큐가 빌 때까지 계속 진행
        while (!pq.isEmpty()) {
            // 우선순위 큐에서 가중치가 최솟값인 지점을 꺼내옴
            int now = pq.poll().index;

            for (int i = 1; i < graph[now].length; i++) {
                // 노드 사이의 가중치
                int cost = graph[now][i];

                // 둘 사이가 연결되어있지 않다면
                if (cost == INF) continue;

                if (dist[i] > dist[now] + cost) {
                    dist[i] = dist[now] + cost;
                    pq.offer(new Node(i, cost));
                }
            }
        }
    }
}

class Node {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}