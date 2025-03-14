class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String kJinsu = Integer.toString(n, k);
        // System.out.println(kJinsu);
        
        String[] nums = kJinsu.split("0");
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i]+" ");
            if(nums[i].equals("")) continue;
            if(nums[i].equals("1")) continue;
            if(nums[i].equals("2")) answer++;
            
            else{
                for(int div = 2; div <= Math.ceil(Math.sqrt(Long.parseLong(nums[i])))+1; div++){
                    if((Long.parseLong(nums[i]))%div==0) break;
                    else if(div == Math.ceil(Math.sqrt(Long.parseLong(nums[i])))){
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
}