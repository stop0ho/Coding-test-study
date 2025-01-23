package week2.day09;

public class Q10_피로도 {

    public int solution(int k, int[][] dungeons) {
        return bt(k, 0, dungeons);
    }

    private int bt(int hp, int total, int[][] dungeons) {
        int answer = total;
        for (int i = 0; i < dungeons.length; i++) {
            if (dungeons[i][0] != -1 && hp >= dungeons[i][0]) {
                int temp = dungeons[i][0];
                dungeons[i][0] = -1;
                answer = Math.max(answer, bt(hp - dungeons[i][1], total + 1, dungeons));
                dungeons[i][0] = temp;
            }
        }
        return answer;
    }
}
