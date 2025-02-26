import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        HashMap<String, Integer> storagePeriods = new HashMap<>();

        for (String term : terms) {
            String[] str = term.split(" ");
            storagePeriods.put(str[0], Integer.parseInt(str[1]));
        }

        int[] todayDate = parseDate(today);
        int todayValue = todayDate[0] * 10000 + todayDate[1] * 100 + todayDate[2];

        for (int i = 0; i < privacies.length; i++) {
            String[] str = privacies[i].split(" ");
            int[] startDate = parseDate(str[0]);
            int expiryDate = calculateExpiryDate(startDate, storagePeriods.get(str[1]));

            if (isExpired(expiryDate, todayValue)) {
                result.add(i + 1);
            }
        }

        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

    private int[] parseDate(String date) {
        String[] yymmdd = date.split("\\.");
        return new int[]{Integer.parseInt(yymmdd[0]), Integer.parseInt(yymmdd[1]), Integer.parseInt(yymmdd[2])};
    }

    private int calculateExpiryDate(int[] date, int storageMonth) {
        int year = date[0];
        int month = date[1] + storageMonth;
        int day = date[2];

        year += month / 12;
        month %= 12;
        if (month == 0) {
            month = 12;
            year -= 1;
        }

        return year * 10000 + month * 100 + day;
    }

    private boolean isExpired(int expiryDate, int today) {
        return expiryDate <= today;
    }
}