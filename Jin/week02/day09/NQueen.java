package Jin.week02.day09;

class NQueen {
    static boolean[] column;
    static boolean[] check1;
    static boolean[] check2;

    public int solution(int n) {
        // 열 확인용
        column = new boolean[n];

        // 대각선 체크용
        check1 = new boolean[n * 2];
        check2 = new boolean[n * 2];

        // 첫 번째 행부터 탐색 시작
        return possibleQueen(n, 0);
    }

    private static int possibleQueen(int n, int x) {
        int answer = 0;

        // 모든 행에 대해서 퀸을 배치했을 경우
        if (x == n) answer++;
        else {
            // 열에 대해서 탐색 시작
            for (int i = 0; i < n; i++) {
                if (isImpossible(x, i, n)) continue;

                // 배치가 가능하다면
                column[i] = true;
                check1[x + i] = true;
                check2[x - i + n] = true;

                // 재귀 진행
                answer += possibleQueen(n, x + 1);

                // 다음 탐색을 위해 true인 값을 모두 false로 변경
                column[i] = check1[x + i] = check2[x - i + n] = false;
            }
        }

        return answer;
    }

    private static boolean isImpossible(int x, int y, int n) {
        // row[i] 해당 열에 이미 퀸이 존재
        // check1[x + y] 오른쪽 위 -> 왼쪽 아래 대각선 방향에 이미 퀸이 존재
        // check2[x - y + n] 왼쪽 위 -> 오른쪽 아래 대각선 방향에 이미 퀸이 존재
        return (column[y] || check1[x + y] || check2[x - y + n]);
    }
}