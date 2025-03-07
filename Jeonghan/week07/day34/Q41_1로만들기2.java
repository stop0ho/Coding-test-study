package week07.day34;

import java.io.*;
import java.util.*;

public class Q41_1로만들기2 {

    /**
     * https://www.acmicpc.net/problem/12852
     */

    private static int[] dp, path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        path = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        sb.append(solution(n)).append("\n");
        int i = n;
        while (i != 0) {
            sb.append(i).append(" ");
            i = path[i];
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int solution(int index) {
        if (dp[index] != Integer.MAX_VALUE) {
            return dp[index];
        }
        if (index == 1) {
            return 0;
        }
        if (index % 3 == 0) {
            update(index, index / 3, solution(index / 3));
        }
        if (index % 2 == 0) {
            update(index, index / 2, solution(index / 2));
        }
        update(index, index - 1, solution(index - 1));
        return dp[index];
    }

    private static void update(int cur, int next, int result) {
        int temp = result + 1;
        if (dp[cur] > temp) {
            dp[cur] = temp;
            path[cur] = next;
        }
    }
}
