package Jin.week14.day66;

import java.io.*;
import java.util.*;

public class 어두운굴다리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력받기
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] pos = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 이분탐색
        int start = 0, end = N;
        int answer = 0;
        while (start <= end) {
            int H = (start + end) / 2;

            if (isPossibleHeight(pos, H, N)) {
                // 2-1. 모든 길을 밝힌다면 높이를 줄여 최솟값 탐색
                answer = H; // 현재값이 최솟값일 수 있으니 가능할 때마다 값 업데이트
                end = H - 1;
            } else {
                // 2-2. 모든 길이 밝혀지지 않는다면 높이를 증가시켜 재탐색
                start = H + 1;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isPossibleHeight(int[] pos, int H, int N) {
        // 0번 길을 밝힐 수 없으면 불가능한 높이
        if (pos[0] - H > 0) return false;

        // N번 길을 밝힐 수 없으면 불가능한 높이
        if (pos[pos.length - 1] + H < N) return false;

        for (int i = 0; i < pos.length - 2; i++) {
            // 가로등 사이 모든 길이 밝혀지지 않으면 불가능한 높이
            if (pos[i] + H < pos[i + 1] - H) return false;
        }

        // 모든 길이 밝혀지면 true 반환
        return true;
    }
}