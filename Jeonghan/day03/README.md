## 예산

### 문제 분석
그리디 알고리즘 정당성 평가  
1. 탐욕적 선택속성 만족    
2. 최적부분구조 만족  

가장 적은 금액을 필요로 하는 부서부터 하나씩 처리해서 최적해 보장   

### 문제 풀이
1. 부서의 필요 금액이 가장 적은 순으로 정렬
2. 가장 적은 순으로 하나씩 예산 지급
3. 더 이상 예산을 줄 수 없는 경우 탐색 종료

<br>

## 귤 고르기

### 문제 분석
그리디 알고리즘 정당성 평가
1. 탐욕적 선택속성 만족
2. 최적부분구조 만족

가장 갯수가 많은 귤 크기부터 하나씩 처리해서 최적해 보장

### 문제 풀이
1. 귤 크기 별로 갯수 계산
2. 갯수가 많은 순으로 정렬
3. 하나씩 처리하면서 총 귤 갯수 계산

### 문제 접근
그냥 풀기보다는 성능이 안좋아도 `stream`을 최대한 사용하는 코드를 작성했습니다.  
개인적으로 연습하고 싶던 부분이라 시도해봤습니다.  
비효율적이고 성능도 안나오기 때문에 눈으로만 봐주세요.  

1.의도 별로 나눠서 `stream` 적용

```java
public int solution(int k, int[] tangerine) {
    int answer = 0;
    Map<Integer, Long> tangerineMap = Arrays.stream(tangerine)
        .boxed()
        .collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        
    List<Map.Entry<Integer, Long>> tangerineSortedList = tangerineMap.entrySet()
        .stream()
        .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
        .collect(Collectors.toList());
        
    for (Map.Entry<Integer, Long> e : tangerineSortedList) {
        answer++;
        if ((k -= e.getValue().intValue()) <= 0) {
            break;
        }
    }
    return answer;
}
```

2. `stream` 미적용 코드 삭제
```java
public int solution(int k, int[] tangerine) {
    Map<Integer, Long> tangerineMap = Arrays.stream(tangerine)
        .boxed()
        .collect(Collectors.groupingBy(t -> t, Collectors.counting()));

    return tangerineMap.entrySet()
        .stream()
        .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
        .map(e -> new int[] { 0, e.getValue().intValue()})
        .reduce(new int[] { 0, 0 }, (acc1, acc2) -> acc1[0] < k ? new int[] { acc1[0] + acc2[1], acc1[1] + 1 } : acc1)[1];
}
```

3. 하나의 `stream`으로 처리
```java
public int solution(int k, int[] tangerine) {
    return Arrays.stream(tangerine)
        .boxed()
        .collect(Collectors.groupingBy(t -> t, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
        .map(e -> new int[] { 0, e.getValue().intValue()})
        .reduce(new int[] { 0, 0 }, (acc1, acc2) -> acc1[0] < k ? new int[] { acc1[0] + acc2[1], acc1[1] + 1 } : acc1)[1];
}
```

<br>
