package Jin.week5.day21;

public class 연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {

        int left = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];

        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];

            while (sum > k && left < right) {
                sum -= sequence[left++];
            }

            if (sum == k) {
                if (right - left < minLength) {
                    minLength = right - left;
                    answer[0] = left;
                    answer[1] = right;
                }
            }
        }

        return answer;
    }
}
