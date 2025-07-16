package Jin.week13.day61;

import java.io.*;
import java.util.*;

public class 자원캐기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 2. 2차원 배열 생성
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3. 각 위치별 최댓값 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // 4. (N, M) 위치에 저장된 값 출력
        bw.write(dp[N][M] + "");

        bw.flush();
        bw.close();
        br.close();
    }
}
