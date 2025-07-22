package Jin.week14.day67;

import java.io.*;
import java.util.*;

public class 용액 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int N = Integer.parseInt(br.readLine());

        int[] liquid = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 이분탐색
        int[] result = new int[2];
        int value = Integer.MAX_VALUE;
        boolean isZero = false;
        for (int i = 0; i < N; i++) {
            int start = i + 1, end = N - 1;

            int sum = 0;
            while (start <= end) {
                int mid = (start + end) / 2;
                sum = liquid[i] + liquid[mid];

                // 혼합 용액의 특성값이 0이면 더 이상 탐색할 필요가 없음
                if (sum == 0) {
                    result = new int[]{ liquid[i], liquid[mid] };
                    isZero = true;
                    break;
                }

                if (sum > 0) {
                    // 
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

                // 현재 혼합 용액의 특성값이 더 0에 가까운 경우, 정답 업데이트
                if (value > Math.abs(sum)) {
                    value = Math.abs(sum);
                    result = new int[]{ liquid[i], liquid[mid] };
                }
            }

            // 혼합 용액의 특성값이 0인 경우 바로 종료
            if (isZero) break;
        }

        bw.write(result[0] + " " + result[1]);
        bw.flush();
        bw.close();
        br.close();
    }
}