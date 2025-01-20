## 표 편집 문제 풀이
- 삭제, 복구가 관건임. 실제로 삭제, 복구 하면 그때마다 O(n)만큼의 연산이 일어난다. 이러면 아무리 열심히 코드를 짜도 절대 시간 효율성 테스트에 통과 못함.
- 그렇다면 어떻게 해야할까? 나는 **연결 리스트**를 사용했다. `prev`, `next`가 몇번인지를 저장해두고, 삭제/복구 연산이 일어날 때마다 해당 값들을 수정해주기만 하면 된다. 이렇게 될 경우 삭제, 복구 연산의 시간 복잡도가 **O(1)**이다.
- **복구**의 경우는 stack에 쌓는 방식으로 구현했다.