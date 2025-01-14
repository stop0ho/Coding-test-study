import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = bfs(n, computers);
        return answer;
    }
    
    static int bfs(int n, int[][] computers){
        //초기화
        int count = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        //r기준으로 탐색 시작
        for(int r = 0; r<n;r++){
            if(visited[r]) continue;
            visited[r] = true;
            count++; //r이 방문되지 않은 점이라면, 새로운 연결지점이기 때문에 Count++
            
            for(int c = 0; c<n;c++){
                if(r==c) continue;
                if(computers[r][c]==0) continue;
                queue.add(c); //r 기준으로 c가 1이라면 add
            }
            
            while(!queue.isEmpty()){ //c를 이어나가며 탐색
                int cur = queue.poll();
                visited[cur] = true;

                for(int next = 0; next<n;next++){
                    if(cur==next) continue;
                    if(computers[cur][next]==0) continue;
                    if(visited[next]) continue;
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        return count;
    }
}