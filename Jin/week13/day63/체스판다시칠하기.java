package Jin.week13.day63;

import java.io.*;
import java.util.*;

public class 체스판다시칠하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                int wResult = 0;
                int bResult = 0;

                // 맨 왼쪽 위 칸이 흰색인 경우
                boolean isWhite = true;
                for (int k = i; k < i + 8; k++) {
                    if (k != i) isWhite = !isWhite;
                    for (int l = j; l < j + 8; l++) {
                        if (isWhite) {
                            if (board[k][l] != 'W') wResult++;
                            isWhite = false;
                        } else {
                            if (board[k][l] != 'B') wResult++;
                            isWhite = true;
                        }
                    }
                }

                // 맨 왼쪽 위 칸이 검은색인 경우
                isWhite = false;
                for (int k = i; k < i + 8; k++) {
                    if (k != i) isWhite = !isWhite;
                    for (int l = j; l < j + 8; l++) {
                        if (isWhite) {
                            if (board[k][l] != 'W') bResult++;
                            isWhite = false;
                        } else {
                            if (board[k][l] != 'B') bResult++;
                            isWhite = true;
                        }
                    }
                }

                min = Math.min(min, Math.min(wResult, bResult));
            }
        }

        bw.write(min + "");
        bw.flush();
        bw.close();
        br.close();
    }
}