package Jin.week07.day33;

public class 없는숫자더하기 {
    public int solution(int[] numbers) {
        int answer = 0;

        boolean[] isExists = new boolean[10];
        for (int num : numbers) {
            if (isExists[num]) continue;
            isExists[num] = true;
        }

        for (int i = 0; i < 10; i++) {
            if (isExists[i]) continue;
            answer += i;
        }

        return answer;
    }
}
