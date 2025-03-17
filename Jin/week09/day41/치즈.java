package Jin.week09.day41;

import java.io.*;
import java.util.*;

public class 치즈 {
    static int n, m;
    static int[][] matrix;
    static boolean[][] visited;

    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, -1, 0, 1 };

    static int cheeseCnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                // 전체 치즈 개수 세기
                if (matrix[i][j] == 1) cheeseCnt++;
            }
        }

        System.out.println(getTime());
    }

    private static int getTime() {
        int time = 0;

        while (cheeseCnt != 0) {
            time++;

            // 외부 공기와 치즈 내부 공기를 구분
            checkAir();

            ArrayList<int[]> meltCheeseList = findMeltingCheese();
            for (int[] position : meltCheeseList) {
                int x = position[0];
                int y = position[1];
                matrix[x][y] = 0;
                cheeseCnt--;
            }
        }

        return time;
    }

    private static void checkAir() {
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) temp[i][j] = 1;
                    // 우선 공기는 모두 2로 설정하기
                else temp[i][j] = 2;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // 문제 조건 상 가장자리는 무조건 다 외부 공기이니까 가장자리 먼저 처리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || i == n - 1 || j == 0 || j == m - 1) && temp[i][j] == 2) {
                    queue.offer(new int[]{ i, j });
                    visited[i][j] = true;
                    temp[i][j] = 0;
                }
            }
        }

        // 가장자리에서 접근 가능한 곳은 외부 공기이므로 0으로 변경
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValidPosition(nx, ny) && !visited[nx][ny] && temp[nx][ny] == 2) {
                    visited[nx][ny] = true;
                    temp[nx][ny] = 0;
                    queue.offer(new int[]{ nx, ny });
                }
            }
        }

        // 원본 배열에 반영
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    private static ArrayList<int[]> findMeltingCheese() {
        ArrayList<int[]> meltCheeseList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    int count = 0;

                    // 인접한 외부 공기의 수 세기
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (isValidPosition(nx, ny) && matrix[nx][ny] == 0) {
                            count++;
                        }
                    }

                    if (count >= 2) {
                        meltCheeseList.add(new int[]{ i, j });
                    }
                }
            }
        }

        return meltCheeseList;
    }

    private static boolean isValidPosition(int x, int y) {
        return (0 <= x && x < n) && (0 <= y && y < m);
    }
}