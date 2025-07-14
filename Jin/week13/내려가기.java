package Jin.week13;

import java.io.*;
import java.util.*;

public class 내려가기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[3];

        // 2. dp 배열 4개 생성
        int[] minDP = new int[3];
        int[] maxDP = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }

            int min0 = minDP[0];
            int min1 = minDP[1];
            int min2 = minDP[2];
            minDP[0] = input[0] + Math.min(min0, min1);
            minDP[1] = input[1] + Math.min(Math.min(min0, min1), min2);
            minDP[2] = input[2] + Math.min(min1, min2);

            int max0 = maxDP[0];
            int max1 = maxDP[1];
            int max2 = maxDP[2];
            maxDP[0] = input[0] + Math.max(max0, max1);
            maxDP[1] = input[1] + Math.max(Math.max(max0, max1), max2);
            maxDP[2] = input[2] + Math.max(max1, max2);
        }

        int min = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);
        int max = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);

        bw.write(max + " " + min);

        bw.flush();
        bw.close();
        br.close();
    }
}