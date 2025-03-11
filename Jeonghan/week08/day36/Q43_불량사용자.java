package week08.day36;

import java.util.*;

public class Q43_불량사용자 {

    private static final Set<Integer> set = new HashSet<>();
    private static String[] user, ban;
    private static boolean[] check;
    private static boolean[][] matched;
    private static int answer;

    public int solution(String[] user_id, String[] banned_id) {
        user = user_id;
        ban = banned_id;
        check = new boolean[user.length];
        matched = new boolean[user.length][ban.length];
        init();
        bt(0, 0);
        return answer;
    }

    private void init() {
        String[] regex = Arrays.stream(ban)
            .map(s -> s.replace("*", "."))
            .toArray(String[]::new);

        for (int i = 0; i < user.length; i++) {
            for (int j = 0; j < ban.length; j++) {
                if (user[i].length() == ban[j].length() && user[i].matches(regex[j])) {
                    matched[i][j] = true;
                }
            }
        }
    }

    private void bt(int index, int flag) {
        if (index == ban.length) {
            if (!set.contains(flag)) {
                set.add(flag);
                answer++;
            }
            return;
        }
        for (int i = 0; i < user.length; i++) {
            if (!check[i] && matched[i][index]) {
                check[i] = true;
                bt(index + 1, (flag | (1 << i)));
                check[i] = false;
            }
        }
    }
}
