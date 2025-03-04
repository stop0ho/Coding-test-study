package week07.day31;

import java.util.*;

public class Q35_110옮기기 {

    public String[] solution(String[] s) {
        return Arrays.stream(s)
            .map(this::solution)
            .toArray(String[]::new);
    }

    private String solution(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int cnt = 0;

        for (char c : s.toCharArray()) {
            stack.push(c);

            if (stack.size() >= 3) {
                int len = stack.size();
                if (stack.get(len - 3) == '1' && stack.get(len - 2) == '1' && stack.get(len - 1) == '0') {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    cnt++;
                }
            }
        }

        for (char c : stack) {
            sb.append(c);
        }

        int index = sb.lastIndexOf("0") + 1;
        return sb.substring(0, index) + "110".repeat(cnt) + sb.substring(index);
    }
}
