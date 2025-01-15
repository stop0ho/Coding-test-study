## 예산
- [문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12982?language=javascript)
- 풀이: 최대한 많은 부서에게 물품을 지원해줘야 하므로 신청 금액이 가장 작은 부서부터 순차적으로 지원해주면 가능하다고 판단
- 아쉬운 점: `reduce`를 사용할 수 있겠으나 난 아직도 `reduce`가 너무 어렵다.. for문 최고

## 귤 고르기
- [문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/138476?language=javascript)
- 크기가 같은 것끼리 집합을 이루고, 집합의 개수가 최소여야 함. => 개수가 제일 많은 것부터 넣으면 되지 않을까?
- 새로 알게된 것: `Object.entries(객체 이름)`은 [key, value] 쌍의 배열 반환