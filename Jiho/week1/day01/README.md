## 게임 맵 최단거리 풀이
### 풀이
**최솟값**을 구하는 문제이며 n, m이 1 이상 100 이하이므로 BFS를 사용할 수 있다. ( n*m <= 10,000 )

### 아이디어
- Queue를 따로 구현하지 않고 `shift()`와 `push()`를 이용했다. `shift()` 연산의 시간 복잡도가 O(n)이지만 n이 그리 크지 않아 이렇게 풀어도 문제 없을 것이라고 판단했다.
- `maps`에서 방문한 곳을 관리: 방문한 곳을 0으로 변경해 중복 탐색 방지
- `dist` 배열을 사용해 각 좌표까지의 최단 거리 기록

> [!NOTE]  
> DFS를 재귀로 구현할 경우 깊이 10,000 이내로 제한해야 함  
> BFS/DFS에서 `n * m <= 10^6` 정도여야 탐색 시간이 안전하다고 볼 수 있다.
