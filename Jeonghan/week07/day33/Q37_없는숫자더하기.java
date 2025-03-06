package week07.day33;

import java.util.*;

public class Q37_없는숫자더하기 {
    public int solution(int[] numbers) {
        return 45 - Arrays.stream(numbers).reduce(0, Integer::sum);
    }
}
