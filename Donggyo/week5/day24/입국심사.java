class Solution {
    
    static int[] times;
    static int n;
    
    public long solution(int n, int[] times) {
        this.times = times;
        this.n = n;
        
        long answer = 0;
        long low = times[0];
        long high = 1_000_000_000L * 100_000L;
        long mid = -1;
        
        while(low+1<high){
            mid = (low+high)/2;
            
            if(check(mid)){
                high = mid;
            } 
            else {
                low = mid;
            }
        }
        answer = low + 1;
        
        return answer;
    }
    
    static boolean check(long totalTime){
        long count = 0;
        
        for(long time:times){
            count += totalTime/time;
        }
        
        if(count < n){
            return false;
        }
        return true;
    }
}