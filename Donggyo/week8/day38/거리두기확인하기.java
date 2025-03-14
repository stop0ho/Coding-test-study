import java.util.*;

class Solution {
    
    static char[][] map;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;
    static boolean invalid = false;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int tc=0;tc<5;tc++){
            System.out.println("tc: "+tc);
            map = new char[5][5];
            invalid = false;
            for(int r=0;r<5;r++){
                String str = places[tc][r];
                for(int c=0;c<5;c++){
                    map[r][c] = str.charAt(c);
                }
            }
            for(int r=0;r<5;r++){
                for(int c=0;c<5;c++){
                    if(map[r][c] == 'P'){
                        visited = new boolean[5][5];
                        bfs(r,c);
                    }
                }
            }
            if(invalid) answer[tc] = 0;
            else answer[tc] = 1;
        }
        return answer;
    }
    
    static void bfs(int nowR, int nowC){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{nowR, nowC, 0});
        visited[nowR][nowC] = true;
        
        while(!queue.isEmpty()){
            int[] info = queue.poll();
            
            int r = info[0];
            int c = info[1];
            int cnt = info[2];
            
            for(int d=0;d<4;d++){
                int idr = r+dr[d];
                int idc = c+dc[d];
                if(idr<0||idc<0||idr>=5||idc>=5) continue;
                if(visited[idr][idc]) continue;
                if(map[idr][idc]=='X') continue;
                if(map[idr][idc]=='P'&&cnt<=1) {
                    System.out.println(idr+" "+idc);
                    invalid = true;
                }
                else{
                    visited[idr][idc] = true;
                    queue.add(new int[]{idr,idc,cnt+1});
                }
            }
        }
    }    
}