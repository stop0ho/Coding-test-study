package day04;

public class Q05_기지국설치 {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int ptr = 0;
        int range = 1 + 2 * w;

        for (int station : stations) {
            int lb = Math.max(station - w, 1);
            int rb = Math.min(station + w, n);

            if (ptr < lb - 1) {
                answer += Math.ceil((lb - ptr - 1) / (double) range);
            }
            ptr = rb;
        }

        if (ptr < n) {
            answer += Math.ceil((n - ptr) / (double) range);
        }
        return answer;
    }
}
