package week08.day37;

import java.util.*;

public class Q44_k진수에서소수개수구하기 {
    private static final int MAX = 1_000_000;
    private static final Set<Long> set = new TreeSet<>();

    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] check = new boolean[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            if (check[i]) {
                continue;
            }
            for (int j = 2; i * j <= MAX; j++) {
                check[i * j] = true;
            }
            set.add((long) i);
        }

        for (int i = 12; i >= 0; i--) {
            int temp = (int) Math.pow(k, i);
            sb.append(n / temp);
            n %= temp;
        }

        return (int) Arrays.stream(sb.toString().split("0"))
            .filter(s -> !s.isBlank() && !s.equals("1") && validate(s))
            .count();
    }

    private boolean validate(String s) {
        long index = Long.parseLong(s);
        if (!set.contains(index)) {
            for (long p : set) {
                if (p > index) {
                    break;
                }
                if (index % p == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
