import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class B2638_치즈_1 {
    static int N,M;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> targetCheese;
    static boolean exit_flag = false;
    public static void main(String[] args) {
        input();
        int melting_time = 0;
        while(!exit_flag) {
            targetCheese = new ArrayList<>();
            fillAir();
            selectTargetCheese();
            meltCheese();
            if(!exit_flag) melting_time++;
        }
        System.out.println(melting_time);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        for(int r = 0; r<N; r++){
            for(int c = 0; c<M; c++){
                map[r][c] = sc.nextInt();
            }
        }
    }

    static void fillAir(){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0});
        visited = new boolean[N][M];
        visited[0][0] = true;
        map[0][0] = -1;
        while(!queue.isEmpty()) {
            int[] info = queue.poll();
            int r = info[0];
            int c = info[1];
            for (int d = 0; d < 4; d++) {
                int idr = r + dr[d];
                int idc = c + dc[d];
                if (idr < 0 || idc < 0 || idr >= N || idc >= M) continue;
                if(visited[idr][idc]) continue;
                if (map[idr][idc] == 0||map[idr][idc]==-1) {
                    map[idr][idc] = -1;
                    queue.add(new int[]{idr, idc});
                    visited[idr][idc] = true;
                }
            }
        }
    }

    static void selectTargetCheese(){
        boolean flag = false;
        for(int r = 0; r<N; r++){
            for(int c = 0; c<M; c++){
                if(map[r][c]==1){
                    if(checkTarget(r, c)){
                        targetCheese.add(new int[]{r,c});
                        flag = true;
                    }
                }
            }
        }
        if(!flag) exit_flag = true;
    }

    static boolean checkTarget(int r, int c){
        int count = 0;
        for(int d = 0; d<4; d++){
            int idr = r+dr[d];
            int idc = c+dc[d];
            if(idr<0||idc<0||idr>=N||idc>=M) continue;
            if(map[idr][idc]==-1) count++;
        }
        if(count>=2) return true;
        else return false;
    }

    static void meltCheese(){
        for(int[] pos:targetCheese){
            map[pos[0]][pos[1]] = -1;
        }
    }
}
