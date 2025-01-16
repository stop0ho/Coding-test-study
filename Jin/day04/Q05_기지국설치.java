package Jin.day04;

public class Q05_기지국설치 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int coverNum = w * 2 + 1;

        // 처음부터 탐색해야 하니까
        int current = 1;
        for (int station : stations) {
            int uncovered = (station - w - 1) - current + 1;
            answer += (int) Math.ceil((double) uncovered / coverNum);

            current = station + w + 1;
        }

        if (current <= n) {
            int uncovered = n - current + 1;
            answer += (int) Math.ceil((double) uncovered / coverNum);
        }

        return answer;
    }
}
