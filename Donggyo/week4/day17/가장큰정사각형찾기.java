class Solution
{
    public int solution(int [][]board)
    {
        int answer = -1;
        int max_length = 0;
        int R = board.length;
        int C = board[0].length;
        int[][] dp = new int[R][C];

        if(R<=1 && C<=1) return board[0][0]*board[0][0];
        
        for(int c=0;c<C;c++){
            dp[0][c] = board[0][c];
        }
        for(int r=0; r<R;r++){
            dp[r][0] = board[r][0];
        }
            
        for(int r = 1; r<R; r++){
            for(int c = 1; c<C; c++){
                if(board[r][c]==1){
                    dp[r][c] = Math.min(Math.min(dp[r-1][c-1], dp[r-1][c]),dp[r][c-1])+1;
                    max_length = Math.max(dp[r][c], max_length);
                }
            }
        }
        answer = max_length*max_length;
        return answer;
    }
}