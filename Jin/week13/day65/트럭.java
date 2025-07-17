package Jin.week13.day65;

import java.io.*;
import java.util.*;

public class 트럭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. queue 두 개 생성
        Queue<Integer> truck = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();

        // 2. input 입력받기
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        // 3. 모든 트럭이 다리를 건널 때까지 반복
        int time = 0; // 시간
        int totalWeight = 0; // 다리 위 트럭 무게의 합
        while (!bridge.isEmpty()) {
            time++;
            totalWeight -= bridge.poll();

            if (!truck.isEmpty()) {
                if (totalWeight + truck.peek() <= L) {
                    int nowWeight = truck.poll();
                    bridge.add(nowWeight);
                    totalWeight += nowWeight;
                } else {
                    bridge.add(0);
                }
            }
        }

        bw.write(time + "");

        bw.flush();
        bw.close();
        br.close();
    }
}