package week07.day34;

import java.io.*;
import java.util.*;

public class Q40_사회망서비스SNS {

    /**
     * https://www.acmicpc.net/problem/2533
     */

    private static int[][] dp;
    private static final Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            map.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            map.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        sb.append(Math.min(search(1, 1, 1), search(1, 0, 1)));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int search(int index, int flag, int parent) {
        if (dp[index][flag] != Integer.MAX_VALUE) {
            return dp[index][flag];
        }

        int result = flag;
        for (int next : map.get(index)) {
            if (next == parent) {
                continue;
            }
            if (flag == 1) {
                result += Math.min(search(next, 0, index), search(next, 1, index));
            } else {
                result += search(next, 1, index);
            }
        }
        return dp[index][flag] = result;
    }
}
