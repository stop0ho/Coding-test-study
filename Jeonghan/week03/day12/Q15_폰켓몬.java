package week03.day12;

import java.util.*;

public class Q15_폰켓몬 {
    public int solution(int[] nums) {
        int n = nums.length;
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        return Math.min(s.size(), n / 2);
    }
}
