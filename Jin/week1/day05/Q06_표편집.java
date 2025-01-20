package Jin.week1.day05;

import java.util.Arrays;
import java.util.Stack;

public class Q06_표편집 {
    Stack<Integer> deleted;
    int[] up;
    int[] down;

    public String solution(int n, int k, String[] cmd) {
        deleted = new Stack<>();
        up = new int[n + 2];
        down = new int[n + 2];

        // 기존 위치 초기화
        for (int i = 0; i < n + 2; i++) {
            up[i] = i - 1;
            down[i] = i + 1;
        }
        
        // 현재 위치, 우리는 가상 공간을 만들었으니까 한칸 더하기 해주는 모습
        k++;
        
        for (String c : cmd) {
            if (c.startsWith("C")) {
                deleted.push(k);
                up[down[k]] = up[k];
                down[up[k]] = down[k];
                
                // 가장 아래 행을 삭제했을 경우만 위의 행,
                // 나머지의 경우는 아래 행으로 선택을 옮겨야 하니까
                k = (n < down[k] ? up[k] : down[k]);
            } else if (c.startsWith("Z")) {
                int restore = deleted.pop();
                down[up[restore]] = restore;
                up[down[restore]] = restore;
            } else {
                String[] s = c.split(" ");
                int x = Integer.parseInt(s[1]);

                for (int i = 0; i < x; i++) {
                    k = s[0].equals("U") ? up[k] : down[k];
                }
            }
        }

        char[] answer = new char[n];
        Arrays.fill(answer, 'O');
        for (int i : deleted) {
            answer[i - 1] = 'X';
        }

        return new String(answer);
    }
}
