package week04.day20;

public class Q23_카드뭉치 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        for (int i = 0, j = 0, k = 0; k < goal.length; k++) {
            if (i < cards1.length && goal[k].equals(cards1[i])) {
                i++;
            } else if (j < cards2.length && goal[k].equals(cards2[j])) {
                j++;
            } else {
                return "No";
            }
        }
        return "Yes";
    }
}
