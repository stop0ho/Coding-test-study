## 섬 연결하기

### MST (Minimum Spanning Tree, 최소 신장 트리)
- 스패닝 트리 : 그래프의 모든 정점들을 잇지만, 사이클이 없는 그래프를 의미한다.
- 최소 스패닝 트리 : 스패닝 트리 중 간선의 가중치 합이 최소가 되는 스패닝 트리

### 크루스칼 알고리즘
- MST를 구하는 대표적인 알고리즘 
- 그리디 하게 동작한다.

#### 동작과정
1. 선택되지 않은 간선 중에 최소 가중치인 간선을 선택한다.
2. 선택 했을 때, 사이클이 발생하지 않아야한다.
3. 총 V-1개의 간선이 선택될 때까지 반복한다.