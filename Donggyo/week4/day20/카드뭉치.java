import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        
        for(String card1:cards1){
            queue1.add(card1);
        }
        for(String card2:cards2){
            queue2.add(card2);
        }
        
        for(String word:goal){
            if(!word.equals(queue1.peek()) && !word.equals(queue2.peek())){
                answer ="No";
                break;
            }
            if(word.equals(queue1.peek())){
                queue1.poll();
            }
            if(word.equals(queue2.peek())){
                queue2.poll();
            }
        }
        return answer;
    }
}