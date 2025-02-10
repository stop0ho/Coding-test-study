function solution(n, costs) {
  let answer = 0;
  const parent = Array(n)
    .fill(0)
    .map((_, index) => index);

  costs.sort((a, b) => a[2] - b[2]);

  function find(node) {
    if (parent[node] === node) return node;
    return (parent[node] = find(parent[node]));
  }

  function union(u, v) {
    const root_u = find(u);
    const root_v = find(v);
    if (root_u !== root_v) parent[root_v] = root_u;
  }

  let edgeCount = 0;

  for (const [island1, island2, cost] of costs) {
    if (find(island1) !== find(island2)) {
      union(island1, island2);
      answer += cost;
      edgeCount++;
    }

    if (edgeCount === n - 1) break;
  }

  return answer;
}
