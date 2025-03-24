package Jin.week08.day36;

import java.util.*;

public class 불량사용자 {

    static Set<HashSet<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        DFS(new HashSet<>(), 0, user_id, banned_id);
        return result.size();
    }

    private void DFS(HashSet<String> set, int idx, String[] userId, String[] bannedId) {
        // bannedId 전부를 다 탐색했다면 종료
        if (idx == bannedId.length) {
            result.add(set);
            return;
        }

        for (int i = 0; i < userId.length; i++) {
            // 이미 포함되어있는 아이디라면 continue (아이디가 중복해서 제재 아이디 목록에 들어가지 않음)
            if (set.contains(userId[i])) continue;

            // 불량 사용자라면 HashSet에 추가하고 다음 bannedId 탐색하기
            if (isBadUser(userId[i], bannedId[idx])) {
                set.add(userId[i]);
                DFS(new HashSet<>(set), idx + 1, userId, bannedId);
                set.remove(userId[i]);
            }
        }
    }

    private boolean isBadUser(String userId, String bannedId) {
        // 불량 사용자 아이디 길이와 응모자 아이디 길이가 같지 않다면, 불량 사용자가 아님
        if (userId.length() != bannedId.length()) return false;

        for (int i = 0; i < userId.length(); i++) {
            // *가 아닌 부분 중 다른 부분이 있다면, 불량 사용자가 아님
            if (bannedId.charAt(i) != '*' &&
                    userId.charAt(i) != bannedId.charAt(i)) return false;
        }

        // 나머지 경우는 모두 불량 사용자
        return true;
    }
}
