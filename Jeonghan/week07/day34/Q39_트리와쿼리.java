package week07.day34;

import java.io.*;
import java.util.*;

public class Q39_트리와쿼리 {

    /**
     * https://www.acmicpc.net/problem/15681
     */

    private static final Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int q = Integer.parseInt(input[2]);
        dp = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            map.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            map.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        solution(r, 0);

        for (int i = 0; i < q; i++) {
            int index = Integer.parseInt(br.readLine());
            sb.append(dp[index]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int solution(int index, int parent) {
        int result = 0;
        for (int next: map.get(index)) {
            if (next == parent) {
                continue;
            }
            result += solution(next, index);
        }
        return dp[index] = result + 1;
    }
}
