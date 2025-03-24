## 섬 연결하기 문제

### 문제 풀이
- **`A - B`** , **`B - C`** 로 연결되어있으면 A - C도 서로 통행할 수 있음, 이런 경우에 최소 비용으로 통행하기!
  - 이러면 생각해야 할 게, **`A - B`** , **`B - C`** 일 때, **`A - C`** 를 굳이 연결할 필요가 없음. 비용만 늘어남
    => 사이클이 존재하면 안된다!
- 일단 최소 비용이 필요한 거니까, 비용이 적은 순으로 정렬해서 사이클이 안 생기게 연결하면 되는걸까?
    => 최소 신장 트리 문제
    => 유니온 파인드 알고리즘을 이용해 구현 가능
    => 사이클이 형성되지 않으면서 모든 정점 연결 가능

### 유니온 파인드 알고리즘
```java
private static int find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}

private static void union(int x, int y) {
    int root1 = find(x);
    int root2 = find(y);
    parent[root2] = root1;
}

// 배열 초기화
parent = new int[n];
for (int i = 0; i < n; i++) {
    parent[i] = i;
}

// 이 이후에 적절하게 union한 다음에 find해서 문제 풀이하면 됨~!
```
