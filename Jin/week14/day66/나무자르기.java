package Jin.week14.day66;

import java.io.*;
import java.util.*;

public class 나무자르기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] tree = new long[N];
        long maxHeight = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Long.parseLong(st.nextToken());
            maxHeight = Math.max(maxHeight, tree[i]);
        }

        // 2. 이분탐색 진행
        long start = 0, end = maxHeight;
        long H = 0;
        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (tree[i] - mid <= 0) continue;
                sum += (tree[i] - mid);
            }

            if (sum >= M) {
                H = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        bw.write(H + "");

        bw.flush();
        bw.close();
        br.close();
    }
}