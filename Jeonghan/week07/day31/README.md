### 문제 분석
문자열 탐색

<br>

### 문제 조건
문자열 최대 길이 1,000,000  
문자 배열 최대 길이 1,000,000  

<br>

### 문제 풀이
초기에는 `replaceAll` 메서드를 활용한 풀이 생각  
해당 방법은 시간 초과  

```java
private String solution(String s) {
    String result;
    int cnt = 0;
    
    while (true) {
        // replaceAll 메서드 사용시 시간 초과
        String temp = s.replaceAll("110", "");
        
        if (s.length() == temp.length()) {
            StringBuilder sb = new StringBuilder();
            int index = s.lastIndexOf("0") + 1;
            result = s.substring(0, index) + "110".repeat(cnt) + s.substring(index);
            break;
        }
        cnt += (s.length() - temp.length()) / 3;
        s = temp;
    }
    return result;
}
```

`replaceAll` 메서드를 `stack`을 활용한 방식으로 변경  

