package Jin.week14.day69;

import java.io.*;
import java.util.*;

public class 이상한술집 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] volume = new int[N];
        int MAX_VALUE = 0;
        for (int i = 0; i < N; i++) {
            volume[i] = Integer.parseInt(br.readLine());
            MAX_VALUE = Math.max(MAX_VALUE, volume[i]);
        }

        // 2. 이분탐색 시작
        long start = 0, end = MAX_VALUE;
        long result = 0;
        while (start <= end) {
            long mid = (start + end) / 2;

            long count = 0;
            for (int i = 0; i < N; i++) {
                if (mid > 0) {
                    count += (int) (volume[i] / mid);
                } else {
                    count += K;
                }

                if (count >= K) break;
            }

            if (count >= K) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }
}