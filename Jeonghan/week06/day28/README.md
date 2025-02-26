### 문제 분석
날짜 비교  

<br>

### 문제 조건
날짜 포멧은 `yyyy.mm.dd`  
년도는 2000 ~ 2022  
달은 1 ~ 12  
일은 1 ~ 28  

<br>

### 문제 풀이
1. 오늘을 기준으로 각 약관의 경계 계산
2. 각 개인정보 별로 해당하는 약간의 경계와 날짜 비교


- 딱 1년전에 풀었던 기록이 있어서 가져왔습니다.
- 객체 지향적으로 지금보다 복잡하게 풀었더군요.

```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    
    private static final Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        int[] target = new int[3];
        setTarget(target, today);
        
        Arrays.stream(terms).forEach(t -> {
            String[] term = t.split(" ");
            map.put(term[0], Integer.parseInt(term[1]));
        });
        
        parseInput(privacies[0]);
        List<PrivateInfo> infos = Arrays.stream(privacies)
            .map(p -> new PrivateInfo(parseInput(p)))
            .collect(Collectors.toList());
        
        answer = calculate(target, infos);
        return answer;
    }
    
    private void setTarget(int[] target, String today) {
        String[] stringTarget = today.split("[.]");
        target[0] = Integer.parseInt(stringTarget[0]);
        target[1] = Integer.parseInt(stringTarget[1]);
        target[2] = Integer.parseInt(stringTarget[2]);
    }
    
    private String[] parseInput(String input) {
        String[] split = input.split("[.]");
        String[] sub = split[2].split(" ");
        return new String[] { split[0], split[1], sub[0], sub[1] };
    }
    
    private int[] calculate(int[] target, List<PrivateInfo> infos) {
        return IntStream.range(0, infos.size())
            .filter(i -> !infos.get(i).isOngoing(target))
            .map(i -> i + 1)
            .toArray();
    }
    
    private class PrivateInfo {
        
        private Integer year;
        private Integer month;
        private Integer day;
        private String category;
        
        private PrivateInfo(String[] input) {
            this.year = Integer.parseInt(input[0]);
            this.month = Integer.parseInt(input[1]) + map.get(input[3]);
            this.day = Integer.parseInt(input[2]);
            this.category = input[3];
            while (this.month > 12) {
                this.month -= 12;
                this.year += 1;
            }
        }
        
        private boolean isOngoing(int[] target) {
            if (this.year != target[0]) {
                return this.year > target[0];
            } else if (this.month != target[1]) {
                return this.month > target[1];
            }
            return this.day > target[2];
        }
    }
}
```
