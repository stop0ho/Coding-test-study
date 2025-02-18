package week05.day21;

public class Q24_연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {
        int[] answer = { 0, Integer.MAX_VALUE };
        int sum = 0;

        for (int lb = 0, rb = 0; rb < sequence.length; rb++) {
            sum += sequence[rb];

            while (sum > k) {
                sum -= sequence[lb++];
            }

            if (sum == k && rb - lb < answer[1] - answer[0]) {
                answer = new int[] { lb, rb };
            }
        }
        return answer;
    }
}
