import java.util.*;

class Solution {
    static HashMap<Long, Long> map = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for(int i = 0; i<room_number.length; i++){
            answer[i] = findParent(room_number[i]);
        }
        return answer;
    }
    
    static long findParent(long requestNumber){
        if(!map.containsKey(requestNumber)){
            map.put(requestNumber, requestNumber+1);
            return requestNumber;
        }
        
        long parent = findParent(map.get(requestNumber));
        map.put(requestNumber, parent);
        return parent;
    }
}