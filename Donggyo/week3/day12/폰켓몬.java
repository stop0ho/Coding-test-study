import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        Set<Integer> set = new HashSet();
        for(int num:nums){
            set.add(num);
        }
        int typeCount = set.size();
        
        int N = nums.length;
        if(typeCount>N/2) answer = N/2;
        else answer = typeCount;
        return answer;
    }
}