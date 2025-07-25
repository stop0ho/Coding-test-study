⏱️ 걸린 시간: 20분

🧠 난이도 느낌: ★★☆☆☆

---

## 💡 문제 요약

**핵심 조건:**

- `land` 는 N행 4열로 구성된 2차원 배열
- 각 행에서 **같은 열을 연속해서 밟을 수 없음**
- 마지막 줄까지 밟았을 때 얻을 수 있는 점수의 최댓값 반환

**입력 제약**:

- 1 ≤ `land.length` ≤ 100,000 (N이 최대 10^5)

---

## 🧭 접근 방식

- dp에 각 칸까지 도달했을 때의 최대 점수 기록
- 같은 열을 밟으면 안 되는 조건때문에 현재 열을 제외한 이전 행의 최댓값을 더했다.
- 시간복잡도: `O(N)`

### 개선 방안

- 현재 매 루프마다 3개의 요소를 만들어 새로운 배열을 생성하고 거기서 최댓값을 구하고 있으므로 약간의 병목이 발생할 수 있다. 하지만 입력 제약이 그렇게 빡세지 않아서 지금 그대로도 괜찮을 듯 하여 냅뒀다.

---