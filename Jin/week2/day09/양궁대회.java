package Jin.week2.day09;

public class 양궁대회 {
    private static int[] apeach;
    private static int[] ryan = new int[11]; // 라이언이 쏜 화살 배열

    private static int[] result; // 정답 배열

    private static int MAX = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        apeach = info;

        DFS(0, n);

        return MAX != -1 ? result : new int[]{-1};
    }

    private static void DFS(int arrow, int n) {
        // 화살
        if (arrow == n) {
            int diff = getDiff();

            if (MAX <= diff) {
                MAX = diff;
                // 꼭 clone 으로 복사해서 ryan 배열이 변해도 값이 변하지 않도록 하기
                result = ryan.clone();
            }

            return;
        }

        // 어피치보다 딱 한 개만 더 쏘면 점수를 얻는 거니까
        for (int i = 0; (i < 11) && (ryan[i] <= apeach[i]); i++) {
            ryan[i] += 1;
            DFS(arrow + 1, n);
            ryan[i] -= 1;
        }
    }

    private static int getDiff() {
        int apeachScore = 0;
        int lionScore = 0;

        for (int i = 0; i < 11; i++) {
            // 둘 다 안 쏜 건 더할 필요 없으니까
            if (apeach[i] + ryan[i] == 0) continue;

            if (apeach[i] >= ryan[i]) {
                apeachScore += (10 - i);
            } else {
                lionScore += (10 - i);
            }
        }
        int diff = lionScore - apeachScore;
        return (diff > 0) ? diff : -1;
    }
}
