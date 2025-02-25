class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
    
        long distance = 0;
        int delivery = 0;
        int pickup = 0;
        
        for(int i = n-1; i >= 0; i--){
            delivery += deliveries[i];
            pickup += pickups[i];
            
            while(delivery > 0 || pickup > 0){
                delivery -= cap;
                pickup -= cap;
                distance += (i+1) * 2;
            }
        }
        return distance;
    }
}