package Jin.week10.day47;

import java.util.*;

public class 두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        int MAX_TRY = (queue1.length + queue2.length) * 2;

        // 배열을 큐로 변경
        // 배열로 선입선출하기엔 계산이 귀찮아질것 같아서 선택
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        // 전체 합 구해두기
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
        }
        for (int i = 0; i < queue2.length; i++) {
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }

        int answer = 0;
        while (true) {
            if (sum1 > sum2) {
                int num = q1.poll();
                sum1 -= num;
                sum2 += num;
                q2.add(num);
                answer++;
            } else if (sum1 < sum2) {
                int num = q2.poll();
                sum2 -= num;
                sum1 += num;
                q1.add(num);
                answer++;
            }

            // 같아지는 순간이 있으면 종료
            if (sum1 == sum2) {
                break;
            }

            // 특정횟수 이상 반복 시, 어떤 방법을 쓰더라도 원소 합을 같게 만들지 못하는 것이기 때문에 break
            if (answer > MAX_TRY) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
