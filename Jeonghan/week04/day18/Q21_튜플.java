package week04.day18;

import java.util.*;
import java.util.stream.Collectors;

public class Q21_튜플 {

    public int[] solution(String s) {
        return Arrays.stream(s.substring(2, s.length() - 2).split("\\},\\{"))
            .flatMap(sub -> Arrays.stream(sub.split(",")).map(Integer::parseInt))
            .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
            .entrySet().stream()
            .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }
}
