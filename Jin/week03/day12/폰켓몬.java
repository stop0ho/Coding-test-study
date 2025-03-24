package Jin.week03.day12;

import java.util.*;
import java.util.stream.Collectors;

public class 폰켓몬 {
    public int solution(int[] nums) {
        Set<Integer> set = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());


        return Math.min(set.size(), (nums.length / 2));
    }
}
