package Jin.week4.day18;

import java.util.*;

public class 튜플 {
    public int[] solution(String s) {
        // 입력값을 String[] 로 관리
        s = s.substring(2, s.length()- 2).replace("},{", "-");
        String[] sArr = s.split("-");

        // length 기준으로 정렬
        Arrays.sort(sArr, Comparator.comparingInt(String::length));

        int[] answer = new int[sArr.length];
        int index = 0;
        for (String str : sArr) {
            String[] check = str.split(",");

            for (int i = 0; i < check.length; i++) {
                int num = Integer.parseInt(check[i]);

                // 배열 내에 값이 존재하지 않는다면 배열에 추가
                boolean exists = Arrays.stream(answer).anyMatch(n -> n == num);
                if (!exists) {
                    answer[index++] = num;
                }
            }
        }

        return answer;
    }
}
