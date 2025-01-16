## 기지국 설치 문제

### 문제 풀이
기지국의 영향을 받고 있는 아파트를 표시하기 위한 boolean 타입의 배열(apart) 생성
- 기지국의 영향을 받고 있는 아파트는 true, 아닌 아파트를 false로 하기
apart 배열을 차례대로 순회하며 설치해야 하는 기지국의 개수 구하기
- 기지국이 커버할 수 있는 아파트의 수가 **`w * 2 + 1`** 개임을 이용(coverNum)
  - 양 옆 w칸 + 자기 위치
- 가장 처음으로 기지국의 영향을 받고 있는 아파트(true 값)를 만나기 전까지, 연속된 false 값의 개수를 셈(falseCnt)
  - true인 값을 만나거나 마지막 아파트일 경우, 기지국의 개수 구하기
    - falseCnt / coverNum 의 올림값이 필요한 기지국의 개수
```java
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 기지국의 영향을 받고 있는 아파트는 true, 아닌 아파트는 false
        boolean[] apart = new boolean[n + 1];
        for (int station : stations) {
            int first = Math.max(station - w, 1);
            int second = Math.min(station + w, n);

            for (int j = first; j <= second; j++) {
                apart[j] = true;
            }
        }

        // 기지국이 커버할 수 있는 아파트의 수 (자기 위치 + 양 옆 w칸만큼)
        int coverNum = w * 2 + 1;
        int falseCnt = 0;
        for (int i = 1; i < n + 1; i++) {
            if (!apart[i]) {
                falseCnt++;
            }

            if (falseCnt != 0 && (i == n || apart[i])) {
                answer += (int) Math.ceil((double) falseCnt / coverNum);
                falseCnt = 0;
            }
        }

        return answer;
    }
```

그런데.. 이렇게 풀면 정확성 테스트는 모두 통과하지만 효율성 테스트는 다 틀린다.. (시간 초과)
아무래도 아파트 개수(N, 최대 200,000,000)만큼 전부 다 순회해서 하나하나 다 세고 계산하고 하니까 시간 초과인 거 같다.

---
### 다시 풀어보기
생각해보니까 이 코드가 필요할까?? 
```java
        boolean[] apart = new boolean[n + 1];
        for (int station : stations) {
            int first = Math.max(station - w, 1);
            int second = Math.min(station + w, n);

            for (int j = first; j <= second; j++) {
                apart[j] = true;
            }
        }
```
굳이 true, false를 나누지 않고, 여기서 바로 기지국 개수를 세서 할 수 있지 않을까? 하는 생각이 들었다.

![KakaoTalk_20250116_210842158_02](https://github.com/user-attachments/assets/7627e295-134a-4c68-9aa3-38099a45474f)
![KakaoTalk_20250116_210842158_01](https://github.com/user-attachments/assets/0d386546-27c2-450b-be18-8d25b7db1bc3)


위에 풀이 사진을 요렇게~ 코드로 작성하면 통과한다!!
```java
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int coverNum = w * 2 + 1;

        // 처음부터 탐색해야 하니까
        int current = 1;
        for (int station : stations) {
            int uncovered = (station - w - 1) - current + 1;
            answer += (int) Math.ceil((double) uncovered / coverNum);

            current = station + w + 1;
        }

        if (current <= n) {
            int uncovered = n - current + 1;
            answer += (int) Math.ceil((double) uncovered / coverNum);
        }

        return answer;
    }
}
```
