package week07.day34;

import java.io.*;

public class Q38_공통부분문자열 {

    /**
     * https://www.acmicpc.net/problem/5582
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        int[][] dp = new int[a.length + 1][b.length + 1];
        int answer = 0;

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                dp[i][j] = a[i - 1] == b[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                answer = Math.max(answer, dp[i][j]);
            }
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
