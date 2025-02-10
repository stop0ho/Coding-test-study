## 양과 늑대 풀이: 백트래킹
- [바킹독 백트래킹](https://blog.encrypted.gg/945)

### 처음의 잘못된 풀이
처음에는 아래와 같이 **단순히 트리를 BFS로 탐색**해서 `양의 수 > 늑대의 수` 조건을 만족하는지 여부로 답을 냈다. 그랬더니 정답이 아니었다. 특정 상태에서 다음 상태로의 선택을 고려하지 않고 있기 때문이다.

```javascript
function solution(info, edges) {
    let answer = 1;
    
    let n = info.length;
    const sheeps = [];
    
    // 트리 초기화
    const wolves = new Array(n).fill(0);
    const tree = Array.from({length: n}, () => []);
    
    for (const [parent, child] of edges) {
        tree[parent].push(child);
    }
    
    
    q = [0];
    
    while (q.length > 0) {
        const pNode = q.shift();
        const cNodes = tree[pNode];
        
        for (const child of cNodes) {
            q.push(child);
            // 늑대면 wolf에 +1
            if (info[child] === 1) {
                wolves[child] = wolves[pNode] + 1;
            } else {
                wolves[child] = wolves[pNode];
                sheeps.push(wolves[child]);
            }
        }
    }
    
    let currentSheeps = 1;
    console.log(sheeps)
    
    for (const count of sheeps) {
        console.log(count, currentSheeps)
        if (currentSheeps > count){
            answer++;
            currentSheeps++;
        }
    }
    
    return answer;
}
```
### 백트래킹
이 문제는 어떤 경로를 선택하여 이동할 때의 양의 수를 최대화 해야 하는 문제다. 따라서 **백트래킹**이 필요하다. 나는 백트래킹이 제일 약한데 꼭 양을 살려야할까? 늑대도 배가 고플텐데 그냥 늑대가 양을 먹으면 안되는걸까?

아무튼 1시간 넘게 문제를 잡고 있었는데 도무지 이해가 안 돼서 챗지피티한테 힌트를 얻었고, 그런데도 이해가 안 돼서 하나하나 그려봤다. 그리면서도 내가 진짜 바보 멍청이인것같아서 반성 또 반성했다.

![](https://i.imgur.com/H6NAWaa.png)

백트래킹은 단순히 **현재 상태를 끝내고 이전 상태로 돌아가서 다른 탐색지를 탐색하는 것**이다. 백트래킹은 좀 더 학습이 필요할 것 같다. ㅠ.ㅠ