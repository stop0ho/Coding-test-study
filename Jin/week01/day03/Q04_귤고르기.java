package Jin.week01.day03;

import java.util.Arrays;

public class Q04_귤고르기 {
    static int MAX_SIZE = 10000001;
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] num = new int[MAX_SIZE];

        // 크기 별로 귤을 담기
        for (int t : tangerine) {
            num[t]++;
        }

        Arrays.sort(num);
        for (int i = num.length - 1; i > 0; i--) {
            k -= num[i];
            answer++;

            if (k <= 0) {
                break;
            }
        }

        return answer;
    }
}
