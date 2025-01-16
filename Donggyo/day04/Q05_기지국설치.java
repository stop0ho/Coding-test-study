class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int point = 1;
        int coverage = 2*w+1;
        
        for(int s:stations){
            if(point < s-w) {
                int supply = calculateSupply(s-w-1, point, w, coverage);
                answer+=supply;
            }
            point = s+w+1;
        }
        if(stations[stations.length-1]+w < n){
            int supply = calculateSupply(n, stations[stations.length-1]+w+1, w, coverage);
            answer+=supply;
        }
        return answer;
    }
    
    static int calculateSupply(int next, int point, int w, int coverage){
        int distance = next-point+1;
        int supply = distance%coverage == 0 ? distance/coverage : distance/coverage+1;
        return supply;
    }
    
}