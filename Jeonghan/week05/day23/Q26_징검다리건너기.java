package week05.day23;

import java.util.*;

public class Q26_징검다리건너기 {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        MaxQueue<Integer> mq = new MaxQueue<>();

        for (int stone : stones) {
            mq.offer(stone);
            if (mq.size() == k) {
                answer = Math.min(answer, mq.getMax());
                mq.pop();
            }
        }
        return answer;
    }

    private static class MaxQueue<T extends Comparable<T>> {
        private final Queue<T> q;
        private final Deque<T> md;

        private MaxQueue() {
            q = new LinkedList<>();
            md = new LinkedList<>();
        }

        private T offer(T o) {
            q.offer(o);
            while (!md.isEmpty() && md.peekLast().compareTo(o) < 0) {
                md.pollLast();
            }
            md.offer(o);
            return o;
        }

        private T pop() {
            if (q.isEmpty()) {
                return null;
            }
            T removed = q.poll();
            if (removed.equals(md.peek())) {
                md.poll();
            }
            return removed;
        }

        private T getMax() {
            return md.isEmpty() ? null : md.peek();
        }

        private int size() {
            return q.size();
        }
    }
}
