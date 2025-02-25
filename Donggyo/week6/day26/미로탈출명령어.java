import java.util.*;

class Solution {  
    static int N,M,X,Y,R,C,K;
    static int[] dr = {1,0,0,-1}; // dlru
    static int[] dc = {0,-1,1,0};
    static String answer = "z";
    static String[] directions = {"d", "l","r","u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.N=n;
        this.M=m;
        this.X=x;
        this.Y=y;
        this.R=r;
        this.C=c;
        this.K=k;
        
        if (getDistance(y,x) > K || (K - getDistance(y,x)) % 2 == 1){
            return "impossible";
        }
        
        dfs(X,Y,0,"");
        return answer.equals("z")? "impossible": answer;
    }
    
    static void dfs(int r, int c, int count, String path){
        if(count>K) return;
        if(path.compareTo(answer)>0) return;
        if(count+getDistance(r,c)>K) return;
        if(count == K && r==R && c==C ){
            answer = path;
            return;
        }
        
        for(int d=0;d<4;d++){
            int idr = r+dr[d];
            int idc = c+dc[d];
            
            if(idr<1||idc<1||idr>=N+1||idc>=M+1) continue;
            String newPath = path+directions[d];
            dfs(idr, idc, count+1, newPath);
        }
    }
    
    static int getDistance(int r, int c){
        return Math.abs(R-r)+Math.abs(C-c);
    }
}