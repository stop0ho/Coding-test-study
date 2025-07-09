⏱️ 걸린 시간: 47분 20초 (실패 후 답 참고)

🧠 난이도 느낌: 개어려움 잉잉

---

## 💡 문제 요약

- 가장 긴 팰린드롬 부분 문자열 반환
- 문자열 길이 최대 **1000**
- 반드시 연속된 문자열이어야 함

---

## 🧭 접근 방식

1️⃣ 내 처음 접근

- 투 포인터를 사용하려고 했다.
- 문자열 양 끝에서 시작해서 같은 문자를 찾으면 `temp` 에 추가하고 이후 `start + 1` 과 `end - 1` 로 좁혀가며 팰린드롬을 찾으려 함
- 만약 다른 문자가 나오면 end만 왼쪽으로 한 칸씩 옮기고 다시 시도하는 방식으로 설계했다.
- 문제는 **홀수 팰린드롬과 짝수 팰린드롬**을 구분하기 어려웠고 구현이 꼬이면서 막혔다.

**2️⃣ 중심 확장법**

- 팰린드롬은 **가운데를 중심으로 좌우가 대칭**이다.
- 모든 문자를 중심으로 잡고 양쪽으로 퍼지며 팰린드롬을 찾는 방법을 사용했다.
- 이때, 홀/짝은 다음과 같이 체크했다.
    - 홀수 팰린드롬 (i, i)
    - 짝수 팰린드롬 (i, i+1)
- 각 경우마다 양쪽 문자가 같을 때까지 확장하면서 가장 긴 팰린드롬을 업데이트했다.

---

## 💥 핵심 로직 & 코드

```jsx
let longestPalindrome = function (s) {
  function expandAroundCenter(left, right) {
    while (left >= 0 && right < s.length && s[left] === s[right]) {
      left--;
      right++;
    }
    return s.slice(left + 1, right);
  }

  let result = "";

  for (let i = 0; i < s.length; i++) {
    const oddPalindrome = expandAroundCenter(i, i);
    const evenPalindrome = expandAroundCenter(i, i + 1);

    if (oddPalindrome.length > result.length) {
      result = oddPalindrome;
    }
    if (evenPalindrome.length > result.length) {
      result = evenPalindrome;
    }
  }

  return result;
};

```

---

## 새로 배운 개념 or 패턴

- 중심 확장법이라는 게 있구나

---

## 틀린 이유 / 실수 포인트

- 투 포인터는 정렬된 배열이나 양 끝에서 조건을 만족시키며 좁히는 문제에 적합하다.
- 이렇게 대칭 구조를 가지는 경우에는 중심 확장법이 적합하다.
- 홀수/짝수를 한 번에 처리하려다가 망함.