function solution(N, stages) {
  var answer = [];
  let L = stages.length;
  let cnt = Array.from({ length: N }, (v, i) => [0, 0, i + 1]);

  for (let i = 0; i < L; i++) {
    if (stages[i] > N) continue;
    cnt[stages[i] - 1][0]++;
  }

  cnt[0][1] = L;
  for (let i = 1; i < N; i++) {
    cnt[i][1] = cnt[i - 1][1] - cnt[i - 1][0];
  }

  cnt.sort((a, b) => b[0] / b[1] - a[0] / a[1]);

  for (let i = 0; i < N; i++) {
    answer.push(cnt[i][2]);
  }
  return answer;
}
