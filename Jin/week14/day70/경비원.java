package Jin.week14.day70;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class 경비원 {
    static int N, M;
    static int[] dPos;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean[][] visited;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int storeCnt = Integer.parseInt(br.readLine());
        int[][] position = new int[storeCnt + 1][2];
        for (int i = 0; i < storeCnt + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            // 미리 배열의 위치에 맞게 저장
            if (direction == 1) {
                position[i][0] = 0;
                position[i][1] = distance;
            } else if (direction == 2) {
                position[i][0] = M;
                position[i][1] = distance;
            } else if (direction == 3) {
                position[i][0] = distance;
                position[i][1] = 0;
            } else {
                position[i][0] = distance;
                position[i][1] = N;
            }
        }

        // 2. BFS 진행
        int sum = 0;
        dPos = position[storeCnt]; // 가장 마지막에 저장된 게 동근이 위치
        for (int i = 0; i < storeCnt; i++) {
            sum += BFS(position[i]);
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int BFS(int[] target) {
        // BFS 마다 초기화
        visited = new boolean[M + 1][N + 1];
        dist = new int[M + 1][N + 1];

        // 최초 위치는 동근이 위치
        int nowX = dPos[0];
        int nowY = dPos[1];
        visited[nowX][nowY] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{ nowX, nowY });

        while (!queue.isEmpty()) {
            int[] nowPos = queue.poll();
            nowX = nowPos[0];
            nowY = nowPos[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (isPossiblePosition(nextX, nextY) && !visited[nextX][nextY]) {
                    dist[nextX][nextY] = dist[nowX][nowY] + 1;
                    visited[nextX][nextY] = true;
                    queue.add(new int[] { nextX, nextY });
                }
            }
        }

        return dist[target[0]][target[1]];
    }

    private static boolean isPossiblePosition(int x, int y) {
        // 전체 배열의 범위를 벗어나지 않으면서, 가장자리여야함
        return ((0 <= x && x <= M) && (0 <= y && y <= N)) && (x == 0 || x == M || y == 0 || y == N);
    }
}