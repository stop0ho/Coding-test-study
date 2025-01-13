### 문제 분석
시작점에서 목표지점까지의 최단 경로를 찾는 문제  
갈 수 있는 위치와 갈 수 없는 위치 존재

<br>

### 문제 조건
시작점 (0, 0)  
목표지점 (n - 1, m - 1)  
갈 수 있는 위치 1  
갈 수 없는 위치 0

<br>

### 문제 풀이
1. 시작점부터 주변 노드를 하나씩 탐색
2. 새롭게 탐색한 노드가 목표지점인지 판단
3. 새롭게 탐색한 노드의 주변 노드들 중에서 이미 탐색했거나 벽인 경우를 제외하고 탐색 대상으로 설정
4. 탐색 대상으로 설정된 노드들을 설정된 순서대로 1번 과정부터 반복

<br>