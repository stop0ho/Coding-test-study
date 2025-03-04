package Jin.week7.day31;

public class Q110옮기기 {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            String str = s[i];

            StringBuilder ans = new StringBuilder();
            StringBuilder key = new StringBuilder();

            for (int j = 0; j < str.length(); j++) {
                Character c = str.charAt(j);
                int length = ans.length();

                // 들어온 연속된 글자가 110인 경우, key에 저장하고 ans 문자열에서는 삭제
                // 이를 확인하기 위해, 해당 위치에 글자가 0이면서 총 길이가 2 이상인 경우 110인지 확인
                if (c == '0' && length >= 2 && ans.charAt(length - 2) == '1' && ans.charAt(length - 1) == '1') {
                    key.append("110");
                    ans.delete(length - 2, length);
                } else {
                    ans.append(c);
                }
            }

            // 110이 존재하는 경우
            if (key.length() > 0) {
                int idx = ans.lastIndexOf("0");
                ans.insert(idx + 1, key);
            }

            answer[i] = ans.toString();
        }

        return answer;
    }
}
