package Jin.week11.day52;

public class 파괴되지않은건물 {
    public int solution(int[][] board, int[][] skill) {

        int row = board.length;
        int column = board[0].length;

        // 기존 배열보다 한칸씩 더 큰 누적합 배열 만들기
        int[][] sum = new int[row + 1][column + 1];
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];

            if (type == 1) {
                // 공격
                sum[r1][c1] -= degree;
                sum[r1][c2 + 1] += degree;
                sum[r2 + 1][c1] += degree;
                sum[r2 + 1][c2 + 1] -= degree;
            } else {
                // 회복
                sum[r1][c1] += degree;
                sum[r1][c2 + 1] -= degree;
                sum[r2 + 1][c1] -= degree;
                sum[r2 + 1][c2 + 1] += degree;
            }
        }

        getSumMatrix(sum);
        int answer = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int finalNum = board[i][j] + sum[i][j];
                if (finalNum > 0) answer++;
            }
        }

        return answer;
    }

    private static void getSumMatrix(int[][] sum) {
        for (int i = 0; i < sum.length; i++) {
            for (int j = 1; j < sum[0].length; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int i = 1; i < sum.length; i++) {
            for (int j = 0; j < sum[0].length; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }
    }
}
