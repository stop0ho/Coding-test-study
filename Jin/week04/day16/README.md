## 땅따먹기 문제

### 문제 풀이
- 밟았던 열을 연속으로 밟지 못함!
  -> 그렇다면 각 열마다 최대치로만 더하면 되는 거 아닌가?
  -> 근데 잠깐만 생각해도 이것이 안된다는 것을 바로 알 수 있음.. 같은 열을 연속으로 밟지 못한다는 조건 때문에 다른 열이면서 그 중에서 최댓값 조합으로 한다고 무조건 그게 최댓값이라고 보장할 순 없다.
- 합했을 때 항상 최댓값이면 되니까 이전 행 중 최댓값 + 현재 위치의 값을 더해가면서 최댓값을 갱신해가면서 풀이