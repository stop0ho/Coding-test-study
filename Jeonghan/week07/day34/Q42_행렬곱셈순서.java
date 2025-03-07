package week07.day34;

import java.io.*;
import java.util.*;

public class Q42_행렬곱셈순서 {

    /**
     * https://www.acmicpc.net/problem/11049
     */

    private static int[][] dp;
    private static int[] x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        dp = new int[n][n];
        x = new int[n];
        y = new int[n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            String[] input = br.readLine().split(" ");
            x[i] = Integer.parseInt(input[0]);
            y[i] = Integer.parseInt(input[1]);
        }

        sb.append(search(0, n - 1));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int search(int s, int e) {
        if (dp[s][e] != Integer.MAX_VALUE) {
            return dp[s][e];
        }
        if (s + 1 == e) {
            return dp[s][e] = x[s] * y[s] * y[e];
        }
        if (s == e) {
            return dp[s][e] = 0;
        }
        for (int i = s; i < e; i++) {
            dp[s][e] = Math.min(dp[s][e], search(s, i) + search(i + 1, e) + x[s] * y[i] * y[e]);
        }
        return dp[s][e];
    }
}
