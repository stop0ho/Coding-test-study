package week10.day48;

import java.util.*;

public class Q50_숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0, j = 0; i < A.length; i++) {
            while (j < B.length && B[j] <= A[i]) {
                j++;
            }
            if (j == B.length) {
                break;
            }
            answer++;
            j++;
        }
        return answer;
    }
}
