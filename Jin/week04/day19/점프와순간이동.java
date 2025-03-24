package Jin.week04.day19;

public class 점프와순간이동 {
    public int solution(int n) {
        int ans = 0;

        while (n != 0) {
            // n이 짝수면 순간이동을 했다는 뜻(순간이동이 건전지가 안드니까 무조건 순간이동이 이득)
            // 홀수면 무조건 1칸 전진해야 함
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
                ans++;
            }
        }

        return ans;
    }
}
