import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class B2665_미로만들기 {
    static int N;
    static int INF = Integer.MAX_VALUE;
    static char[][] map;
    static int[][] dist;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        input();
        bfs();
        System.out.println(dist[N-1][N-1]);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new char[N][N];
        for(int r = 0; r<N;r++){
            String str = sc.next();
            for(int c=0;c<N;c++) {
                map[r][c] = str.charAt(c);
            }
        }
    }

    static void bfs(){
        dist = new int[N][N];
        for(int r=0;r<N;r++){
            Arrays.fill(dist[r], INF);
        }
        dist[0][0] = 0;
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0,0});

        while(!deque.isEmpty()){
            int[] cur = deque.poll();
            int r = cur[0];
            int c = cur[1];

            for(int d=0;d<4;d++){
                int idr = r+dr[d];
                int idc = c+dc[d];

                if(idr<0||idc<0||idr>=N||idc>=N) continue;
                int weight = map[idr][idc] == '0'? 1 : 0;

                if(dist[r][c]+weight<dist[idr][idc]){
                    if(map[idr][idc]=='0'){ //검은색 가중치 1
                        deque.add(new int[]{idr,idc});
                        dist[idr][idc] = dist[r][c]+weight;
                    }
                    if(map[idr][idc]=='1'){ //흰색 가중치 0
                        deque.addLast(new int[]{idr,idc});
                        dist[idr][idc] = dist[r][c]+weight;
                    }
                }
            }
        }
    }
}
