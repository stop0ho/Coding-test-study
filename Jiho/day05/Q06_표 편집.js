function solution(n, k, cmd) {
  let stack = [];
  const prev = Array(n)
    .fill(null)
    .map((_, i) => i - 1);
  const next = Array(n)
    .fill(null)
    .map((_, i) => i + 1);
  next[n - 1] = null;
  let now = k;

  for (const command of cmd) {
    const [action, number] = command.split(" ");

    if (action === "U") {
      for (let i = 0; i < +number; i++) {
        now = prev[now];
      }
    } else if (action === "D") {
      for (let i = 0; i < +number; i++) {
        now = next[now];
      }
    } else if (action === "C") {
      stack.push([prev[now], now, next[now]]);
      if (prev[now] !== null) {
        next[prev[now]] = next[now];
      }
      if (next[now] !== null) {
        prev[next[now]] = prev[now];
      }
      now = next[now] !== null ? next[now] : prev[now];
    } else if (action === "Z") {
      const [p, c, n] = stack.pop();
      if (p !== null) next[p] = c;
      if (n !== null) prev[n] = c;
    }
  }

  const answer = Array(n).fill("O");
  stack.forEach(([_, c]) => {
    answer[c] = "X";
  });

  return answer.join("");
}
