import java.util.*;

class Solution {
    
    static ArrayDeque<Integer> deque = new ArrayDeque<>();
    static int cur;
    static int size;
    
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        cur = k;
        size = n;
        
        for(String command:cmd){
            String[] str = command.split(" ");
            String control = str[0];
            switch(control){
                case "U": 
                    moveColumn(str);
                    break;
                case "D": 
                    moveColumn(str);
                    break;
                case "C":
                    deleteColumn();
                    break;
                case "Z":
                    recoverColumn();
                    break;
            }
        }
        
        answer = buildResult();
        return answer;
    }
    
    static void moveColumn(String[] str){
        String control = str[0];
        int X = Integer.valueOf(str[1]);
        if (control.equals("D")) {
            cur += X;
        }
        if (control.equals("U")) {
            cur -= X;
        }
    }
    
    static void deleteColumn(){
        deque.push(cur);
        size--;
        if(cur==size) cur--;
    }
    
    static void recoverColumn(){
        int value = deque.pop();
        if(value<=cur) cur++;
        size++;
    }
    
    static String buildResult(){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<size; i++) sb.append("O");
        while(!deque.isEmpty()){
            sb.insert(deque.pop(), "X");
        }
        
        return sb.toString();
    }
}