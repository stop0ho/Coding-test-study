package week06.day28;

import java.util.*;
import java.util.stream.*;

public class Q30_개인정보수집유효기간 {
    private static Map<String, Integer> map;

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        map = Arrays.stream(terms)
            .map(t -> t.split(" "))
            .collect(Collectors.toMap(
                s -> s[0],
                s -> getExpiredDatetime(today, s[1]),
                (e, r) -> r
            ));

        for (int i = 0; i < privacies.length; i++) {
            String[] p = privacies[i].split(" ");
            int datetime = dtoi(atod(p[0]));

            if (datetime <= map.get(p[1])) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getExpiredDatetime(String today, String month) {
        int[] datetime = atod(today);
        int m = Integer.parseInt(month);
        datetime[0] -= m / 12;
        datetime[1] -= m % 12;

        if (datetime[1] < 1) {
            datetime[0] -= 1;
            datetime[1] += 12;
        }
        return dtoi(datetime);
    }

    private int[] atod(String s) {
        return Arrays.stream(s.split("\\."))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    private int dtoi(int[] datetime) {
        return datetime[0] * 10000 + datetime[1] * 100 + datetime[2];
    }
}
