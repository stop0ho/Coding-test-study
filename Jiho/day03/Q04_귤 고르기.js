function solution(k, tangerine) {
  let answer = 0;
  let counts = {};

  // 개수별 정리
  tangerine.forEach((t) => {
    if (!(t in counts)) counts[t] = 0;
    counts[t]++;
  });

  // 개수 많은 순 정렬
  counts = Object.entries(counts)
    .sort((a, b) => b[1] - a[1])
    .map((v) => v[1]);

  // answer
  for (let i = 0; i < counts.length; i++) {
    k -= counts[i];
    answer++;
    if (k <= 0) break;
  }

  return answer;
}
