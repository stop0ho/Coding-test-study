## 미로 탈출 명령어
### 처음 풀이(시간 초과)
처음에 다음과 같이 DFS로 문제를 풀었다.
```javascript
function solution(n, m, x, y, r, c, k) {
    let answer = [];
    
    let board = Array.from(Array(n), () => Array(m).fill('.'))
    
    board[x-1][y-1] = "S";
    board[r-1][c-1] = "E"
    
    const direction = {
        "l": [0, -1],
        "r": [0, 1],
        "u": [-1, 0],
        "d": [1, 0],
    }
    
    function dfs(str, x, y, depth) {
        if (depth === k) {
            if (x === r-1 && y === c-1) answer.push(str);
            return;
        }
        for (const key in direction) {
            const [dx, dy] = direction[key];
            if (0 <= x + dx && x + dx < n && 0 <= y + dy && y + dy < n) {
                dfs(str+key, x + dx, y + dy, depth + 1);
            }
        }
        
    }
    
    dfs("", x-1, y-1, 0);
    answer.sort()
    
    return answer.length === 0 ? "impossible":answer[0];
}
```
그랬더니 계속해서 시간초과가 났는데, 이유는 다음과 같다.
1. 정렬 연산(`answer.sort()`)는 탐색 경로의 개수만큼 시간 복잡도를 잡아먹는다.
2. 애초에 답이 될 수 없는 상황임에도 연산을 시작한다.
3. 현재 상황에서 때려죽여도 답을 만들 수 없는 경우에도 탐색을 계속한다.

이 문제는 단순 DFS가 아니라, **백트래킹**이 필요한 문제다. 모든 경로를 탐색하되, 불필요한 문제는 가지치기를 해나가야 하기 때문이다.

그리고 굳이 모든 답을 answer 배열에 넣어둘 필요가 없다. 애초에 사전순대로 움직이면서 탐색하면 되지않는가..!!!!

초기에 한 것처럼 `board` 배열을 초기화 할 필요도 없다. 저기에 있는 값 어차피 안 쓴다. ㅋㅋ

그래서 이 모든 것을 고친 것이 지금의 코드다. (챗지피티에게 검수를 맡긴 결과 변수명을 더 깔끔히 고쳐주었다.)

### `direction`을 객체가 아닌 배열로 사용해야 하는 이유
1. 객체는 `for ...in` 반복 시 순서가 보장되지 않는다.
2. 객체에서 순서를 강제하려면 추가적인 `Object.entries(direction).sort()`가 필요하다.