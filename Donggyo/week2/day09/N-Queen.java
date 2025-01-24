class Solution {
    static int answer = 0;
    static int[] board;
    public int solution(int n) {
        board = new int[n];    
        backTracking(n, 0);
        return answer;
    }
    
    static void backTracking(int n, int row){
        if(row == n){
            answer++;
            return;
        }
        for(int c = 0; c<n; c++){
            board[row]=c;
            if(!isValid(row)) continue;
            backTracking(n, row+1);
        }
    }
    
    static boolean isValid(int R){
        for(int r = 0; r<R;r++){
            if(board[r]==board[R]) return false;
            if(Math.abs(R-r)==Math.abs(board[R]-board[r])){
                return false;
            }
        }
        return true;
    }
}