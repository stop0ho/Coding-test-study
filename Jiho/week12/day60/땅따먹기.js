function solution(land) {
  let dp = Array.from({ length: land.length }, () =>
    Array.from({ length: 4 }, () => 0)
  );

  // 1. dp 첫째줄 초기화
  dp[0] = [...land[0]];

  // 2. dp 채우기
  for (let i = 1; i < land.length; i++) {
    for (let j = 0; j < 4; j++) {
      dp[i][j] =
        land[i][j] + Math.max(...dp[i - 1].filter((_, index) => index !== j));
    }
  }

  return Math.max(...dp[dp.length - 1]);
}
