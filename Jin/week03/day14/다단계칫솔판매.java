package Jin.week03.day14;

import java.util.*;

class 다단계칫솔판매 {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        // 자식이 key, 부모가 value인 해시맵 생성
        HashMap<String, String> tree = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            tree.put(enroll[i], referral[i]);
        }

        // 판매원 이름이 key, 이익금이 value인 해시맵 생성
        HashMap<String, Integer> sellerProfit = new HashMap<>();
        for (int i = 0; i < seller.length; i++) {
            String sellerName = seller[i];
            int money = amount[i] * 100;

            // 1원 미만이거나, 루트 노드인 경우 중단(민호가 얼마 벌었는지는 몰라도 됨)
            while (money > 0 && !sellerName.equals("-")) {
                // 10%를 제한 금액을 얻으니까
                int getProfit = money - (money / 10);

                // 이미 다른데서 수금한 돈이 있으면 거기에 더하고, 없으면 초기값 0에 더하기!
                sellerProfit.put(sellerName, sellerProfit.getOrDefault(sellerName, 0) + getProfit);

                // 부모 계산으로 넘어가기 + 돈은 10% 금액
                sellerName = tree.get(sellerName);
                money /= 10;
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = sellerProfit.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}