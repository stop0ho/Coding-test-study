import java.util.*;

class Solution {
    static HashMap<String, Integer> map;
    static int[] courseMax;
    static int orderLen;
    static ArrayList<String> ans = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        String[] answer;

        courseMax = new int[course.length];
        map = new HashMap<>();

        for(int i = 0; i<orders.length;i++){
            char[] order = orders[i].toCharArray();
            Arrays.sort(order);
            orderLen = order.length;

            for(int j = 0; j<course.length;j++){
                comb(order,0,0,course[j], "", j);
            }
        }
        selectMenu(course);
        
        answer = new String[ans.size()];
        for(int i = 0; i<ans.size();i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    static void comb(char[] order, int idx, int cnt, int N, String s, int j){
        if(cnt == N){
            map.put(s, map.containsKey(s)?map.get(s)+1:1);
            courseMax[j] = Math.max(courseMax[j], map.get(s));
        }
        else{
            for(int i = idx; i<orderLen;i++){
                comb(order, i+1, cnt+1, N, s+order[i], j);
            }
        }
    }
    
    static void selectMenu(int[] course){
        for(String s: map.keySet()){
            for(int k = 0; k<course.length;k++){
                if(courseMax[k]>=2 && course[k]==s.length() && courseMax[k]==map.get(s)){
                    ans.add(s);
                }
            }
        }
        Collections.sort(ans);
    }
}
