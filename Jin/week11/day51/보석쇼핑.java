package Jin.week11.day51;

import java.util.*;

public class 보석쇼핑 {
    static int MAX_NUM = 100001;
    public int[] solution(String[] gems) {
        int[] answer = { 0, MAX_NUM };

        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }

        // 전체 보석 가짓수는 set의 사이즈
        int totalGem = set.size();

        Map<String, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        window.put(gems[0], 1);

        while (left < gems.length) {
            if (window.size() == totalGem) {
                // 보석이 모두 포함된 경우

                if (answer[1] - answer[0] > right - left) {
                    // 진열대 번호 업데이트
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                window.put(gems[left], window.get(gems[left]) - 1);

                if (window.get(gems[left]) == 0) {
                    window.remove(gems[left]);
                }
                left++;
            } else {
                // 보석이 포함되지 않은 경우, 보석 추가
                right++;

                // 범위를 벗어나는 경우, 더 이상 진행이 안되는 경우이므로 break해서 종료
                if (right >= gems.length) break;

                window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);
            }
        }

        return answer;
    }
}
