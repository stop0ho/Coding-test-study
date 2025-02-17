### 문제 분석
시작 위치에서 목표 위치까지 탐색하면서 벽을 최소로 변경  
벽을 하나씩 문으로 변경하면서 든 비용을 기준으로 탐색  
백준 [치즈 문제](https://www.acmicpc.net/problem/2636)와 유사(이것도 한번 풀어보세요. 재밌습니다.)  

<br>

### 문제 조건
목표지점까지 최단 거리가 아닌 최소 비용  
기존 벽을 모두 문으로 변경 가능  

<br>

### 문제 풀이
1. 시작 지점부터 탐색 시작
2. 아래 조건으로 비용 관리 
```
현재 위치가 문이고, 탐색 위치가 문인 경우 -> 비용 증가 없음
현재 위치가 문이고, 탐색 위치가 벽인 경우 -> 비용 증가
현재 위치가 벽이고, 탐색 위치가 문인 경우 -> 비용 증가 없음
현재 위치가 벽이고, 탐색 위치가 벽인 경우 -> 비용 증가
```

3. 비용이 적은 순으로 탐색
4. 목표 지점 발견시 종료

<br>
