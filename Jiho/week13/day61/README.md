## 💡 문제 요약

- 각각의 카드의 수에 대해, 자신보다 큰 카드들 중 자기 배수의 값이 존재하면 점수를 얻고, 배수 쪽은 점수를 잃음
- N(최대 100,000)개의 카드, 각 값은 1~1,000,000 이하의 자연수

## 🧭 접근 방식

- 각 카드의 값을 미리 exists 배열에 표시해서, 존재 유무를 O(1)로 확인
- 1부터 max(1,000,000)까지 반복하면서, 해당 값의 배수도 카드로 존재하면 점수 계산 (score배열 활용)
- 기존 방식은 "각 카드 *각 배수*"에 대해 반복문을 돌려서, 전체 시간 복잡도가 O(N * log M) 수준 (M=1,000,000)

### 어디서 막혔는지

- 본 코드 자체는 정답이 잘 나오고 시간도 통과함.
- 하지만 한 번 더 최적화가 가능한 부분은, cards 배열만큼만 바깥 for문을 도는 것 (즉, 존재하는 카드만을 기준으로, 해당 카드의 배수만 체크).

## ⏱️ 시간복잡도 최적화 방법

- 현재 방식: **O(N log M)** 수준 (실제로는 각 카드마다 최대 20~30회 정도의 곱셈 수행)
- cards 배열을 set으로 만들어, 바깥 반복문을 "존재하는 카드"에만 한정하면, 불필요한 반복 감소
- max(카드)까지만 for문 돌기 (현재 코드도 적용 중)
- "Sieve of Eratosthenes" 같은 체 접근 기법이 이미 적용되어서 더 큰 체감 상의 개선은 어렵고, 실질 코딩량은 비슷함

```js
const N = Number(input[0]);
const cards = input[1].split(" ").map(Number);
const max = Math.max(...cards) + 1;

const exists = new Set(cards);
const score = new Array(max + 1).fill(0);

for (const card of cards) {
  for (let mul = card * 2; mul < max; mul += card) {
    if (exists.has(mul)) {
      score[card]++;
      score[mul]--;
    }
  }
}

console.log(cards.map(v => score[v]).join(' '));
```
**차이점**  
- 바깥 반복문을 1부터 max가 아니라, cards 원소들만 돌면서, 실제로 "존재하는 카드에 한해서" 점수 처리  
- exists를 Set 자료구조로 바꿔서 lookup 정수 효율화  
- 시간상 실제 테스트에서 보다 빨라질 수 있음

