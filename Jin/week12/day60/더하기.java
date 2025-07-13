package Jin.week12.day60;

import java.io.*;
import java.util.*;

public class 더하기 {
    static int MAX_VALUE = 11;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. memo 배열에 가짓수 저장
        int[] memo = new int[MAX_VALUE];
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;
        for (int i = 4; i < MAX_VALUE; i++) {
            memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
        }

        // 2. 테스트 케이스 입력받기
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            // 3. memo 배열에 저장된 값 출력
            bw.write(memo[n] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}