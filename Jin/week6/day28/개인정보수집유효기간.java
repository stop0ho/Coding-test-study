package Jin.week6.day28;

import java.util.*;

public class 개인정보수집유효기간 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayDays = convertToDays(today);

        HashMap<String, Integer> termMap = new HashMap<>();

        for (String t : terms) {
            String[] termArr = t.split(" ");
            termMap.put(termArr[0], Integer.parseInt(termArr[1]));
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privArr = privacies[i].split(" ");
            String date = privArr[0];
            String term = privArr[1];

            int expireDays = convertToDays(date) + (termMap.get(term) * 28);

            if (todayDays >= expireDays) {
                result.add(i + 1);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private int convertToDays(String date) {
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return (year * 12 * 28) + ((month - 1) * 28) + day;
    }
}
