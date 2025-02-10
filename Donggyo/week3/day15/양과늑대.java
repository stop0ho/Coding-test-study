import java.util.*;

class Solution {
    static int V;
    static int[] info;
    static boolean[] visited;
    static int[] left;
    static int[] right;
    static int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        V = info.length;
        this.info = info;
        
        left = new int[V];
        right = new int[V];
        visited = new boolean[1 << V];
        
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        
        for(int[] edge:edges){
            int parent = edge[0];
            int child = edge[1];
            if(left[parent]==-1) left[parent] = child;
            else right[parent] = child;
        }
        answer = bfs(0);
        return answer;
    }
    
    static int bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1<<start); // 1을 start만큼 왼쪽으로 밀어준다. ex) 1<<0 :1 , 1<<1 :10, 1<<2 :100
        
        int max = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll(); //현재까지 방문한 노드들 (단순 현재 위치가 아님!)
            if(visited[cur]) continue;
            visited[cur] = true;
            
            int score = 0;
            int sheep = 0;
            for(int i = 0; i<V;i++){
                if((1 << i & cur)==0) continue; // i번 노드를 방문하지 않았다면 넘기기
                if(info[i]==0) {
                    score++;
                    sheep++;
                }
                else score--;
            }
            if(score<=0) continue;
            
            max = Math.max(max, sheep);
            
            for(int i = 0; i<V; i++){
                if((1 << i & cur)==0) continue; // 현재 방문한 노드 아니면 패스
                if(left[i]!=-1) queue.add(1<<left[i] | cur); // 현재까지 방문한 노드에 자식 노드를 추가
                if(right[i]!=-1) queue.add(1<<right[i] | cur);
            }
        }
        return max;
    }
}