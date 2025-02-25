package Jin.week6.day27;

public class 택배배달과수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int d = cap, p = 0;

        for (int i = n - 1; i >= 0; i--) {
            d -= deliveries[i];
            p += pickups[i];

            while (d < 0 || p > cap) {
                d += cap;
                p -= cap;
                answer += (i + 1) * 2;
            }
        }

        return answer;
    }
}
