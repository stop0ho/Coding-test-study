import java.util.*;

class Solution {
    
    static HashMap<Integer, int[]> nodes = new HashMap<>();
    
    public int[] solution(int[][] edges) {
        
        for(int[] edge : edges){
            int st = edge[0];
            int ed = edge[1];
            
            if(!nodes.containsKey(st)){
                nodes.put(st, new int[]{0,0});
            }
            if(!nodes.containsKey(ed)){
                nodes.put(ed, new int[]{0,0});
            }
            nodes.get(st)[0]++;
            nodes.get(ed)[1]++;
        }
        
        int mainNode = 0;
        int eight = 0;
        int stick = 0;
        int doughnut = 0;
        
        for(int key : nodes.keySet()){
            int[] counts = nodes.get(key);
            int out = counts[0];
            int in = counts[1];
            if(in == 0 && out>=2){
                mainNode = key;
            }
            if(in>=2 && out>=2){
                eight++;
            }
            if(in>0 && out ==0){
                stick++;
            }
        }
        
        doughnut = nodes.get(mainNode)[0]-eight-stick;
        
        int[] answer = new int[]{mainNode, doughnut, stick, eight}; 
        
        return answer;
    }
}