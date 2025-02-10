package Jin.week4.day17;

import java.util.*;

class 가장큰정사각형찾기 {
    public int solution(int [][]board)
    {
        int row = board.length;
        int col = board[0].length;

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == 1) {
                    int up = board[i - 1][j];
                    int left = board[i][j - 1];
                    int upLeft = board[i - 1][j - 1];

                    board[i][j] += Math.min(up, Math.min(left, upLeft));
                }
            }
        }

        int max = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .max().getAsInt();

        return max * max;
    }
}