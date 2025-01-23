## 피로도

### 문제 분석
탐색한 던전에 대한 순서가 중요  
결국 모든 순서쌍을 탐색하기 때문에 dfs  
탐색할 필요가 없는 경우는 해당 경우는 더 이상 탐색할 필요 없기 때문에 백트래킹 사용  

### 문제 풀이
1. 던전을 하나씩 탐색
2. 탐색한 던전에 필요한 피로도 감소
3. 다음 던전을 탐색하면서 위의 과정 반복
4. 모든 과정 중에 최대 던전 탐색 횟수 반환

<br>

## NQueen

### 문제 분석
전형적인 백트래킹 문제  

### 문제 풀이
1. x 좌표를 하나씩 올리면서 모든 y 좌표 확인
2. 방정식을 이용해서 탐색 가능한 좌표인지 확인
3. 모든 퀸을 배치한 후 카운팅
4. 위의 과정 반복

<br>

## 양궁대회

### 문제 분석
문제가 굉장히 더러움  
말장난이 심함  

### 문제 풀이
1. 탐색 중인 점수에 대해 라이언이 이기는 경우와 이기지 않는 경우를 탐색
2. 최종 점수에 도달하거나 화살을 다 쓴 경우 점수 계산
3. 점수차이가 최대인 경우에 라이언 배열 갱신
4. 점수차이가 이전 최대값과 동일한 경우 최소 점수가 존재하는 배열로 갱신

### 다른 풀이
이 문제를 한 1년 전에 풀었던 기록이 있더라구요.  
이 당시에는 많이 복잡하게 비트 연산으로 상태를 저장해서 풀었던 것 같아요.  

```java
import java.util.*;

class Solution {
    
    private int max = Integer.MIN_VALUE;
    private int[] info;
    private int[] answer = new int[11];
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        search(0, n, 0, 0, 0);
        return max <= 0 ? new int[] { -1 } : answer;
    }
    
    private void search(int cnt, int n, int flag, int a, int r) {
        if (cnt == 10) {
            if (max <= r - a) {
                int[] sub = new int[11];
                for (int i = 0; i < 11; i++) {
                    if ((flag & (1 << i)) != 0) {
                        sub[i] = info[i] + 1;
                    } else {
                        sub[i] = 0;
                    }
                }
                if (n != 0) {
                    sub[10] += n;
                }
                if (max < r - a) {
                    max = r - a;
                    answer = sub;
                } else {
                    for (int i = 10; i >= 0; i--) {
                        if (sub[i] > answer[i]) {
                            answer = sub;
                            break;
                        } else if (sub[i] < answer[i]) {
                            break;
                        }
                    }
                }
            }
            return;
        }
        
        
        if (n > info[cnt] && n != 0) {
            search(cnt + 1, n - info[cnt] - 1, flag | (1 << cnt), a, r + 10 - cnt);
        }
        search(cnt + 1, n, flag, info[cnt] != 0 ?  a + 10 - cnt : a, r);
    }
    
}
```

<br>
