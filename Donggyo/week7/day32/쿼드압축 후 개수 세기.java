class Solution {
    static int N;
    static int zero = 0;
    static int one = 0;
    static int[][] map;
    public int[] solution(int[][] arr) {
        this.map = arr;
        int[] answer = {};
        N = arr.length;
        
        recur(N, 0, 0);
        answer = new int[]{zero, one};
        return answer;
    }
    
    static void recur(int size, int r, int c){
        if(size == 1){
            if(map[r][c]==1) one++;
            else zero++;
            return;
        }
        if(canPress(r, c, size)){
            if(map[r][c]==1) one++;
            else zero++;
            return;
        }
        recur(size/2, r, c);
        recur(size/2, r+size/2, c);
        recur(size/2, r, c+size/2);
        recur(size/2, r+size/2, c+size/2);        
    }
    
    static boolean canPress(int st_r, int st_c, int size){
        int std = map[st_r][st_c];
        
        for(int r = st_r; r < st_r+size; r++){
            for(int c = st_c; c < st_c+size; c++){
                if(std!=map[r][c]){
                    return false;
                }
            }
        }
        return true;
    } 
}