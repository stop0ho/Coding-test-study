class Solution {
    static int[] result = {-1};
    static int diff_max = -1;
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        int[] lion = new int[11];
        
        backTracking(n, 0, info, lion);
        answer = result;
        return answer;
    }
    
    static void backTracking(int n, int count, int[] info, int[] lion){
        if(count==n){
            int apeach_score = 0;
            int lion_score = 0;
            for(int i = 0; i<11; i++){
                if(info[i] !=0 || lion[i]!=0){
                    if(info[i]>=lion[i]) apeach_score += (10-i);
                    else lion_score += (10-i);
                }
            }
            if(lion_score > apeach_score && lion_score-apeach_score>=diff_max){
                diff_max = lion_score-apeach_score;
                result = lion.clone();
            }
            return;
        }
        for(int i = 0; i<11 && info[i]>=lion[i]; i++){
            lion[i]++;
            backTracking(n, count+1, info, lion);
            lion[i]--;
        }
    }
}