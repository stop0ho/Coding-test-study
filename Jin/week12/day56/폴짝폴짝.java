package Jin.week12.day56;

import java.io.*;
import java.util.*;

public class 폴짝폴짝 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] bridge = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            bridge[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); // 시작 위치
        int b = Integer.parseInt(st.nextToken()); // 도착 위치

        int answer = BFS(a, b, bridge);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int BFS(int a, int b, int[] bridge) {
        Queue<int[]> queue = new LinkedList<>(); // BFS 탐색에 사용할 큐 생성
        boolean[] visited = new boolean[N + 1]; // 방문 배열 생성

        queue.offer(new int[]{a, 0});
        visited[a] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int jumpCnt = current[1];

            // b 위치에 도달 시 점프횟수 반환
            if (pos == b) return jumpCnt;

            // 현재 위치에 작성된 수의 배수 index로만 뛸 수 있음
            for (int i = 1; pos + i * bridge[pos] <= N; i++) {
                int nextPos = pos + i * bridge[pos];
                if (!visited[nextPos]) {
                    queue.offer(new int[]{nextPos, jumpCnt + 1});
                    visited[nextPos] = true;
                }
            }

            for (int i = 1; pos - i * bridge[pos] >= 1; i++) {
                int nextPos = pos - i * bridge[pos];
                if (!visited[nextPos]) {
                    queue.offer(new int[]{nextPos, jumpCnt + 1});
                    visited[nextPos] = true;
                }
            }
        }

        return -1;
    }
}