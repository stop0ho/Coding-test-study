class Solution {
    
    int solution(int[][] land) {
        int answer = 0;
        int N = land.length;
        int[][] dp = new int[N][4];
        
        for(int c=0;c<4;c++){
            dp[0][c] = land[0][c];
        }
        int max = -1;
        for(int r = 1; r<N; r++){
            for(int c = 0; c<4; c++){
                for(int k = 0; k<4; k++){
                    if(c-k==c) continue;
                    if(c-k>=0) dp[r][c] = Math.max(dp[r-1][c-k]+land[r][c], dp[r][c]);
                    if(c+k<4) dp[r][c] = Math.max(dp[r][c], dp[r-1][c+k]+land[r][c]);
                    max = Math.max(dp[r][c], max);
                }
            }
        }
        answer = max;
        return answer;
    }
}