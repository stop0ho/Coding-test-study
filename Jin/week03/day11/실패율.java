package Jin.week03.day11;

import java.util.*;

public class 실패율 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stageChallenger = new int[N + 2];
        int challengerNum = stages.length;

        // 현재 스테이지에 몇 명이 존재하는 지 확인
        for (int i = 0; i < challengerNum; i++) {
            stageChallenger[stages[i]]++;
        }

        List<double[]> fail = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            // 해당 스테이지에 0명이 존재할 경우, 실패율은 무조건 0
            if (stageChallenger[i] == 0) {
                fail.add(new double[]{i, 0});
            } else {
                // 해당 스테이지에 사람이 존재할 경우에만 실패율 계산
                fail.add(new double[]{i, (double) stageChallenger[i] / challengerNum});
                // 다음 스테이지에는 현재 스테이지에 존재하는 사람이 없을테니 빼기해줘야 함!
                challengerNum -= stageChallenger[i];
            }


        }

        // 실패율을 기준으로 내림차순, 실패율이 같을 경우 작은 번호의 스테이지가 먼저 올 수 있게
        fail.sort(
                (a, b) -> a[1] == b[1] ?
                        Double.compare(a[0], b[0]) : Double.compare(b[1], a[1])
        );

        // 스테이지 번호만 저장
        for (int i = 0; i < N; i++) {
            answer[i] = (int) fail.get(i)[0];
        }

        return answer;
    }
}