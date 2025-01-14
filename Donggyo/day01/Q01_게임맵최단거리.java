import java.util.*;

class Solution {
    static int R,C;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;
    
    public int solution(int[][] maps) {
        R = maps.length;
        C = maps[0].length;
        visited = new boolean[R][C];
        
        int answer = bfs(maps);
        return answer;
    }
    
    static int bfs(int[][] maps){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0,1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int w = cur[2];
            
            if(r==R-1&&c==C-1) return w;
            
            for(int d=0;d<4;d++){
                int idr = r+dr[d];
                int idc = c+dc[d];
                if(idr<0||idc<0||idr>=R||idc>=C) continue;
                if(visited[idr][idc]) continue;
                if(maps[idr][idc]==0) continue;
                queue.add(new int[]{idr,idc,w+1});
                visited[idr][idc]=true;
            }
        }
        return -1;
    }
}