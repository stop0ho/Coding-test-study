## 표 편집 문제

### 문제 풀이
※ 평소에 문제 풀이 경험이 정말 없어서(백준 실버 3 이상..?? 문제들은 정말 경험이 없습니다ㅠㅠ) 이번 문제는 책을 보며 풀어보았습니다!!
- **배열**을 그대로 사용해서 풀면 정말정말 쉽겠지만... **삽입 등 비효율적인 부분**이 있다.

**마지막에 리턴할 값?**
- 처음과 비교해서 **행이 삭제됐는지** 여부를 나타냄 (OXOOOX 이런 식으로)
  - 이 점을 이용해서, **인덱스만** 이용해서 연산하기!
- 이름같은 건 중요한 정보가 아님.
  - 나였으면 class를 하나 더 만들어서라도 이름 정보를 넣었을 거 같은데... 이런 점을 배워야 함. **꼭 필요한 정보와 아닌 정보를 구분**해서 간단하게 할 수 있게!

**인덱스만으로 연산**
- 행을 이동할 때마다 이동된 행의 위치를 기반으로(행이 이동될 때마다 바뀌는 상대적인 위치로!) up, down을 표시
  - 행번호가 0이라면, 그 위에는 -1(없다는 뜻), 그 아래는 1 이라는 뜻! up과 down 사이의 숫자값이 현재 그 친구의 행번호

**(가장 마지막 행을 제외한) 삭제 연산**
- 삭제된 행 up, down 값을 그 아래 행으로 옮겨야 함!
  - **`up[down[k]] = up[k]`** / **`down[up[k]] = down[k]`**
  - 그런데 Z를 누르면 복구가 되어야 하니까, 어딘가에 기존 정보를 저장해둬야 함.
    - 스택에서 최근 삭제한 행을 pop하고 restore에 보관
    - restore에 있는 행 기준으로, 윗 행의 다음과 아래 행의 이전이 restore 행이 되어야 함
      - **`down[up[restore]] = up[down[restore]] = restore`**

**`가장 처음과 끝에서 연산하는 경우`**
- 가장 위의 행을 삭제하고 다시 복구하는 경우, 가장 위에 삽입해야 함
- 가장 아래 행을 삭제하고 다시 복구하는 경우, 가장 아래에 삽입해야 함
  - 가장 위와 아래에 가상 공간을 둬서(배열 크기 + 2) 가장 위와 아래에 삽입할 때 사용하기!
    - 따라서, 기존에 0, 1, 2, ... 순서였다면 1, 2, 3, ... 이 순서로 인덱스를 생각해야 함

**`행 선택`**
- 얘네는 그냥 현재 위치를 나타내는 애들만 변경해주면 되니까!! 매우 쉬움


