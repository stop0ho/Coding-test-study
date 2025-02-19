### 문제 분석
각 호텔방 입력에 대해 그 값보다 같거나 큰 방 중에 빈방을 배정  
초기 아이디어는 계수 정렬을 이용한 이분 탐색이였지만 입력 최대값이 너무 큼  
트리셋을 이용한 이상값 찾는 방식으로 접근  

<br>

### 문제 조건
입력 최대값 1,000,000,000,000  
입력 길이 최대 200,000  

<br>

### 문제 풀이
1. 입력 배열 순회
2. 입력값이 set에 들어있다면 이미 배정완료된 방
3. 배정완료된 방인 경우 tset을 순회하면서 이상값 탐색
4. 탐색된 방 배정

<br>

배정된 방을 제거하는 로직의 위치에 따라 시간 초과 발생  
오답: 시간초과(87.9/100)

```java
for(int i = 0; i < room_number.length; i++){
    long room = room_number[i];
    while(set.contains(room)){
        room = tset.higher(room);
    }

    set.add(room);
    tset.remove(room);
    tset.add(room + 1);
    answer[i] = room;
}
```

<br>

입력은 꼭 방배정이 가능하게만 주어진다는 점을 이용해서 예외처리 로직 생략  
```java
// 해당 값이 없는 경우 null 발생
// 원래라면 Long 타입 캐스팅 및 null 처리 필수
room = tset.higher(room);
```
