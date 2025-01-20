function solution(N, road, K) {
  const graph = Array.from({ length: N + 1 }, () => []);
  const distance = Array(N + 1).fill(Infinity);
  distance[1] = 0;

  for (const [a, b, c] of road) {
    graph[a].push([b, c]);
    graph[b].push([a, c]);
  }

  const q = [[1, 0]];
  while (q.length > 0) {
    q.sort((a, b) => a[1] - b[1]);
    const [node, dist] = q.shift(); // 가장 짧은 경로 노드

    if (dist > distance[node]) continue;

    for (const [nextNode, nextDist] of graph[node]) {
      const newDist = dist + nextDist;
      if (newDist < distance[nextNode]) {
        distance[nextNode] = newDist;
        q.push([nextNode, newDist]);
      }
    }
  }

  return distance.filter((d) => d <= K).length;
}
