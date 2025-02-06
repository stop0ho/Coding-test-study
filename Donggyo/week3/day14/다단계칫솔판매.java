import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        Map<String, String> tree = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        
        for(int i = 0; i<enroll.length; i++){
            String child = enroll[i];
            String parents = referral[i];
            tree.put(child, parents);
            indexMap.put(child, i);
        }
        
        for(int i = 0; i<seller.length; i++){
            String cur = seller[i];
            int profit = 100 * amount[i];
            
            while(!cur.equals("-")){
                int payment = profit/10;
                int myProfit = profit-payment;
                int index = indexMap.get(cur);
                answer[index] += myProfit;
                
                cur = tree.get(cur);
                profit = payment;
                
                if(profit<1) break;
            }
        }
        return answer;
    }
}