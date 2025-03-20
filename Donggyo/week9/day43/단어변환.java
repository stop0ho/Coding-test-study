
import java.util.*;

class Solution {
    
    Set<String> set = new HashSet<>();
    static String target;
    static String[] words;
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        this.target = target;
        this.words = words;
        visited = new boolean[words.length];
        
        for(String word : words){
            set.add(word);
        }
        if(!set.contains(target)) return 0;
        
        bt(begin, 0);
        
        return answer;
    }
    
    static void bt(String now, int count){
        if(now.equals(target)){
            answer = Math.min(answer, count);
            return;
        }
        
        for(int i = 0; i<words.length; i++){
            if(visited[i]) continue;
            if(validConvert(now, words[i])){
                visited[i] = true;
                bt(words[i], count+1);
                visited[i] = false;
            }
        }
    }
    
    static boolean validConvert(String now, String target){
        int count = 0;
        
        for(int i = 0; i<now.length(); i++){
            if(now.charAt(i)!=target.charAt(i)){
                count++;
            }
            if(count>1) return false;
        }
        return true;
    }
}