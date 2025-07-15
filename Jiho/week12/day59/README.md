⏱️ 걸린 시간: 1시간 15분**(결국 실패 - 다시 풀어봐야 할 듯)**

🧠 난이도 느낌: 졸랭 어려움

---

## 💡 문제 요약

- A부터 B까지 모든 수를 2진수로 변환했을 때 1의 총 개수
- 1 ≤ A ≤ B ≤ **10^16**

---

## 🧭 접근 방식

### 1차: dp

- 처음에는 아래와 같이 `dp` 로 풀었다. 이때의 시간 복잡도는 `O(n)`
    - 나눌 수 있는 가장 큰 2의 지수로 나누고, 나머지에 해당되는 것을 더해주면 답이 된다.
    - 예) 10: 8 + 2, 11: 8 + 3
    
    ```jsx
      for (let i = a; i <= b; i++) {
        const log = Math.floor(Math.log2(i));
        const div = Math.pow(2, log);
        const mod = i % div;
        dp[i] = dp[div] + dp[mod];
        answer += dp[i];
      }
    ```
    
    - **N이 10^16까지 가능**하므로 문제가 생겼다.

### 2차: 비트 규칙

숫자를 2진수로 바꿨을 때 각 자리마다 1이 등장하는 **반복 패턴**이 있다.

| 10진수 | 2진수 |
| --- | --- |
| 0 | 000 |
| 1 | 001 |
| 2 | 010 |
| 3 | 011 |
| 4 | 100 |
| 5 | 101 |
| 6 | 110 |
| 7 | 111 |

각 자리(비트)별로 1이 등장하는 횟수

- 맨 오른쪽 비트(1의 자리): 0,1,0,1,… 반복 → 4번(0~7 중)
- 그 다음 비트(2의 자리): 0,0,1,1,0,0,1,1 → 4번
- 맨 왼쪽 비트(4의 자리): 0,0,0,0,1,1,1,1 → 4번

⇒ 1이 등장하는 횟수는 `전체 개수/2`

이를 적용하면, n = 13일 때

- 0~7: 패턴 반복되므로 계산 가능
- 8~13: 0~5의 1의 개수 + 1(8이 1이니까) * 6

---

## 💥 핵심 로직 & 코드

```jsx
function countOnes(n) {
  let result = 0n;
  let bit = 0n;
  while ((1n << bit) <= n) {
    const blockSize = 1n << (bit + 1n);
    const fullBlocks = n / blockSize;
    const remainder = n % blockSize;
    result += fullBlocks * (blockSize / 2n);
    result += remainder >= (1n << bit) ? (remainder - (1n << bit) + 1n) : 0n;
    bit += 1n;
  }
  return result;
}
```

---

## 새로 배운 개념 or 패턴

- 자바스크립트에는 `Math.log2` 라는 함수가 있다.