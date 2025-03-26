package Jin.week10.day48;

import java.util.*;

public class 숫자게임 {
    public int solution(int[] A, int[] B) {

        int len = A.length;
        Arrays.sort(A);
        Arrays.sort(B);

        // B팀의 가장 큰 숫자가 A팀의 가장 작은 숫자보다 작은 경우, B팀은 무조건 패배
        if (A[0] > B[len - 1]) return 0;

        // A팀의 가장 큰 숫자가 B팀의 가장 작은 숫자보다 작은 경우, B팀은 무조건 승리
        if (A[len - 1] < B[0]) return len;

        int answer = 0;
        int aIdx = 0, bIdx = 0;

        // A 배열은 끝까지 안돌아도 되니까.. 조건은 B 배열을 끝까지 다 돌았을 때로 설정
        while (bIdx < len) {
            if (A[aIdx] < B[bIdx]) answer++;
            else {
                while (bIdx < len) {
                    if (A[aIdx] < B[bIdx]) {
                        answer++;
                        break;
                    }
                    bIdx++;

                }
            }
            aIdx++;
            bIdx++;
        }

        return answer;
    }
}
