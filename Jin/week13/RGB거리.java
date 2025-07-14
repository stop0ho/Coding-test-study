package Jin.week13;

import java.io.*;
import java.util.*;

public class RGB거리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력 받기
        int N = Integer.parseInt(br.readLine());

        int[][] input = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. dp 배열 생성
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = input[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = input[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = input[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        // 3. N번째 행에서 최솟값 출력
        int result = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }
}