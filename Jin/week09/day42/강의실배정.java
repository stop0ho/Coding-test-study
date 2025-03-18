package Jin.week09.day42;

import java.util.*;
import java.io.*;

public class 강의실배정 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> lectures = new PriorityQueue<>(
                Comparator.comparingInt((int[] p) -> p[0])
                        .thenComparingInt((int[] p) -> p[1]));

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);

            lectures.add(new int[]{ start, end });
        }

        PriorityQueue<Integer> answer = new PriorityQueue<>();

        while (!lectures.isEmpty()) {
            int[] lecture = lectures.poll();
            int start = lecture[0];
            int end = lecture[1];

            if (!answer.isEmpty() && answer.peek() <= start) {
                answer.poll();
            }

            answer.add(end);
        }

        System.out.println(answer.size());
    }
}