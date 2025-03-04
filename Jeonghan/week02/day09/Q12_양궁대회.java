package week02.day09;

import java.util.*;

public class Q12_양궁대회 {

    private static int max;
    private static int[] apeachInfo;
    private static int[] answer;

    public int[] solution(int n, int[] info) {
        max = 0;
        apeachInfo = info;
        answer = new int[11];
        bt(0, n, new int[11]);
        return max == 0 ? new int[] {-1} : answer;
    }

    private void bt(int index, int cnt, int[] ryanInfo) {
        if (index == 11) {
            ryanInfo[10] += cnt;
            calculateScore(ryanInfo);
            ryanInfo[10] -= cnt;
            return;
        }
        if (cnt == 0) {
            calculateScore(ryanInfo);
            return;
        }
        if (apeachInfo[index] < cnt) {
            ryanInfo[index] = apeachInfo[index] + 1;
            bt(index + 1, cnt - apeachInfo[index] - 1, ryanInfo);
            ryanInfo[index] = 0;
        }
        bt(index + 1, cnt, ryanInfo);
    }

    private void calculateScore(int[] ryanInfo) {
        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < 11; i++) {
            if (ryanInfo[i] > apeachInfo[i]) {
                ryanScore += 10 - i;
            } else if (ryanInfo[i] < apeachInfo[i]) {
                apeachScore += 10 - i;
            }
        }

        int diff = ryanScore - apeachScore;
        if (max <= diff) {
            if (max == diff) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] < ryanInfo[i]) {
                        break;
                    } else if (answer[i] > ryanInfo[i]) {
                        return;
                    }
                }
            }
            max = diff;
            answer = Arrays.copyOf(ryanInfo, 11);
        }
    }

}
