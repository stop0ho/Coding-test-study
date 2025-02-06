import java.util.*;

class Solution {
    static int N;
    static int[] parents;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n+1];
        for(int i = 1; i<n+1; i++){
            parents[i] = i;
        }
        
        Arrays.sort(costs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[2]-o2[2];
            }
        });
        
        int edge = 0;
        for(int r = 0; r<costs.length; r++){
            int st = costs[r][0];
            int ed = costs[r][1];
            int w = costs[r][2];
            
            if(findset(st)==findset(ed)) continue;
            union(st, ed);
            answer += w;
            edge++;
            if(edge==n-1) break;
        }
        return answer;
    }
    
    static int findset(int x){
        if(parents[x] == x) return x;
        else return parents[x] = findset(parents[x]);
    }
    
    static void union(int a, int b){
        a = findset(a);
        b = findset(b);
        if(a!=b) parents[b] = a;
    }
}