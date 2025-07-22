package Jin.week14.day66;

import java.io.*;
import java.util.*;

public class 선분위의점 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 1-1. point 배열에 점의 좌표 저장
        int[] point = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 선분 입력마다 2번의 이분탐색 진행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            int start = 0, end = N - 1;

            // upper bound 구하기
            while (start <= end) {
                int mid = (start + end) / 2;
                if (point[mid] > p2) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            int upperBound = start;

            // lower bound 구하기
            start = 0;
            end = N - 1;
            while (start <= end)  {
                int mid = (start + end) / 2;
                if (point[mid] < p1) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            int lowerBound = start;

            bw.write((upperBound - lowerBound) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
} 