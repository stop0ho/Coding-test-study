import java.util.*;

class Solution {
    static int[] sum;
    static int[] people;
    static Node[] stageFail;
    
    static class Node{
        int stageNum;
        double failRate;
        
        public Node(int stageNum, double failRate){
            this.stageNum = stageNum;
            this.failRate = failRate;
        }
    }
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        people = new int[N+1];
        for(int i = 0; i<stages.length;i++){
            people[stages[i]-1]++;
        }
        sum = new int[N+1];
        sum[N] = people[N];
        for(int i = N-1;i>=0;i--){
            sum[i] = people[i]+sum[i+1];
        }
        stageFail = new Node[N];
        
        for(int i = 0; i<N;i++){
            double failRate = (double)people[i]/(double)sum[i];
            if(people[i]==0||sum[i]==0) failRate = 0.0;
            stageFail[i] = new Node(i, failRate);
        }

        Arrays.sort(stageFail, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o2.failRate, o1.failRate);
            }
        });
        for(int i = 0; i<N;i++){
            answer[i] = stageFail[i].stageNum+1;
        }
        return answer;
    }
}