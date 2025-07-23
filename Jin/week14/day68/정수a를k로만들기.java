package Jin.week14.day68;

import java.io.*;
import java.util.*;

public class 정수a를k로만들기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력받기
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 2. 배열 생성
        int[] dp = new int[K + 1];

        // 3. dp 연산
        for (int i = A; i <= K; i++) {
            if (i + 1 <= K) {
                dp[i + 1] = (dp[i + 1] == 0) ? dp[i] + 1 : Math.min(dp[i + 1], dp[i] + 1);
            }
            if (i * 2 <= K) {
                dp[i * 2] = (dp[i * 2] == 0) ? dp[i] + 1 : Math.min(dp[i * 2], dp[i] + 1);
            }
        }

        // 4. dp[K] 출력
        bw.write(dp[K] + "");

        bw.flush();
        bw.close();
        br.close();
    }
}