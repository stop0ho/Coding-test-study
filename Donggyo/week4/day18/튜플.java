import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer;
        s = s.replace("{","");
        s = s.replace("}","");
        String[] str = s.split(",");
        
        ArrayList<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<str.length;i++){
            map.put(str[i], map.containsKey(str[i]) ? map.get(str[i])+1 : 1);
        }
        for(int cnt = str.length; cnt>=1;cnt--){
            for(String key : map.keySet()){
                if(map.get(key)==cnt) list.add(key);
            }
        }
        answer = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            answer[i] = Integer.parseInt(list.get(i));
        }
        return answer;
    }
}