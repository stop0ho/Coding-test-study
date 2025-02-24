function solution(n, m, x, y, r, c, k) {
  let answer = "";
  // 이동방향을 사전순으로
  const direction = [
    [1, 0, "d"],
    [0, -1, "l"],
    [0, 1, "r"],
    [-1, 0, "u"],
  ];

  const distance = Math.abs(x - r) + Math.abs(y - c);

  // 도달 불가
  if (distance > k || (k - distance) % 2 !== 0) return "impossible";

  function dfs(x, y, path, depth) {
    if (depth + Math.abs(x - r) + Math.abs(y - c) > k) return;

    if (depth === k) {
      if (x === r && y === c) answer = path;
      return;
    }

    for (const [dx, dy, move] of direction) {
      const nx = x + dx,
        ny = y + dy;
      if (nx > 0 && nx <= n && ny > 0 && ny <= m) {
        dfs(nx, ny, path + move, depth + 1);
        if (answer) return;
      }
    }
  }

  dfs(x, y, "", 0);

  return answer;
}
