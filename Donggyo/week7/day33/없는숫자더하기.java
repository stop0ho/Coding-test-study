class Solution {
    public int solution(int[] numbers) {
        int sum = 0;
        for(int number:numbers){
            sum+=number;
        }
        int answer = 45-sum;
        return answer;
    }
}