package week09.day42;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Q47_강의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> result = new PriorityQueue<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            pq.offer(new int[] { Integer.parseInt(input[0]), Integer.parseInt(input[1]) });
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (!result.isEmpty() && result.peek() <= cur[0]) {
                result.poll();
            }
            result.offer(cur[1]);
        }

        sb.append(result.size());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
