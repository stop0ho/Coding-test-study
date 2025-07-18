## 💡 문제 요약

- DDR 게임에서 왼발 또는 오른발로 발판을 밟음
- 입력으로 주어지는 방향을 순서대로 밟을 때 **최소한의 힘**으로 밟는 경우의 힘의 총합을 구해야 함
- 입력 길이는 최대 100,000이지만 실제 명령 수는 100 이하

---

## 🧭 접근 방식

- **최소한의 힘** ⇒ 아! DP구나
- `dp[step][left][right]` : 최소 힘
    - 현재 step번째 방향 밟을 예정이고, 왼발은 left, 오른발은 right에 위치해 있을 때
- 왼발로 밟을지 오른발로 밟을지를 선택하면서 최소 힘 누적

### 시간 복잡도 분석

- DP 상태 수: 최대 100단계 * 5(left) * 5(right) = 2500
- 각 상태에서 두 가지 이동 선택 가능하므로 총 연산수 5000 정도

---

## 💥 핵심 로직 & 코드

```jsx
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
```