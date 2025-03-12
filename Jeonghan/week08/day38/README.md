### 문제 분석
배열 탐색  

<br>

### 문제 조건
맨해튼 거리 2 내에 파티션이 없는 경우 응시자간 연결 불가  

<br>

### 문제 풀이
응시자 위치에서 맨해튼 거리 2 내를 탐색  
파티션이 있다면 해당 경로 탐색 중지  
탐색 중 다른 응시자 발견시 탐색 종료  

- 사실 해당 문제는 맨해튼 거리가 굉장히 작기 때문에 굳이 탐색할 필요도 없음(응시자 사이에 특정 좌표만 확인)

```java
private static char[][] map;
    
public int[] solution(String[][] places) {
    int[] answer = { 1, 1, 1, 1, 1 };

    for (int i = 0; i < places.length; i++) {
        map = new char[5][5];
        List<int[]> ps = new ArrayList<>();
        
        for (int j = 0; j < 5; j++) {
            char[] temp = places[i][j].toCharArray();
            for (int k = 0; k < 5; k++) {
                map[j][k] = temp[k];
                if (map[j][k] == 'P') {
                    ps.add(new int[] { j, k });
                }
            }
        }
        
        for (int j = 0; j < ps.size(); j++) {
            int[] p1 = ps.get(j);
            for (int k = j + 1; k < ps.size(); k++) {
                int[] p2 = ps.get(k);
                int dx = Math.abs(p1[0] - p2[0]);
                int dy = Math.abs(p1[1] - p2[1]);
                
                if (dx + dy > 2) {
                    continue;
                } else if (dx == dy) {
                    answer[i] = (map[p1[0]][p2[1]] != 'X' || map[p2[0]][p1[1]] != 'X') ? 0 : 1;
                } else if (map[(p1[0] + p2[0]) / 2][(p1[1] + p2[1]) / 2] != 'X') {
                    answer[i] = 0;
                }
                if (answer[i] == 0) {
                    break;
                }
            }
            if (answer[i] == 0) {
                break;
            }
        }
    }
    return answer;
}
```
