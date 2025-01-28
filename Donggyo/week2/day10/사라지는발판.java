class Solution {
    
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int R, C;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        R = board.length;
        C = board[0].length;
        
        answer = dfs(board, aloc, bloc);
        
        return answer;
    }
    
    static int dfs(int[][] board, int[] myLoc, int[] otherLoc){
        int r = myLoc[0];
        int c = myLoc[1];
        if(board[r][c] == 0) return 0;
        
        int result = 0;
        for(int d = 0; d<4; d++){
            int idr = r+dr[d];
            int idc = c+dc[d];
            
            if(idr<0||idc<0||idr>=R||idc>=C) continue;
            if(board[idr][idc]==0) continue;
            board[r][c] = 0;
            
            int count = dfs(board, otherLoc, new int[]{idr,idc})+1;
            board[r][c] = 1;
            
            if(count % 2 == 1 && result % 2 == 0) result = count;
            else if(count % 2 == 0 && result % 2 == 0 ) result = Math.max(result, count);
            else if(count % 2 == 1 && result % 2 == 1 ) result = Math.min(result, count);
        }
        return result;
    }
}