function solution(land) {
  let answer = 0;
  const dp = Array.from({ length: land.length }, (_, i) => [...land[i]]);

  for (let i = 1; i < land.length; i++) {
    for (let j = 0; j < 4; j++) {
      for (let k = 0; k < 4; k++) {
        if (j === k) continue;
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i][j]);
      }
    }
  }

  return Math.max(...dp[land.length - 1]);
}
