package Jin.week12.day58;

import java.io.*;
import java.util.*;

public class 버섯농장 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 2. 나무판의 정보 저장하기
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3. DFS 탐색
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    // 3-1. 각 구역이 몇 칸으로 이루어져있는지 구하기
                    int areaSize = DFS(matrix, i, j);
                    // 3-2. 각 구역별로 몇 개의 포자를 심어야 하는지 구하기
                    result += getSporeNum(areaSize, K);
                }
            }
        }

        if (result > M || result == 0) bw.write("IMPOSSIBLE\n");
        else {
            bw.write("POSSIBLE\n");
            bw.write(M - result + "");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int DFS(int[][] matrix, int x, int y) {
        // 다시 방문하지 못하게 변경
        matrix[x][y] = 1;

        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isPossiblePosition(matrix, nextX, nextY) && matrix[nextX][nextY] == 0) {
                count += DFS(matrix, nextX, nextY);
            }
        }
        return count;
    }

    private static boolean isPossiblePosition(int[][] matrix, int x, int y) {
        return (0 <= x && x < matrix.length) && (0 <= y && y < matrix.length);
    }

    private static int getSporeNum(int area, int K) {
        return (int) Math.ceil((double) area / K);
    }
}