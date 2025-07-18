const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const op = input[0]
  .split(" ")
  .slice(0, -1)
  .map((v) => +v);
const n = op.length;

const dp = Array.from({ length: n + 1 }, () =>
  Array.from({ length: 5 }, () => Array(5).fill(Infinity))
);

function cost(from, to) {
  if (from === to) return 1;
  if (from === 0) return 2;
  if (Math.abs(from - to) === 2) return 4;
  return 3;
}

dp[0][0][0] = 0;

for (let step = 0; step < n; step++) {
  const next = op[step];
  for (let l = 0; l < 5; l++) {
    for (let r = 0; r < 5; r++) {
      const currCost = dp[step][l][r];
      if (currCost === Infinity) continue;

      dp[step + 1][next][r] = Math.min(
        dp[step + 1][next][r],
        currCost + cost(l, next)
      );

      dp[step + 1][l][next] = Math.min(
        dp[step + 1][l][next],
        currCost + cost(r, next)
      );
    }
  }
}

let answer = Infinity;
for (let l = 0; l < 5; l++) {
  for (let r = 0; r < 5; r++) {
    answer = Math.min(answer, dp[n][l][r]);
  }
}
console.log(answer);
