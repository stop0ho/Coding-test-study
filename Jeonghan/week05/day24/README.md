### 문제 분석
각 심사관마다 독립적이기 때문에 하나씩 처리(다른 심사관에게 영향없음)  

<br>

### 문제 조건
심사 대기자는 최대 1,000,000,000  
심사 시간은 최대 1,000,000,000  
심사관은 최대 100,000  

<br>

### 문제 풀이
초기 접근은 간단하게 O(n) 탐색  
n 자체가 10억이라 시간 초과 발생(자바 기준 1억번 연산이 1초)  

```java
public long solution(int n, int[] times) {
    PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
    for (int time : times) {
        pq.offer(new long[] { time, time });
    }
    for (int i = 0; i < n - 1; i++) {
        long[] cur = pq.poll();
        pq.offer(new long[] { cur[0], cur[1] + cur[0] });
    }
    return pq.peek()[1];
}
```

<br>

심사 시간을 먼저 선택한 후 가능한지 탐색  
해당 시간을 나눈 몫의 총합이 그 시간 동안 처리 가능한 인원  
가장 n과 같거나 큰 값을 구하기 위해 `lower bound` 사용  
