package week09.day43;

import java.util.*;

public class Q48_단어변환 {
    private static final Map<Integer, List<Integer>> map = new HashMap<>();
    private static int answer;
    private static String[] ws;
    private static boolean[] check;

    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        ws = new String[words.length + 1];
        check = new boolean[words.length + 1];

        ws[0] = begin;
        System.arraycopy(words, 0, ws, 1, words.length);

        for (int i = 0; i < ws.length - 1; i++) {
            char[] ic = ws[i].toCharArray();

            for (int j = i + 1; j < ws.length; j++) {
                char[] jc = ws[j].toCharArray();
                int cnt = 0;

                for (int k = 0; k < jc.length; k++) {
                    if (ic[k] == jc[k]) {
                        continue;
                    }
                    if (++cnt == 2) {
                        break;
                    }
                }

                if (cnt == 1) {
                    map.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                    map.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                }
            }
        }

        check[0] = true;
        solution(0, 0, target);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void solution(int index, int cnt, String target) {
        if (answer < cnt) {
            return;
        }
        if (ws[index].equals(target)) {
            answer = cnt;
            return;
        }
        List<Integer> next = map.getOrDefault(index, new ArrayList<>());
        for (int i = 0; i < ws.length; i++) {
            if (!check[i] && next.contains(i)) {
                check[i] = true;
                solution(i, cnt + 1, target);
                check[i] = false;
            }
        }
    }
}
