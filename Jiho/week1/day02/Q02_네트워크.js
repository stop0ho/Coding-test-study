function solution(n, computers) {
  let visited = Array(n).fill(false);
  let answer = 0;

  function bfs(start) {
    let q = [start];
    while (q.length > 0) {
      const node = q.shift();
      visited[node] = true;

      for (let next = 0; next < n; next++) {
        if (computers[node][next] === 1 && !visited[next]) {
          q.push(next);
        }
      }
    }
  }

  for (let i = 0; i < n; i++) {
    if (!visited[i]) {
      bfs(i);
      answer++;
    }
  }

  return answer;
}
