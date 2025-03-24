## 개인정보 수집 유효기간 문제

### 문제 풀이

- 개인정보 n개, 한 달은 28일까지 있음!! (달 계산 문제)
- 유효기간이 지나면 파기
- 단순히 개인정보 수집 일자 + 유효기간 해서 현재 날짜와 비교하면 될 듯..
  - 날짜 계산이 관건인 문제~~~

(근데 저는 날짜 계산 문제에 진짜 취약해요.ㅠ)
-> 바로 날짜 계산에 약한 취약점이 발병했습니다.
모든 날짜가 28일이라는 쉬운 조건(쉬운 조건이 맞나요?)에도 불구하고....
28일에서 한달이 지나도 28일이 되는 기적의 계산법으로 계산을 했습니다;;;

```java
for (int i = 0; i < privacies.length; i++) {
    String[] privArr = privacies[i].split(" ");
    String date = privArr[0];
    String term = privArr[1];
    int expMonth = termMap.get(term);

    int[] priv = Arrays.stream(date.split("\\."))
            .mapToInt(Integer::parseInt)
            .toArray();

    int totalMonth = priv[1] + expMonth;
    priv[0] += (totalMonth - 1) / 12;
    priv[1] = (totalMonth - 1) % 12 + 1;

    if (isExpired(todayArr, priv)) {
        result.add(i + 1);
    }
}
```
날짜 계산이 어쩐지 쉽더라구요..... ㅠㅠㅠㅠ 일은 생각도 안해도 되고 연/월만 생각해도 되는 문제인 줄 알았는데,
계속 테스트케이스 1번도 통과하지 못하길래 뭘까 싶고.. 진지하게 생각해도 계산을 애초에 틀려먹게 생각해서 절대 고쳐지지 않을 거 같아서 claude에게 물어봤습니다..
일 계산을 잘못했다고 하더라구요..ㅠㅠㅠ 클로드의 도움을 받아서, 전체 일수(모두 28일이니까 계산하기 쉬움)를 구해서 더하고 비교하는 방식으로 변경했습니다.
