## k진수에서 소수 개수 구하기

### 문제 풀이

- 10진수 n을 k진수로 변환하기 + k진수로 변환한 수에서 조건에 맞는 소수가 몇 갠지 확인하기
  - 101 처럼 자릿수에 0을 포함하지 않는 소수만 찾는거
-> 진수 변환 후 0으로 split해서 소수 찾기만 하면 되는 문제!

### 오답

```java
class Solution {
    static int answer = 0;
    
    public int solution(int n, int k) {
        // 진수 변환
        String convertNum = Integer.toString(n, k);
        String[] convertArr = convertNum.split("0");
        
        // 소수가 몇 개인지 확인하기
        check(convertArr);
        
        return answer;
    }
    
    private void check(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("")) continue;
            
            int number = Integer.parseInt(arr[i]);
            if (isPrime(number)) answer++;
        }
    }
    
    private boolean isPrime(int number) {
        // 1은 소수가 아님
        if (number == 1) return false;
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        
        return true;
    }
}
```

처음에는 이렇게 코드를 짰었는데.. 1번 11번이 런타임 에러가 나왔다.
그렇게 해서 질문하기 아무 거나 찾아봤는데 (진짜 아무거나 찾아봐서 코틀린이었나 파이썬이었음) 이게 n이 백만까지 들어와서.... 진수 변환하고 난 이후에 소수로 int 범위에서 벗어나는! 그런 친구가 들어올 수 있어서 그렇다는 것이었다...
항상 이런 사소하지만 중요한 걸 놓쳐서 런타임 에러같은게 뜬다.... ㅠ 좀 더 꼼꼼해질 필요가 있다.

int를 long으로 바꿔서 범위 초과되지 않게 바꾸니 정답이 됐다.