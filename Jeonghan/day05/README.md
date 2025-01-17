### 문제 분석
커맨드에 따라 위치를 움직이면서 삭제/복원  
해당 인덱스들은 복원할때 복원되는 위치가 정해져 있음  

<br>

### 문제 조건
`U`, `D` 커맨드는 현재 위치를 움직이지만 데이터 변화 없음  
`C` 커맨드는 현재 위치도 움직이고 데이터 변화도 있음  
`Z` 커맨드는 현재 위치를 움직이지 않지만 데이터 변화 있음  


<br>

### 문제 풀이
1. 덱 자료구조 사용  
스택에는 삭제되기 전 덱 자료구조 상태를 스냅샷 형태로 저장  
하지만 해당 접근 방식 효율성 0점   

```
정확성: 30.0
효율성: 0.0
합계: 30.0 / 100.0
```

```java
class Solution {

    private final static Stack<Deque<Integer>> s = new Stack<>();
    private static Deque<Integer> dq = new ArrayDeque<>();

    public String solution(int n, int k, String[] cmd) {
        for (int i = k; i < n; i++) {
            dq.addLast(i);
        }
        for (int i = 0; i < k; i++) {
            dq.addLast(i);
        }

        for (String c : cmd) {
            String[] operation = c.split(" ");
            apply(operation);
        }

        char[] answer = new char[n];
        for (int i = 0; i < n; i++) {
            answer[i] = 'X';
        }
        while (!dq.isEmpty()) {
            answer[dq.pollFirst()] = 'O';
        }
        return String.valueOf(answer);
    }

    private void apply(String[] operation) {
        switch (operation[0]) {
            case "U":
                move(-Integer.parseInt(operation[1]));
                break;
            case "D":
                move(Integer.parseInt(operation[1]));
                break;
            case "C":
                delete();
                break;
            case "Z":
                undo();
                break;
        }
    }

    private void move(int cnt) {
        if (dq.size() == 0) {
            return;
        }
        while (cnt != 0) {
            if (cnt > 0) {
                dq.addLast(dq.pollFirst());
                cnt--;
            } else {
                dq.addFirst(dq.pollLast());
                cnt++;
            }
        }
    }

    private void delete() {
        if (dq.size() == 0) {
            return;
        }
        s.push(new LinkedList<>(dq));
        int deletedIndex = dq.pollFirst();
        if (!dq.isEmpty() && deletedIndex > dq.peekFirst()) {
            dq.addFirst(dq.pollLast());
        }
    }

    private void undo() {
        if (s.size() == 0) {
            return;
        }
        Deque<Integer> lastVersion = s.pop();
        int current = dq.peekFirst();
        while (lastVersion.peekFirst() != current) {
            lastVersion.addLast(lastVersion.pollFirst());
        }
        dq = lastVersion;
    }
}
```

<br>

2. 단순 배열 사용  
자료구조를 사용해서 탐색하는 것이 시간초과가 발생하기 때문에 포인터 방식으로 변경  
하지만 결국 다음 위치를 구하기 위해서는 배열 탐색 필요  

```
정확성: 30.0  
효율성: 35.0  
합계: 65.0 / 100.0  
```

```java
class Solution {

    private final static Stack<Integer> s = new Stack<>();
    private static char[] answer;
    private static int ptr;
    private static int size;
    private static int total;

    public String solution(int n, int k, String[] cmd) {
        answer = new char[n];
        for (int i = 0; i < n; i++) {
            answer[i] = 'O';
        }
        ptr = k;
        size = n;
        total = n;

        for (String c : cmd) {
            if (size == 0) {
                continue;
            }
            String[] operation = c.split(" ");
            apply(operation);
        }
        return String.valueOf(answer);
    }

    private void apply(String[] operation) {
        switch (operation[0]) {
            case "U":
                move(-Integer.parseInt(operation[1]));
                break;
            case "D":
                move(Integer.parseInt(operation[1]));
                break;
            case "C":
                delete();
                break;
            case "Z":
                undo();
                break;
        }
    }

    private void move(int cnt) {
        while (cnt != 0) {
            if (cnt > 0) {
                do {
                    ptr = (ptr + 1) % total;
                } while (answer[ptr] == 'X');
                cnt--;
            } else {
                do {
                    ptr = ptr == 0 ? total - 1 : ptr - 1;
                } while (answer[ptr] == 'X');
                cnt++;
            }
        }
    }

    private void delete() {
        if (size == 0) {
            return;
        }
        int deletedIndex = ptr;
        answer[deletedIndex] = 'X';
        s.push(deletedIndex);
        size--;

        if (size == 0) {
            return;
        }

        for (int i = ptr; i < total; i++) {
            if (answer[i] == 'O') {
                ptr = i;
                break;
            }
        }

        if (deletedIndex == ptr) {
            move(-1);
        }
    }

    private void undo() {
        if (size == total) {
            return;
        }
        answer[s.pop()] = 'O';
        size++;
    }
}
```

<br>

3. 최종 TreeSet 사용  
TreeSet 자체를 인터페이스로 감싸지 않고 구현체 자체를 사용  
별로 좋은 방법은 아니지만, 이때까지 풀이한 것 중 가장 효율적인 풀이  
정렬되어 있는 성질을 이용해서 해결하지 못했던 문제인 `탐색하지 않고 다음 위치를 구하는 것` 성공  

```
정확성: 30.0
효율성: 70.0
합계: 100.0 / 100.0
```

<br>
