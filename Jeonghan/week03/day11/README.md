### 문제 분석
가장 실패율이 높은 순으로 배열 반환  
실패율은 단계에 있는 유저를 상위 단계 유저 총합으로 나눈 값  
단순 완전 탐색으로 해결 가능  

<br>

### 문제 조건
스테이지 갯수는 1 ~ 500  
하지만 stages 길이가 최대 200,000 이기때문에 n^2 탐색은 불가  

<br>

### 문제 풀이
1. 각 단계 별로 몇몇의 유저가 있는지 탐색
2. 상위 단계부터 해당 단계 유저수와 그 위의 모든 유저수를 이용해서 비율 계산
3. 비율이 높은 순으로 배열 반환

<br>
