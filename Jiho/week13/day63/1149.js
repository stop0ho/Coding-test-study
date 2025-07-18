const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(input[0]);
const rgb = input.slice(1).map((v) => v.split(" ").map((v) => +v)); // 빨강, 초록, 파랑

const dp = Array.from({ length: N }, (_, i) => [...rgb[i]]);

for (let i = 1; i < N; i++) {
  for (let j = 0; j < 3; j++) {
    // 같은 열이 아닌 것만 체크해야 함.
    let min = Infinity;
    for (let k = 0; k < 3; k++) {
      if (j === k) continue;
      if (min > dp[i - 1][k]) min = dp[i - 1][k];
    }
    dp[i][j] += min;
  }
}

console.log(Math.min(...dp[N - 1]));
