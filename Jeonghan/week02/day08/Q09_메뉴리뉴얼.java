package week02.day08;

import java.util.*;
import java.util.stream.Collectors;

public class Q09_메뉴리뉴얼 {

    private static final Map<String, Integer> map = new TreeMap<>();

    public String[] solution(String[] orders, int[] course) {
        for (String o : orders) {
            char[] naturalOrder = o.toCharArray();
            Arrays.sort(naturalOrder);
            dfs("", String.valueOf(naturalOrder), 0);
        }

        List<Integer> courseList = Arrays.stream(course)
            .boxed()
            .collect(Collectors.toList());

        return map.entrySet().stream()
            .filter(e -> courseList.contains(e.getKey().length()) && e.getValue() > 1)
            .collect(Collectors.groupingBy(e -> e.getKey().length(), Collectors.toList()))
            .values().stream()
            .flatMap(g -> {
                int max = g.stream()
                    .mapToInt(Map.Entry::getValue)
                    .max()
                    .orElse(Integer.MIN_VALUE);

                return g.stream()
                    .filter(e -> e.getValue() == max)
                    .map(Map.Entry::getKey);
            })
            .sorted(Comparator.naturalOrder())
            .toArray(String[]::new);
    }

    private void dfs(String previous, String order, int index) {
        if (index == order.length()) {
            if (previous.length() > 1) {
                map.put(previous, map.computeIfAbsent(previous, k -> 0) + 1);
            }
            return;
        }

        String key = String.valueOf(order.charAt(index));
        dfs(previous + key, order, index + 1);
        dfs(previous, order, index + 1);
    }
}
