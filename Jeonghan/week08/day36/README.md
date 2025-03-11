### 문제 분석 
모든 패턴에 대해 하나씩 아이디를 할당하면서 카운팅  
백트래킹을 활용한 문자열 탐색  

<br>

### 문제 조건
배열 최대 크기 8  
동일한 패턴존재로 인해 중복 목록 발생시 하나로 취급  

<br>

### 문제 풀이
배열 최대 크기가 작기 때문에 비트마스킹으로 중복 목록 처리  
패턴마다 하나씩 할당 가능한 아이디를 할당해가면서 카운팅  
기존에 구현했던 패턴 초기화 코드에서 정규표현식으로 변경  

```java
private void init() {
    for (int i = 0; i < user.length; i++) {
        for (int j = 0; j < ban.length; j++) {
            if (user[i].length() != ban[j].length()) {
                continue;
            }
            boolean finished = true;
            for (int k = 0; k < user[i].length(); k++) {
                if (ban[j].charAt(k) != '*' && user[i].charAt(k) != ban[j].charAt(k)) {
                    finished = false;
                    break;
                }
            }
            if (!finished) {
                continue;
            }
            matched[i][j] = true;
        }
    }
}
```
