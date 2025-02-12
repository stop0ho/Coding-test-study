## 피로도 문제

### 문제 풀이
- 전체 경우에 대해서 모두 탐색해서 최댓값을 얻어와야 함 → DFS로 전체 탐색
  - 던전을 순서대로 모두 탐색한 후에 다른 경우에 대해서도 탐색을 하면서 최댓값을 얻어와야 하니까 **`visited[i] = false`** 로 변경해주어야 함

## N-Queen 문제

### 문제 풀이
- 퀸을 하나 배치할 때마다 해당 퀸의 행/열/대각선 방향에 배치하지 못하게 하는 문제!
  - 일단 퀸은 같은 행에 배치할 순 없으니까 모두 다 다른 행에 배치하기 (행은 해결 완료, for문 써서 한 줄 씩 넣을 거니까)
  - 열도 다 다른 열에 배치해야 함. 그런데 다 다른 열 이면서 대각선이어도 안됨
    - 열은 길이 N인 배열 하나 만들어서 거기 열에 이미 누가 점유하고 있는지 여부 확인하는 걸로!
    - 하나라도 위반되면 그 이상은 탐색못하게 하기
    
  → 그런데 대각선 체크는 어떻게 하지?? 항상 이런 문제들 대각선 체크 때문에 못풀었던 것 같음

### 대각선 체크
(이 부분은 책을 보고 아이디어를 얻었습니다.)
- 행 + 열의 합을 이용해 대각선 위치에 있는지 확인하기
  
  (ex) 퀸이 (1, 2)에 위치하고 있다면 합은 3. (3, 0), (2, 1), (0, 3)과 같이 합이 3인 친구들은 다 오른쪽 위에서 왼쪽 아래 방향인 대각선이므로 대각선 체크가 가능!
  - 왼쪽 위에서 오른쪽 아래 방향 대각선의 합은 (행 - 열)의 절댓값이 같은 걸 이용하기??
    - 이렇게하면 (1, 0), (2, 1) 체크하는거랑 (0, 1), (1, 2) 체크하는 거랑 똑같아져서 예상치못한 문제가 발생할듯
    - 책에서는 (행 - 열 + N)을 했는데... 이렇게 하면 1-0+4 = 5, 0-1+4 = 3 이렇게 되어서 다 달라지긴 함!

## 양궁대회 문제

### 문제 풀이
- 문제 조건 정리
  - 라이언이 쏠 화살의 개수 n
  - 어피치가 맞힌 과녁 개수(10부터 0까지 순서대로) 배열 info

  **!! 라이언이 가장 큰 점수차이로 이겨야 함 !!**
  **!! 최댓값이 같은 점수가 여러 개일 때 낮은 점수를 더 맞힌 경우를 반환해야 함 !!**

- 라이언은 어피치가 쏜 거보다 딱 한발만 더 쏴도 점수를 얻는 거니까 해당 방식을 이용
  - [여기 블로그](https://record-developer.tistory.com/124) 아이디어를 참고했습니다!
  - 이렇게 하면, 가장 마지막에 저장되는 배열이 문제에서 원하는 **낮은 점수를 더 맞힌 경우**가 되므로 따로 체크를 더 할 필요도 없음!