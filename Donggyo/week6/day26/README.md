## 미로 탈출 명령어
시간 초과의 지옥에 빠져, 울면서 문제 풀었습니다.

### 접근 과정
1. 가장 처음 dfs로 구현을 했습니다. 시작 - 도착점에 도달하면 list에 넣었고, 문자열은 알파벳 순으로 지정했기 때문에 list.get(0) 하면 정답.

```java
import java.util.*;

class Solution {  
    static int N,M,X,Y,R,C,K;
    static int[] dr = {1,0,0,-1}; // dlru
    static int[] dc = {0,-1,1,0};
    static ArrayList<String> list = new ArrayList<>();
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";
        ...
        dfs(X,Y,0,"");
        if(!list.isEmpty()){
            answer = list.get(0);
        }
        return answer;
    }
    
    static void dfs(int r, int c, int count, String path){
        if(count>K) return;
        if(count == K && r==R && c==C){
            list.add(path);
            return;
        }
        for(int d=0;d<4;d++){
            int idr = r+dr[d];
            int idc = c+dc[d];
            ...
        }
    }
}
```

- 테케 4개만 통과. 시간 초과 넘 크게 났음. 맥북 터지는 줄 알았음.

2. 방향 경로를 알파벳 순으로 비교해서, 더 높은 값은 탐색하지 않게 조건 추가했습니다.
```java
class Solution {  
    ...
    static String answer = "z";
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        ...
        dfs(X,Y,0,"");
        return answer.equals("z")? "impossible": answer;
    }
    
    static void bt(int r, int c, int count, String path){
        if(count>K) return;
        if(path.compareTo(answer)>0) return; //!!!!!
        if(count == K && r==R && c==C ){
            answer = path;
            return;
        }
        for(int d=0;d<4;d++){
          ...
        }
    }
}
```
- answer를 알파벳 가장 끝인 z로 잡고, 비교하면서 최소 값만 진입할 수 있게함.
- 마찬가지로 시간 초과. 그래도 시간을 많이 줄였습니다. 대략 20ms -> 2.00ms

3. 거리 계산 로직 추가
- 백트래킹으로 해결해야하나 삽질을 많이 하다가, 인터넷 풀이 참고를 했습니다.

- 수학 계산으로 가망 없으면 진입하지 못하게 하기
```java
import java.util.*;

class Solution {  
    ...
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        ...
        //!!!
        if (getDistance(y,x) > K || (K - getDistance(y,x)) % 2 == 1){
            return "impossible";
        }
        dfs(X,Y,0,"");
        return answer.equals("z")? "impossible": answer;
    }
    
    static void dfs(int r, int c, int count, String path){
        if(count>K) return;
        if(path.compareTo(answer)>0) return;
        if(count+getDistance(r,c)>K) return; //!!!!
        ...
    }
    
    static int getDistance(int r, int c){
        return Math.abs(R-r)+Math.abs(C-c);
    }
}
```

- 대단한 알고리즘이 아닌 간단한 조건으로 시간 초과를 해결하는 카카오스러운..? 문제를 간혹 만나는데, 그때마다 풀이를 어렵게 생각하는 습관에 익숙해져있나 싶어요. 쉽게 생각해보는 것에도 익숙해질 필요를 느꼈습니다.