class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int N = sequence.length;
        int sum = 0;
        int left = 0;
        int min_length = Integer.MAX_VALUE;
        
        for(int right = 0; right< N; right++){
            sum += sequence[right];
            
            while(sum > k){
                sum -= sequence[left++];
            }
            if(sum == k && right-left < min_length){
                answer = new int[]{left, right};
                min_length = right-left;
            }
        }
        return answer;
    }
}