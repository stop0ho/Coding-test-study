package Jin.week07.day34;

import java.util.*;

public class 괄호변환 {
    public String solution(String p) {
        String answer = "";

        // 입력값이 빈 문자열 또는 올바른 괄호 문자열일 경우 그대로 반환
        if (isCorrect(p)) return p;
        String[] uv = divide(p);
        answer = getCorrectString(uv);

        return answer;
    }

    private boolean isCorrect(String p) {
        // 입력값이 올바른 괄호 문자열일 경우
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if (c == '(') {
                // c가 왼쪽 괄호일 경우 스택에 넣어줌
                stack.push(p.charAt(i));
            } else {
                // c가 오른쪽 괄호일 경우, 스택의 최상단을 확인해서 올바른 괄호 문자열인지 확인
                if (stack.isEmpty()) {
                    // 스택이 비어있는 경우, 올바른 괄호 문자열이 아님
                    return false;
                }
                if (stack.peek() == '(') {
                    // 왼쪽 괄호일 경우 올바른 괄호 문자열(짝이 맞으므로)이므로, pop
                    stack.pop();
                }
            }
        }

        // 반복문을 다 돌았을 때, 스택이 비어있어야지 올바른 괄호 문자열임
        if (stack.isEmpty()) return true;
        return false;
    }

    /*
    문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리.
    u는 균형잡힌 괄호 문자열로 더 이상 분리가 안되어야 함 -> 왼쪽 괄호 오른쪽 괄호 개수 세서, 같아지는 순간 바로 끝내기
    v는 나머지 문자열 전부 하면 되니까!
    */
    private String[] divide(String w) {
        // 여기다가 저장할 거임!
        String[] uv = new String[2];

        // u랑 v가 나눠지는 index 위치 알아야 하니까 따로 저장
        int index = 0;

        int leftCnt = 0, rightCnt = 0;
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);

            // 괄호 개수 세주기
            if (c == '(') leftCnt++;
            else rightCnt++;

            // 개수가 같아지면, u는 여기서 종료.
            if (leftCnt == rightCnt) {
                index = i + 1;
                break;
            }
        }

        uv[0] = w.substring(0, index);
        uv[1] = w.substring(index);

        return uv;
    }

    // 나눠진 문자들이 올바른 문자열인지 확인하고, 그렇지 않다면 재귀 수행
    private String getCorrectString(String[] uv) {
        // 재귀 너무 깊어지지 않도록 빈문자열만 있으면 바로 반환하기
        if (uv[0].isEmpty() && uv[1].isEmpty()) {
            return "";
        }

        StringBuilder correctString = new StringBuilder();

        if (isCorrect(uv[0])) {
            // u가 올바른 문자열일 경우, v에 대해 재귀 수행
            String[] vArr = divide(uv[1]);
            String correctVStr = getCorrectString(vArr);
            correctString.append(uv[0]).append(correctVStr);
        } else {
            // u가 올바른 문자열이 아닌 경우, 4번 수행
            correctString.append(makeCorrect(uv));
        }

        return correctString.toString();
    }

    // 4. 올바른 문자열이 아니라면 아래 과정을 수행합니다.
    private String makeCorrect(String[] uv) {
        // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
        StringBuilder correct = new StringBuilder("(");

        // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
        String[] newUv = divide(uv[1]);
        String v = getCorrectString(newUv);
        correct.append(v);

        // 4-3. ')'를 다시 붙입니다.
        correct.append(")");

        // 4-4. u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        for (int i = 1; i < uv[0].length() - 1; i++) {
            char c = uv[0].charAt(i);
            if (c == '(') correct.append(")");
            else correct.append("(");
        }

        // 4-5. 생성된 문자열을 반환합니다.
        return correct.toString();
    }
}
