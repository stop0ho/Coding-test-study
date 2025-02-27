## 2016년 문제

### 문제 풀이

- day28 문제 풀이하다가 제 달력 계산 실력에 큰 회의감을 느껴.. Lv1 하나 먼저 손풀기로 풀고, 다른 계산 문제 풀어보려고 선택했습니다. 놀려고 고른게 아니예요 ㅠㅠ
  - 큰 문제가 있다면 그냥 달력 문제를 못 풀어서 Lv1도 어렵게 느낀다는 거겠죠...

- 1월 1일이 금요일이니까 금요일부터 시작하는 배열 생성
- 전체 일수를 구해서 7로 나눠주면 무슨 요일인지 구할 수 있음!

## 방금 그곡 문제

### 문제 풀이

- 날짜 계산 말고, 시간 계산 문제 풀이!
- 재생된 시간만큼 반복해서 문자열 만들고, 그 문자열에 네오가 들은 거 있는지 검사하기
-> 일치하는 음악 여러 개일 경우, 재생된 시간 / 먼저 입력된 음악 제목 순으로 제목 반환
-> 괜찮은 생각인진 모르겠지만... 우선순위큐로 조건을 여러 번 비교해서 일치하는 음악, 재생된 시간, 입력된 순으로 비교를 하면 되지 않을까...

- 문제 풀이 중 발생한 오류....
  - 시간 계산하는데 20분 - 30분 같은 경우를 고려하지 않았다;; 마이너스 시간에 살 뻔했다..
  - '#' 음계 처리를 해주지 않았다.
    - 아니 # 처리해줬는데도 테스트 34번이 틀리길래 질문하기 들어가서 찾아봤는데... 문제에 없는 B#이 테스트케이스에 숨어있다는 것이다!!!!! 진짜 억울하다!

### 개선해보기
- 정답처리는 되었으나.. 시간이 너무너무 오래 걸리는 부분들이 있어 개선해보려고 claude에게 질문해보았다.

```java
    static class Melody implements Comparable<Melody> {
        int idx;            // 입력 순서
        String name;        // 노래 제목
        int playTime;       // 재생 시간
        boolean isPossible; // 조건 일치 여부

        public Melody(int idx, String name, int playTime, boolean isPossible) {
            this.idx = idx;
            this.name = name;
            this.playTime = playTime;
            this.isPossible = isPossible;
        }

        @Override
        public int compareTo(Melody o) {
            // 조건에 맞는 음악인지 비교
            if (this.isPossible && !o.isPossible) return -1;
            if (!this.isPossible && o.isPossible) return 1;

            // 재생 시간 비교 - 내림차순
            if (this.playTime != o.playTime) return o.playTime - this.playTime;

            // 입력 순서 비교 - 오름차순
            return this.idx - o.idx;
        }
    }
```
- 우선순위큐로 조건에 맞는 음악인지부터 시작해서.. 순서 정렬해서 하고 있었다.
  - 조건에 맞는 음악만 우선순위 큐에 넣어서 비교하면 되므로.. 우선순위 큐에 모든 노래를 넣을 필요가 없다! (진짜 맞는 말)
  - 나는 조건이 있으면 무조건 그 조건을 다 우선순위 큐에 넣는다거나.. 뭐 등등 그런 경향이 있는 것 같다. (근데 가끔은 왜 조건을 제대로 안읽는걸까?) 이렇게 필요한 조건만 딱딱 넣는 방향으로 바꿔보자 ㅠㅠ..
