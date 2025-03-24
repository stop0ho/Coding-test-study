package Jin.week02.day10;

import java.util.*;

public class 사라지는발판 {
    // 문제에서 주어주는 애들도 선언해서 쓰면 파라미터로 안넣어도 되네요!! 왜 이 생각은 안했을까
    private static int ROW, COL;
    private static int[][] BOARD;

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};
    private static boolean[][] visited;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        BOARD = board;
        ROW = board.length;
        COL = board[0].length;

        visited = new boolean[ROW][COL];

        return DFS(aloc, bloc, 0).step;
    }

    private static Result DFS(int[] aPlayer, int[] bPlayer, int turn) {
        // 항상 A 플레이어가 먼저 시작할테니, 홀짝으로 턴을 확인 (A가 0, 2, .. 짝수턴)
        boolean isEvenTurn = (turn % 2 == 0);

        int[] nowPlayer = isEvenTurn ? aPlayer : bPlayer;
        boolean canMove = false;
        boolean isOpponentWinner = true;

        ArrayList<Integer> winSteps = new ArrayList<>();
        ArrayList<Integer> lostSteps = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int nowX = nowPlayer[0] + dx[i];
            int nowY = nowPlayer[1] + dy[i];

            if (isValid(nowX, nowY) && !visited[nowX][nowY]) {
                canMove = true;

                // 두 플레이어의 위치가 같아지는 경우(= B가 A 서 있는 위치에 온 경우) 무조건 A 플레이어 승리
                if (aPlayer[0] == bPlayer[0] && aPlayer[1] == bPlayer[1]) {
                    // 그 다음 턴에 A 움직이면서 종료되니까 turn + 1을 반환
                    return new Result(true, turn + 1);
                }

                // 현재 위치가 아닌, 이동하기 전 발판이 사라지는 거니까!! 주의해서 작성
                visited[nowPlayer[0]][nowPlayer[1]] = true;

                Result result =
                        isEvenTurn ?
                        DFS(new int[]{nowX, nowY}, bPlayer, turn + 1) :
                        DFS(aPlayer, new int[]{nowX, nowY}, turn + 1);

                // 재귀가 끝나면 다른 경우도 탐색해야 하니까
                visited[nowPlayer[0]][nowPlayer[1]] = false;

                // and 비트 연산
                // 상대 플레이어가 이긴 경우에만 true로 유지할 수 있도록!
                isOpponentWinner &= result.win;

                if (result.win) {
                    winSteps.add(result.step);
                } else {
                    lostSteps.add(result.step);
                }
            }
        }

        // 이동할 수 없는 경우, 패배
        if (!canMove) {
            return new Result(false, turn);
        }

        // 상대 플레이어가 승리한 경우 -> 최대 횟수로 움직여서 져야 최선을 다한 거니까
        if (isOpponentWinner) {
            return new Result(false,
                    winSteps.stream()
                            .max(Comparator.comparingInt(o -> o))
                            .get());
        }

        // 현재 플레이어가 승리한 경우 -> 최단 횟수로 움직여서 이겨야 최선을 다한 거니까
        return new Result(true,
                lostSteps.stream()
                        .min(Comparator.comparingInt(o -> o))
                        .get());
    }

    private static boolean isValid(int x, int y) {
        // 보드 내이면서 1인 위치만 방문할 수 있으니까
        return (x >= 0 && x < ROW && y >= 0 && y < COL) && BOARD[x][y] == 1;
    }

    private static class Result {
        boolean win;
        int step;

        public Result(boolean win, int step) {
            this.win = win;
            this.step = step;
        }
    }
}
