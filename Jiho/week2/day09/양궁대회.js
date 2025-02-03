function solution(n, info) {
  let maxDiff = 0;
  let bestShot = Array(11).fill(0);

  const dfs = (idx, arrowsLeft, ryan) => {
    if (idx === 11) {
      if (arrowsLeft > 0) ryan[10] += arrowsLeft;
      let ryanScore = 0,
        apeachScore = 0;

      for (let i = 0; i < 11; i++) {
        if (info[i] === 0 && ryan[i] === 0) continue;
        if (ryan[i] > info[i]) ryanScore += 10 - i;
        else apeachScore += 10 - i;
      }

      const scoreDiff = ryanScore - apeachScore;
      if (scoreDiff > maxDiff) {
        maxDiff = scoreDiff;
        bestShot = [...ryan];
      } else if (scoreDiff === maxDiff) {
        for (let i = 10; i >= 0; i--) {
          if (ryan[i] > bestShot[i]) {
            bestShot = [...ryan];
            break;
          } else if (ryan[i] < bestShot[i]) {
            break;
          }
        }
      }
      return;
    }

    if (arrowsLeft > info[idx]) {
      let newRyan = [...ryan];
      newRyan[idx] = info[idx] + 1;
      dfs(idx + 1, arrowsLeft - newRyan[idx], newRyan);
    }

    dfs(idx + 1, arrowsLeft, [...ryan]);
  };

  dfs(0, n, Array(11).fill(0));

  return maxDiff > 0 ? bestShot : [-1];
}
