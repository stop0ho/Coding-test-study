## 메뉴리뉴얼

### 문제 풀이
1. 조합을 이용해, 가능한 경우의 수를 모두 구하고 map에 넣는다.
- course[] 배열 값을 통해 원소의 개수 정한다. ex) course = {2, 3} 이라면 원소 크기가 2,3 인 조합 생성
2. map에 저장된 경우의 수와 course 개수를 비교해 해당하는 문자열을 ans 리스트에 저장한다.
3. ans 리스트를 String[] 배열로 반환한다.