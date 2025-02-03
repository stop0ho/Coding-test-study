package week03.day11;

import java.util.*;

public class Q14_실패율 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stageCnt = new int[N + 2];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int stage : stages) {
            stageCnt[stage]++;
        }

        int total = stageCnt[N + 1];
        for (int i = N; i > 0; i--) {
            total += stageCnt[i];
            pq.offer(new Node(i, total != 0 ? (double) stageCnt[i] / total : 0.0));
        }

        int cnt = 0;
        while (!pq.isEmpty()) {
            answer[cnt++] = pq.poll().index;
        }
        return answer;
    }

    private static class Node implements Comparable<Node> {
        private int index;
        private double rate;

        private Node(int index, double rate) {
            this.index = index;
            this.rate = rate;
        }

        @Override
        public int compareTo(Node o) {
            if (this.rate != o.rate) {
                return Double.compare(o.rate, this.rate);
            }
            return Integer.compare(this.index, o.index);
        }
    }
}
