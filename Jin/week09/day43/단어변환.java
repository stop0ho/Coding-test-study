package Jin.week09.day43;

public class 단어변환 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        if (isPossible(words, target)) {
            answer = getAnswer(begin, target, words, new boolean[words.length], 0);
        }

        // 변환할 수 없는 경우 고려
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private int getAnswer(String begin, String target, String[] words, boolean[] visited, int depth) {
        int minValue = Integer.MAX_VALUE;

        // 변환이 완료되면 depth 반환
        if (begin.equals(target)) {
            return depth;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;

            if (canConvert(begin, words[i])) {
                visited[i] = true;
                minValue = Math.min(minValue, getAnswer(words[i], target, words, visited, depth + 1));
                visited[i] = false;
            }
        }

        return minValue;
    }

    // 예제 #2) 타겟이 words 안에 없을 수도 있음
    private boolean isPossible(String[] words, String target) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                return true;
            }
        }

        return false;
    }

    // 한 번에 한 개의 알파벳만 변환 가능
    private boolean canConvert(String origin, String convert) {
        int diff = 0;

        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) != convert.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }

        return diff == 1;
    }
}