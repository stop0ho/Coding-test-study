package Jin.week12.day59;

// BFS

import java.io.*;
import java.util.*;

public class 적록색약 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static char[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력
        int N = Integer.parseInt(br.readLine());

        // 2. 그림 정보 저장하기
        matrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        // 2-1. 같은 크기의 방문 배열 2개 준비
        boolean[][] visitedNotRG = new boolean[N][N];
        boolean[][] visitedRG = new boolean[N][N];

        // 3. 적록색약 여부에 따라 2번 탐색
        int notRGCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedNotRG[i][j]) {
                    notRGCnt++;
                    visitedNotRG[i][j] = true;
                    bfsNotRG(i, j, visitedNotRG);
                }
            }
        }

        int RGCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedRG[i][j]) {
                    RGCnt++;
                    visitedRG[i][j] = true;
                    bfsRG(i, j, visitedRG);
                }
            }
        }

        bw.write(notRGCnt + "\n" + RGCnt);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfsNotRG(int x, int y, boolean[][] visitedNotRG) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{ x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                // 3-1. 적록색약 X - 색상이 완전히 일치할 때만 탐색
                if (isPossiblePosition(nextX, nextY) && !visitedNotRG[nextX][nextY]) {
                    char nowColor = matrix[nowX][nowY];
                    char nextColor = matrix[nextX][nextY];

                    if (nowColor == nextColor) {
                        visitedNotRG[nextX][nextY] = true;
                        queue.add(new int[]{ nextX, nextY });
                    }
                }
            }
        }
    }

    private static void bfsRG(int x, int y, boolean[][] visitedRG) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{ x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                // 3-1. 적록색약 X - 색상이 완전히 일치할 때만 탐색
                if (isPossiblePosition(nextX, nextY) && !visitedRG[nextX][nextY]) {
                    char nowColor = matrix[nowX][nowY];
                    char nextColor = matrix[nextX][nextY];

                    if (isSameColorForRG(nowColor, nextColor)) {
                        visitedRG[nextX][nextY] = true;
                        queue.add(new int[]{ nextX, nextY });
                    }
                }
            }
        }
    }

    private static boolean isPossiblePosition(int x, int y) {
        return (0 <= x && x < matrix.length) && (0 <= y && y < matrix.length);
    }

    private static boolean isSameColorForRG(char now, char next) {
        // B인 경우
        if (now == 'B' && next == 'B') return true;

        // R 또는 G인 경우
        if ((now == 'R' || now == 'G') && (next == 'R' || next == 'G')) return true;

        return false;
    }
}


/*
* BFS
public class Main {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static char[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력
        int N = Integer.parseInt(br.readLine());

        // 2. 그림 정보 저장하기
        matrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        // 2-1. 같은 크기의 방문 배열 2개 준비
        boolean[][] visitedNotRG = new boolean[N][N];
        boolean[][] visitedRG = new boolean[N][N];

        // 3. 적록색약 여부에 따라 2번 탐색
        int notRGCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedNotRG[i][j]) {
                    notRGCnt++;
                    visitedNotRG[i][j] = true;
                    bfsNotRG(i, j, visitedNotRG);
                }
            }
        }

        int RGCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedRG[i][j]) {
                    RGCnt++;
                    visitedRG[i][j] = true;
                    bfsRG(i, j, visitedRG);
                }
            }
        }

        bw.write(notRGCnt + "\n" + RGCnt);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfsNotRG(int x, int y, boolean[][] visitedNotRG) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{ x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                // 3-1. 적록색약 X - 색상이 완전히 일치할 때만 탐색
                if (isPossiblePosition(nextX, nextY) && !visitedNotRG[nextX][nextY]) {
                    char nowColor = matrix[nowX][nowY];
                    char nextColor = matrix[nextX][nextY];

                    if (nowColor == nextColor) {
                        visitedNotRG[nextX][nextY] = true;
                        queue.add(new int[]{ nextX, nextY });
                    }
                }
            }
        }
    }

    private static void bfsRG(int x, int y, boolean[][] visitedRG) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{ x, y });

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                // 3-1. 적록색약 X - 색상이 완전히 일치할 때만 탐색
                if (isPossiblePosition(nextX, nextY) && !visitedRG[nextX][nextY]) {
                    char nowColor = matrix[nowX][nowY];
                    char nextColor = matrix[nextX][nextY];

                    if (isSameColorForRG(nowColor, nextColor)) {
                        visitedRG[nextX][nextY] = true;
                        queue.add(new int[]{ nextX, nextY });
                    }
                }
            }
        }
    }

    private static boolean isPossiblePosition(int x, int y) {
        return (0 <= x && x < matrix.length) && (0 <= y && y < matrix.length);
    }

    private static boolean isSameColorForRG(char now, char next) {
        // B인 경우
        if (now == 'B' && next == 'B') return true;

        // R 또는 G인 경우
        if ((now == 'R' || now == 'G') && (next == 'R' || next == 'G')) return true;

        return false;
    }
}
* */