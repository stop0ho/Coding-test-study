package Jin.week05.day23;

public class 징검다리건너기 {
    public int solution(int[] stones, int k) {
        int left = 1, right = 200000000, answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean isPossible(int[] stones, int k, int mid) {
        int zero = 0;

        for (int stone : stones) {
            if (stone - mid < 0) {
                zero++;

                if (zero >= k) {
                    return false;
                }
            } else {
                zero = 0;
            }
        }
        return true;
    }
}
