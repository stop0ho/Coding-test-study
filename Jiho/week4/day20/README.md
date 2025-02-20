## 카드뭉치 풀이
`cards1`, `cards2`의 맨 처음에 원하는 단어가 있다면 `shift()`하는 간단한 방식

### 다른 코드 작성해보기

`shift()` 연산의 시간복잡도가 `O(n)`인데, 굳이 `O(n)`의 시간복잡도를 갖는 연산을 해가며 매번 원본 배열을 수정하고, 새로운 요소를 넣어줄 배열을 관리할 필요가 없지 않을까 생각했다.

```javascript
function solution(cards1, cards2, goal) {    
    let index1 = 0;
    let index2 = 0;
    
    for (const str of goal) {
        if (index1 < cards1.length && cards1[index1] === str) {
            index1++;
            continue;
        }
        if (index2 < cards2.length && cards2[index2] === str) {
            index2++;
            continue;
        }
        return "No"
    }
    
    return "Yes"
}
```

결과적으로 어느정도 유의미한 차이를 낼 수 있었음. 처음 코드는 O(n^2)이라 배열 크기가 커질수록 속도가 느려지는 반면에 두번째 코드는 `goal`을 순회하기만 하므로 시간복잡도가 `O(n)`으로 줄어들었다. 근데 사실 goal의 길이가 너무 작아서 `shift()`연산해도 풀이 자체에 문제는 없음.

#### 처음 코드
![](https://i.imgur.com/KgZKp65.png)

#### 수정 후 코드
![](https://i.imgur.com/jrfqXXh.png)