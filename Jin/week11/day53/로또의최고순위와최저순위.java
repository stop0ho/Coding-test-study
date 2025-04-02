package Jin.week11.day53;

import java.util.*;

public class 로또의최고순위와최저순위 {
    public int[] solution(int[] lottos, int[] win_nums) {

        Set<Integer> winNums = new HashSet<>();
        for (int num : win_nums) {
            winNums.add(num);
        }

        int containNum = 0;
        int zeroNum = 0;
        for (int num : lottos) {
            // 0인 경우
            if (num == 0) {
                zeroNum++;
                continue;
            }

            // 0이 아닌 경우
            if (winNums.contains(num)) containNum++;
        }

        int[] ranking = { 6, 6, 5, 4, 3, 2, 1 };
        int[] answer = { ranking[containNum + zeroNum], ranking[containNum] };
        return answer;
    }
}
