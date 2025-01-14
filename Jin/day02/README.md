## 네트워크 문제

### 문제 풀이
**`DFS`** 사용
- visited 배열을 사용해 방문 여부를 체크

```java
    private void DFS(int node, int[][] computers) {
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                visited[node] = true;
                DFS(i, computers);
            }
        }
    }
```
- 재귀로 DFS를 구현
- 방문하지 않은 노드이면서 연결이 되어있을 경우에만 방문할 수 있도록 조건 설정

```java
for (int i = 0; i < computers.length; i++) {
	if (!visited[i]) {
		answer++;
		visited[i] = true;
		DFS(i, computers);
	}
}
```
- 방문하지 않은 곳이 있을 때마다 네트워크의 개수를 늘려줌
  - 시작지점 이외에 방문하지 않은 곳이 있다는 말이 곧 연결되지 않은 노드가 존재한다는 뜻이니까