import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] counts = new int[10000001];
        
        for(int t:tangerine){
            counts[t]++;
        }
        Arrays.sort(counts);
        
        for(int i = counts.length-1; i >= 0; i--){
            k-=counts[i];
            answer++;
            if(k<=0) break;
        }
        return answer;
    }
}