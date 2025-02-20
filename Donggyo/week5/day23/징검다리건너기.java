class Solution {
    static int k;
    static int[] stones;
    public int solution(int[] stones, int k) {
        int answer = 0;
        this.k = k;
        this.stones = stones;
        
        int high = 200000001;
        int low = -1;
        
        while(low+1<high){
            int mid = (high+low)/2;
            if(canJump(mid)){
                low = mid;
            }
            else high = mid;
        }
        answer = low+1;
        
        return answer;
    }
    
    static boolean canJump(int damage){
        int cnt = 0;
        for(int i = 0; i<stones.length; i++){
            if(stones[i]-damage<=0) cnt++;
            else cnt = 0;
            
            if(cnt == k) return false;
        }
        return true;
    }
}