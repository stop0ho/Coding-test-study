import java.util.*;

class Solution {
    
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<Node>[] list;
    
    static class Node implements Comparable<Node>{
        int v;
        int w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Node o){
            return w-o.w;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
    
        initialize(road, N);
        dijkstra();
        answer = countTarget(K);
        
        return answer;
    }
    
    static void initialize(int[][] road, int N){
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        
        list = new ArrayList[N+1];
        for(int i = 0; i<N+1;i++){
            list[i] = new ArrayList<>();
        }
        
        for(int r=0; r<road.length;r++){
            int st = road[r][0];
            int ed = road[r][1];
            int w = road[r][2];
            list[st].add(new Node(ed, w));
            list[ed].add(new Node(st, w));
        }
    }
    
    
    static void dijkstra(){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist[1] = 0;
        queue.add(new Node(1, 0));
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int v = node.v;
            int w = node.w;
            
            if(dist[v]<w) continue;
            for(int i = 0; i<list[v].size(); i++){
                int v2 = list[v].get(i).v;
                int w2 = list[v].get(i).w;
                
                if(dist[v2]>w2+w){
                    dist[v2]=w2+w;
                    queue.add(new Node(v2, w+w2));
                }
            }
        } 
    }
    
    static int countTarget(int K){
        int count = 0;
        for(int d:dist){
            if(d<=K) count++;
        }
        return count;
    }
}