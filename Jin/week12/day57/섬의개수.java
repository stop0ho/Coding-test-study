package Jin.week12.day57;

import java.io.*;
import java.util.*;

public class 섬의개수 {
    static int[] dx = { 0, -1, 0, 1, -1, -1, 1, 1 };
    static int[] dy = { 1, 0, -1, 0, 1, -1, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 1-1. 0 0 입력 시 종료
            if (w == 0 && h == 0) break;

            // 2. 2차원 배열 생성
            int[][] island = new int[w][h];
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < h; j++) {
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;
            // 3. 탐색하기
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    // 섬인 경우 탐색 시작
                    if (island[i][j] == 1) {
                        result++;
                        DFS(island, i, j);
                    }
                }
            }

            bw.write(result + "\n");
        }

        // 4. 결과 반환
        bw.flush();
        bw.close();
        br.close();
    }

    // 3-1. 8방향 탐색
    private static void DFS(int[][] island, int x, int y) {
        // 섬에 방문하면 0으로 변경
        island[x][y] = 0;

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            // 지도를 벗어나지 않으면서 이동이 가능할 섬일 경우 계속 탐색 진행
            if (isPossiblePosition(island, nextX, nextY) && island[nextX][nextY] == 1) {
                DFS(island, nextX, nextY);
            }
        }
    }

    // 3-2. 2차원 배열 내 범위가 맞는지 확인
    private static boolean isPossiblePosition(int[][] island, int x, int y) {
        int W = island.length;
        int H = island[0].length;

        return (0 <= x && x < W) && (0 <= y && y < H);
    }
}