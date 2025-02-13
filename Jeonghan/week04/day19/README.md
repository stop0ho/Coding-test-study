### 문제 분석
2의 배수로 갈수 있다면 가고, 아니라면 비용 1 추가  
탑 다운 방식 계산 필수  

<br>

### 문제 조건
시간 제한이 굉장히 타이트한 문제  

1. 바텀 업 메모이제이션 - 효율성 0점(메모리 초과)

```java
public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = i % 2 == 0 ? dp[i / 2] : dp[i - 1] + 1;
        }
        return dp[n];
}
```

<br>

2. 스트림 - 효율성 0점(시간 초과)  

```java
public int solution(int n) {
    return (int) IntStream.iterate(n, a -> a / 2)
        .takeWhile(a -> a > 0)
        .filter(a -> a % 2 == 1)
        .count();
}
```

