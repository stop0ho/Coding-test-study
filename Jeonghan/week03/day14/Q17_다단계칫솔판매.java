package week03.day14;

import java.util.*;

public class Q17_다단계칫솔판매 {
    private static final Map<String, Integer> map = new HashMap<>();
    private static final Map<Integer, List<Integer>> propagations = new HashMap<>();

    private static int[] answer;
    private static int[] parents;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        answer = new int[n];
        parents = new int[n];

        for (int i = 0; i < n; i++) {
            map.put(enroll[i], i);
            propagations.put(i, new ArrayList<>());
            parents[i] = referral[i].equals("-") ? -1 :map.get(referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            int index = map.get(seller[i]);
            answer[index] += amount[i] * 90;
            lazyPropagate(index, amount[i] * 10);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int propagation : propagations.get(i)) {
                int propagationCost = propagation / 10;
                answer[i] += propagation - propagationCost;
                lazyPropagate(i, propagationCost);
            }
        }
        return answer;
    }

    /**
     * 해당 인덱스 부모에게만 이자를 전달하는 함수
     * 부모가 없는 경우 동작 없음
     *
     * @param index
     * @param cost
     */
    private void lazyPropagate(int index, int cost) {
        int parent = parents[index];
        if (parent != -1 && cost != 0) {
            propagations.get(parent).add(cost);
        }
    }
}
