package Jin.week02.day08;

import java.util.*;

public class 메뉴리뉴얼 {
    static HashMap<String, Integer> newMenu;

    public static String[] solution(String[] orders, int[] course) {
        // <메뉴 조합, 주문된 수량> 형태로 저장되는 HashMap 생성
        newMenu = new HashMap<>();

        for (String o : orders) {
            char[] order = o.toCharArray();
            Arrays.sort(order);
            
            // 코스 가짓수에 맞는 것만 저장해야 하니까
            for (int c : course) {
                combinations(0, order, "", c);
            }
        }

        List<String> answerList = new ArrayList<>();
        for (int c : course) {
            // 최소 주문 수는 2 이상
            int max = 2;

            for (String key : newMenu.keySet()) {
                int value = newMenu.get(key);

                // 특정 가짓수에서
                if (key.length() == c) {
                    // 더 큰 값이 들어오면 변경
                    if (value > max) {
                        max = value;
                        answerList.removeIf(s -> s.length() == c);
                        answerList.add(key);
                    } else if (value == max) {
                        // 값이 같다면 추가
                        answerList.add(key);
                    }
                }
            }
        }

        // 오름차순으로 정렬
        String[] answer = answerList.toArray(new String[0]);
        Arrays.sort(answer);

        return answer;
    }

    private static void combinations(int idx, char[] order, String result, int courseNum) {
        // 코스 가짓수에 맞는 요리들만 저장하기
        if (courseNum == result.length()) {
            newMenu.put(result, newMenu.getOrDefault(result, 0) + 1);
        }

        // 재귀로 조합 찾기
        for (int i = idx; i < order.length; i++) {
            combinations(i + 1, order, result + order[i], courseNum);
        }
    }
}
