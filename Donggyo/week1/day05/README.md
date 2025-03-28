## 표편집 문제 분석

### 문제 조건
- n: 표의 행 개수
- k: 선택한 행의 위치
- Cmd: 문자열 배열

- 5 ≤ n ≤ 1,000,000
- 0 ≤ k < n
- 1 ≤ cmd의 원소 개수 ≤ 200,000


### 문제 풀이
세 가지 변수를 잘 관리하는 것이 포인트이다.

1. 현재 컬럼의 위치를 나타내는 정수형 `cur`
2. 현재 표의 전체 크기를 나타내는 정수형 `size`
3. 삭제한 컬럼의 위치를 보관하는 스택 자료구조인 `deque`
  (자바에서는 stack이 레거시 자료구조라 deque로 구현함)

<br>

그리고 명령어에 따른 동작은 크게 세 가지이다.
1. moveColumn()
-  방향에 따라 현재 컬럼 위치(`cur`)를 이동한다.

2. deleteColumn()
- 행을 삭제한다.
- 표에서 행이 하나 없어지는 것이기 때문에, size도 하나 줄인다.
- 삭제한 행의 위치는 stack에 저장한다. 복구를 위함이다.

3. recoverColumn()
- 행을 복구한다.
- stack은 LIFO이기 때문에 pop을 통해 마지막 값을 복구할 수 있다.
- 행이 복구되었기 때문에 size를 다시 늘린다.
- 현재 포인터의 위치와 비교해서 cur 값보다 아래의 값이 들어오면 cur를 ++한다.

<br>

이제 `size`, `stack` 자료구조를 이용해 정답을 만들 수 있다.
1. size 만큼 "O"으로 채운다.
2. stack에서 pop을 하며 해당 인덱스를 "X"로 채운다.

쉽게 생각해, `삭제한 행들의 인덱스`만 기억하면 결과를 만들어낼 수 있다.

