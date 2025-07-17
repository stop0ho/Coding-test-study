package Jin.week13.day64;

import java.io.*;
import java.util.*;

public class 오목 {
    // 동 남 북동 남동 순서
    static int[] dx = { 0, 1, -1, 1 };
    static int[] dy = { 1, 0, 1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int[][] board = new int[19][19];
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 탐색
        boolean gameEnd = false;
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                if (!gameEnd && board[x][y] != 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        // 역방향에 같은 색 바둑알이 존재한다면, 이미 확인한 케이스이므로 스킵
                        int prevX = x - dx[dir];
                        int prevY = y - dy[dir];
                        if (isPossiblePosition(prevX, prevY) &&
                                board[prevX][prevY] == board[x][y]) continue;

                        if (!gameEnd && count(x, y, dir, board) == 5) {
                            gameEnd = true;
                            bw.write(board[x][y] + "\n" + (x + 1) + " " + (y + 1));
                            break;
                        }
                    }
                }
            }
            if (gameEnd) break;
        }

        if (!gameEnd) bw.write("0");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int count(int x, int y, int dir, int[][] board) {
        int nextX = x + dx[dir];
        int nextY = y + dy[dir];

        // 바둑판의 범위를 넘어갔을 경우 탐색 종료
        if (!isPossiblePosition(nextX, nextY)) return 1;

        // 다음 탐색 위치와 현재 위치의 바둑알의 색이 다르다면 탐색 종료
        if (board[nextX][nextY] != board[x][y]) return 1;

        // 진행 방향에 동일한 색의 바둑알이 있는 경우 계속 탐색
        return count(nextX, nextY, dir, board) + 1;
    }

    private static boolean isPossiblePosition(int x, int y) {
        return (0 <= x && x <= 18) && (0 <= y && y <= 18);
    }
}