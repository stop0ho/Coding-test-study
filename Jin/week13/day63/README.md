## 📌 문제 탐색하기

### 문제

숫자 야구 게임 중 가능성이 있는 답의 총 개수를 출력

<img width="600" height="351" alt="image" src="https://github.com/user-attachments/assets/a6ab0776-2ddf-49c9-a8d2-5347aa373beb" />


- **1에서 9까지의 서로 다른 숫자 세 개로 구성된 세 자리 수**
- 세 자리의 수의 **동일한 자리에 위치하면 스트라이크**
- 세 자리의 수에 **있긴 하나 다른 위치에 있으면 볼**

### 조건

N: 질문 수 **1 ≤ N ≤ 100**

N의 수만큼 민혁이가 질문한 세 자리 수와 영수가 대답한 스트라이크와 볼의 수가 주어짐

### 풀이 및 시간 복잡도 고려

처음 봤을 때 감도 오지 않았다..

직접 게임을 한다고 하면..

- 123 356 327 결과를 보고 32X 인걸 확신
- 489 결과를 보고 324 또는 328로 답이 2가지임을 알 수 있음

⇒ 이거를 어떻게 코드로 옮기지! 싶어서..ㅎㅎ 알고리즘 분류를 보게 되었다.

알고리즘 분류

- 구현
- **브루트포스 알고리즘** ⇒ 완전 탐색 문제였다!

완전탐색을 한다면, 100 - 999 사이 세 자리 숫자 중에 입력으로 들어온 숫자와 조건이 맞는 숫자의 개수를 세는 문제로 풀이할 수 있다.

총 900개(당연히 조건에 만족하는 숫자 개수는 이거보다는 작다) 숫자 중 N개의 조건을 보고 맞는지 확인하는 것이므로, O(900 * N) = **O(N)**의 시간복잡도를 가짐

→ N의 최댓값은 **100이므로 1초 이내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력 받기
    - 세 자리 수 값은 계산 편의성을 위해 String으로 입력받기
        - 자릿수마다 비교가 필요하므로 **charAt 메서드**를 통해 쉽게 하고 싶었다.
2. 100부터 999까지 세 자리 수 중 조건에 맞는 수 찾기
    - **1에서 9까지의 서로 다른 숫자 세 개로 구성**된 세 자리 수 **판별** 필요
    - **입력받은 수와 스트라이크/볼 개수 비교**해서 조건에 맞는 수인지 **판별** 필요
        - 스트라이크/볼 개수 비교 방법
            - **contains 메서드**를 이용해 몇 개의 숫자가 포함되어 있는지 확인 후 **볼 개수에 저장**
            - 각 **자릿수마다 비교** 후 **스트라이크 개수**를 구함

              → 스트라이크 개수까지 포함되어 있는 볼 개수에서 **스트라이크 개수를 빼서 정확한 볼 개수 구하기**

3. 조건에 맞는 수의 개수 출력

## 📌 문제 탐색하기

### 체스판 다시 칠하기 문제

M X N 크기의 보드판을 잘라 8 X 8 크기의 체스판으로 만들기

- 자르는 위치는 상관 없음
- 체스판을 색칠하는 경우 - 맨 왼쪽 위 칸이 흰색인 경우와 검은색인 경우
    - 변을 공유하는 두 개의 사각형은 다른 색으로 칠해야 함

### 조건

N: 체스판 행 개수 **8 ≤ N ≤ 50**

M: 체스판 열 개수 **8 ≤ M ≤ 50**

### 풀이 및 시간 복잡도 고려

최대 50 X 50짜리 보드판을 8 X 8 크기로 잘라 64개 칸을 탐색하며 최솟값을 구해야 함

최대 자르는 행위는 최대 (N - 7) X (M - 7) 크기만큼 수행하고, 자를 때마다 64개 칸을 탐색하며 색칠해야 함.

추가로, 맨 왼쪽 위의 칸을 기준으로 색칠해야 해서 한 번 자를 때 총 2번 연산해야 하므로 O(N X M X 64 X 2) = **O(NM)**의 시간복잡도를 가짐

→ 50 X 50 X 64 X 2 = **320,000이므로 2초 이내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력 받기
2. 이중 for문으로 8 X 8 체스판 만들어서 색칠하기
    - for문 범위로 8 X 8 체스판이 만들어질 수 있게 하기

    ```java
    for (int i = 0; i < N - 7; i++) {
    	for (int j = 0; j < M - 7; j++) { ... }
    }
    ```

    - 이중 for문 내부에서 또 다른 이중 for문을 만들어 64개의 칸 탐색하기 (총 2번 필요)

    ```java
    // 맨 왼쪽 위 칸이 흰색인 경우
    for (int k = i; k < i + 8; k++) {
    	for (int l = j; l < j + 8; l++) { ... }
    }
    
    // 맨 왼쪽 위 칸이 검은색인 경우
    for (int k = i; k < i + 8; k++) {
    	for (int l = j ;l < j + 8; l++) { ... }
    }
    ```

3. 모든 케이스에서 색칠 횟수를 구한 후, 최솟값을 출력

---

## 📌 시도 회차 수정 사항

### 1회차

- 줄이 바뀔 때를 고려하지 않음 (줄이 바뀌면 전 줄 마지막에 색칠한 색과 똑같은 색을 칠해야 함)

  → 가장 첫 번째 줄을 제외하고는 줄이 바뀔 때마다 **`isWhite = !isWhite`** 코드를 적용해 직전에 색칠한 색과 동일한 색을 칠해줌

- 색칠하는 코드는 k, l 변수를 사용해야 했는데 i, j를 사용함 (;;)

  → k, l로 변경


### 느낀 점
사실 이 문제는.. 3달 전에 깔짝 공부할 때 풀다가 실패했던 문제다.
오늘 비슷한 문제를 풀어서 성공한 것 같긴 하지만 ㅎㅎ 그래도 뿌듯하다.
풀이를 이렇게 꼼꼼하게 쓰면서 하니까 시간복잡도도 생각하기 좋다~
이 프로그램 끝나도 계속 이런 식으로 공부해나가 볼 마음이 든다 ㅎㅎ 기분 좋아
