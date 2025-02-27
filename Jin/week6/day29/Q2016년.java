package Jin.week6.day29;

public class Q2016년 {
    public String solution(int a, int b) {
        String[] days = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        int[] daysInMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int totalDays = 0;

        for (int i = 1; i < a; i++) {
            totalDays += daysInMonth[i];
        }
        totalDays += (b - 1);

        int day = totalDays % 7;
        return days[day];
    }
}
