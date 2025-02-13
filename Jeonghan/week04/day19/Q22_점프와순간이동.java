package week04.day19;

public class Q22_점프와순간이동 {

    public int solution(int n) {
        int answer = 0;
        while (n > 0) {
            if (n % 2 != 0) {
                answer++;
            }
            n /= 2;
        }
        return answer;
    }
}
