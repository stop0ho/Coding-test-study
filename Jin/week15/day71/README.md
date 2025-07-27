## 📌 문제 탐색하기

### 점프 점프 문제

1 X N 크기의 미로의 가장 오른쪽 끝으로 갈 수 있는 최소 점프 횟수 출력

- 칸에 쓰여 있는 수 이하의 수만큼 오른쪽으로 떨어진 칸으로 점프할 수 있음
    - 3번째 칸에 3이 써져있다면 4, 5, 6번 칸 중 하나로 점프할 수 있음
- 가장 오른쪽 끝으로 갈 수 없는 경우 -1 출력

### 조건

N: 미로의 길이 **1 ≤ N ≤ 1,000**

$A_i$: 각 칸에 적혀있는 숫자  **0 ≤ $A_i$ ≤ 100**

### 풀이 및 시간 복잡도 고려

### 1회차 풀이

숫자 자체가 되게 작은 수들의 집합이라.. 그냥 완전탐색으로 풀 수 있지 않을까 하는 생각이 들었다.

미로의 길이는 최대 1,000이고, 각 칸마다 최대 100칸까지 점프할 수 있기 때문에 충분하지 않을까??

첫 번째 for문에서는 미로의 처음부터 끝까지 탐색하고, 내부 for문에서 해당 위치에서 갈 수 있는 지점까지 전부 확인해보기

이러면 **O(N * $A_i$)**의 시간복잡도를 가지는데, **1,000 * 100 = 100,000이므로 1초 내에 문제 풀이가 가능**하다.

최솟값으로 저장하는 것도 늘 하던 것처럼 방문한 칸이 처음 방문한 것이라면 바로 값을 저장하고, 아니라면 둘 중 최솟값을 저장하는 방식으로 전체 미로를 탐색하면 된다.

여기서 고려할 점은,

1. 배열의 범위를 벗어날 수 있음

   → 배열의 범위를 벗어나지 않는 선에서만 값을 업데이트 하면 됨. 넘어가는 순간부터 break해서 내부 for문을 탈출하면 된다.

2. 마지막 칸에 갈 수 없는 경우가 있음

   → 출력할 때 값이 0이라면 -1을 출력하게 하면 됨


### 2, 3회차 풀이

해당 풀이는 시도 회차 수정 사항을 먼저 작성하고, 정답 처리 이후 작성한 내용입니다!

시도 회차 수정 사항에도 작성한 것처럼, 모든 경우를 도는 완전탐색으로는 이 문제를 해결할 수 없다.

이유는 다음과 같다.

1. 첫 번째 칸이 0인 경우, N = 1인 경우를 제외하고는 어떤 경우에도 마지막 칸에 도달할 수 없음
2. 첫 번째 칸이 0이 아니어도, 중간에 길이 끊기는 경우가 존재

⇒ **첫 번째 칸을 제외하고** **memo[i] = 0이라면 어떤 경우에라도 해당 칸에 도달하지 못한 것**이므로, 이 칸에서 **추가 탐색을 진행할 수 없게 함**

마지막으로 N 값이 1부터 시작가능하므로, 시작하자마자 도착하는 경우가 있다.

- N = 1인 경우는 따로 처리해서 0을 반환할 수 있게 함

---

## 📌 코드 설계하기

1. input 입력받기
2. 완전탐색하며 모든 경우의 수를 구함
3. 마지막 칸 값 출력

---

## 📌 시도 회차 수정 사항

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            // 1. input 입력받기
            int N = Integer.parseInt(br.readLine());
            int[] maze = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                maze[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] memo = new int[N];
            for (int i = 0; i < N; i++) {
                int jump = maze[i];
                
                for (int j = i + 1; j <= i + jump; j++) {
                    // 배열의 인덱스를 벗어난다면 탐색 종료
                    if (j >= N) break;
                    
                    memo[j] = (memo[j] == 0) ? memo[i] + 1 : Math.min(memo[j], memo[i] + 1);
                }
            }
            
            int result = (memo[N - 1] != 0) ? memo[N - 1] : -1;
            bw.write(result + "");
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 반례

    ```java
    // 입력
    5
    0 0 0 1 0
    
    // 오답
    1
    
    // 정답
    -1
    ```

    - 첫 번째 칸에서 이동하지 못하는 경우, 어떠한 경우에도 마지막 칸에 도달할 수 없는데 그걸 고려하지 않았다.

      → 그럼 어딘가에서 길이 끊긴다면, 첫 번째 칸이 0이 아니어도 방문하지 못하는 경우가 있을 것이다.

      ⇒ **첫 번째 칸이 0인 경우는 탐색하지 못하게** 변경

      ⇒ **첫 번째 칸을 제외하고 `memo[i]` 값이 0인 경우는 탐색하지 않도록** 변경


### 2회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            // 1. input 입력받기
            int N = Integer.parseInt(br.readLine());
            int[] maze = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                maze[i] = Integer.parseInt(st.nextToken());
            }
            
            // 2. 탐색 시작
            int[] memo = new int[N];
            for (int i = 0; i < N; i++) {
                // 가장 첫번째 칸이 0이면 탐색을 진행할 필요가 없음
                if (i == 0 && maze[i] == 0) break;
                
                // 첫 번째 칸을 제외하고 memo[i]가 0이라면 방문하지 못하는 칸이므로 다음 칸 탐색
                if (i != 0 && memo[i] == 0) continue;
                
                int jump = maze[i];
                for (int j = i + 1; j <= i + jump; j++) {
                    // 배열의 인덱스를 벗어나는 경우 종료
                    if (j >= N) break;
                    
                    memo[j] = (memo[j] == 0) ? memo[i] + 1 : Math.min(memo[j], memo[i] + 1);
                }
            }
            
            // 3. 마지막 칸 값 출력
            int result = (memo[N - 1] != 0) ? memo[N - 1] : -1;
            bw.write(result + "");
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 반례

    ```java
    // 입력
    1
    0
    
    // 오답
    -1
    
    // 정답
    0
    ```

    - 첫 번째 칸이 마지막 칸인 경우, 시작하자마자 오른쪽 끝에 도달한 것이므로 0이 나와야 하는데, 이동 불가능인 -1이 나왔다.

      ⇒ **N = 1인 경우를 따로 처리**하도록 변경

      ⇒ 생각해보니, **첫 번째 칸을 제외하고 memo[i] 값이 0인 경우 탐색하지 않도록 하는 조건문이 추가**되어 **첫 번째 칸이 0인 경우에 대한 조건문은 필요 없음**

---

## 📌 문제 탐색하기

### 단축키 지정 문제

**N개의 옵션에 단축키를 의미하는 대표 알파벳 지정하기**

단축키를 지정하는 법은 아래의 순서를 따름

1. 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 **단어의 첫 글자가 이미 단축키로 지정되어 있는지** 살펴보기, **단축키로 지정되어 있지 않다면 그 알파벳을 단축키로 지정**
2. **모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보며 단축키로 지정 안된 것이 있다면 단축키로 지정**
3. 어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두고, **대소문자를 구분하지 않음**
4. 위의 규칙을 첫 번째 옵션부터 N번째 옵션까지 차례대로 적용

**단축키로 지정된 알파벳은 좌우에 대괄호를 씌워서 출력**

### 조건

N: 옵션의 개수 **1 ≤ N ≤ 30**

옵션을 나타내는 문자열: 5개 이하의 단어, 각 단어는 10개 이하의 알파벳 **1 ≤ 옵션 문자열 ≤ 50(공백 미포함)**

### 풀이 및 시간 복잡도 고려

30개의 옵션을 처음부터 끝까지 다 완전탐색한다고 치면, **O(N * 옵션 문자열 길이)**의 시간복잡도를 가짐

→ 30 * 50(공백 미포함) = **1,500이므로 2초 내에 문제 풀이 가능**

단축키를 지정해 대괄호를 씌워서 표현하는 방법

1. for문으로 최대 30개의 옵션을 순회

   → 각 옵션을 **`split` 메서드를 사용해 공백을 기준으로 분리** 후 단축키 지정 시작

2. 규칙에 맞게 탐색 후 출력 형식에 맞게 저장

   → 단어는 모두 **`toUpperCase`** 메서드를 써서 **대문자로만 판단**하기 (**대소문자를 구분하지 않음**)

   이미 단축키로 지정되어 있는 알파벳은 재지정할 수 없으므로 set 자료구조를 사용하기

    - split 메서드로 분리한 **단어들의 첫 글자 확인**
    - 첫 글자가 모두 단축키로 등록되어있다면, **두번째 글자부터 끝까지 확인**

      ⇒ 그냥 String 문자열 처음부터 끝까지 다 탐색하면 됨

      → 첫 글자는 어차피 Set에 저장할 수 없기 때문에 넘어가기 때문.. 어차피 공백 포함해도 54개 탐색하는 거라 큰 지장이 없음!!

    - **어떠한 것도 지정할 수 없다면 넘어가기**

      ⇒ 단축키로 등록할 수 있다면, **해당 알파벳 양 옆에 대괄호를 추가해 배열에 다시 저장**

      이거는 **substring 또는 StringBuilder**를 사용해 저장하기

3. 배열 순서대로 출력하기

---

## 📌 코드 설계하기

1. input 입력받기
2. 옵션을 순회하며 단축키 지정
    - set 자료구조를 사용해 단축키를 중복 없이 지정할 수 있도록 하기
    - split 메서드로 옵션을 단어 별로 분리 후, 단축키 지정하기
        - 조건에 맞게 단축키를 찾은 후, 대괄호를 붙여 저장
3. 배열 순서대로 출력

---

## 📌 시도 회차 수정 사항

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            // 1. input 입력받기
            int N = Integer.parseInt(br.readLine());
            
            String[] option = new String[N];
            for (int i = 0; i < N; i++) {
                option[i] = br.readLine();
            }
            
            // 2. 옵션을 순회하며 단축키 지정
            // 2-1. set 자료구조를 사용해 단축키를 중복 없이 지정할 수 있게 하기
            Set<Character> useAlphabet = new HashSet<>();
            
            for (int i = 0; i < N; i++) {
                // 2-2. split 메서드로 단어 별로 분리
                String o = option[i];
                String[] words = o.split(" ");
                
                boolean isPossibleAlphabet = false;
                int idx = -1;
                
                // 2-3. 단축키 지정하기
                for (int j = 0; !isPossibleAlphabet && j < words.length; j++) {
                    // 첫 글자가 단축키로 가능한지 탐색
                    char first = Character.toUpperCase(words[j].charAt(0));
                    
                    if (!useAlphabet.contains(first)) {
                        isPossibleAlphabet = true;
                        idx = 0;
                        useAlphabet.add(first);
                    }
                }
                
                for (int j = 0; !isPossibleAlphabet && j < o.length(); j++) {
                    // 두 번째 글자 이후부터 단축키로 가능한지 탐색
                    // = 어차피 첫 글자들은 if 조건문 통과 못하기 때문에 단어별로 처음부터 끝까지 확인하면 됨
                    char c = Character.toUpperCase(o.charAt(j));
                        
                    if (!useAlphabet.contains(c)) {
                        isPossibleAlphabet = true;
                        idx = j;
                        useAlphabet.add(c);
                    }
                }
                
                // 옵션에서 단축키가 등록된 경우, 대괄호를 붙여 저장
                if (isPossibleAlphabet) {
                    String result = "";
                    if (idx == 0) {
                        result = "[" + o.charAt(idx) + "]" + o.substring(idx + 1);
                    } else {
                        result = o.substring(0, idx) + "[" + o.charAt(idx) + "]" + o.substring(idx + 1);
                    }
                    
                    option[i] = result;
                }
            }
            
            for (int i = 0; i < N; i++) {
                bw.write(option[i] + "\n");
            }
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 반례

    ```java
    // 입력
    3
    rsek sovwf eg
    rLniu zlqpZPgAj yaDadvB sunyyLkZy
    ZwsGvvp
    
    // 출력
    [r]sek sovwf eg
    [r]Lniu zlqpZPgAj yaDadvB sunyyLkZy
    Z[w]sGvvp
    
    // 정답
    [r]sek sovwf eg 
    rLniu [z]lqpZPgAj yaDadvB sunyyLkZy 
    Z[w]sGvvp
    ```

    - 첫 글자가 단축키로 가능한지 탐색하는 구간에서 **idx를 0으로 저장해서 무조건 첫 번째 글자에 괄호가 씌워지는 상황**

      ⇒ 반복문이 돌 때마다 **단어의 길이를 더해 다음 단어의 idx 위치를 맞춰주기**


### 2회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            // 1. input 입력받기
            int N = Integer.parseInt(br.readLine());
            
            String[] option = new String[N];
            for (int i = 0; i < N; i++) {
                option[i] = br.readLine();
            }
            
            // 2. 옵션을 순회하며 단축키 지정
            // 2-1. set 자료구조를 사용해 단축키를 중복 없이 지정할 수 있게 하기
            Set<Character> useAlphabet = new HashSet<>();
            
            for (int i = 0; i < N; i++) {
                // 2-2. split 메서드로 단어 별로 분리
                String o = option[i];
                String[] words = o.split(" ");
                
                boolean isPossibleAlphabet = false;
                int idx = 0;
                
                // 2-3. 단축키 지정하기
                for (int j = 0; !isPossibleAlphabet && j < words.length; j++) {
                    // 첫 글자가 단축키로 가능한지 탐색
                    char first = Character.toUpperCase(words[j].charAt(0));
                    
                    if (!useAlphabet.contains(first)) {
                        isPossibleAlphabet = true;
                        useAlphabet.add(first);
                        break;
                    }
                    
                    // 다음 단어의 첫 번째 인덱스는 현재 단어의 길이 + 1(공백)
                    idx += words[j].length() + 1;
                }
                
                for (int j = 0; !isPossibleAlphabet && j < o.length(); j++) {
                    // 두 번째 글자 이후부터 단축키로 가능한지 탐색
                    // = 어차피 첫 글자들은 if 조건문 통과 못하기 때문에 단어별로 처음부터 끝까지 확인하면 됨
                    char c = Character.toUpperCase(o.charAt(j));
                        
                    if (!useAlphabet.contains(c)) {
                        isPossibleAlphabet = true;
                        idx = j;
                        useAlphabet.add(c);
                    }
                }
                
                // 옵션에서 단축키가 등록된 경우, 대괄호를 붙여 저장
                if (isPossibleAlphabet) {
                    String result = "";
                    if (idx == 0) {
                        result = "[" + o.charAt(idx) + "]" + o.substring(idx + 1);
                    } else {
                        result = o.substring(0, idx) + "[" + o.charAt(idx) + "]" + o.substring(idx + 1);
                    }
                    
                    option[i] = result;
                }
            }
            
            for (int i = 0; i < N; i++) {
                bw.write(option[i] + "\n");
            }
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 반례

    ```java
    // 입력
    18
    // 생략 ...
    lgN Lbn HuxRERw
    
    // 출력
    // 생략 ...
    lgN[ ]Lbn HuxRERw
    
    // 정답
    // 생략 ...
    lgN L[b]n HuxRERw 
    
    ```

    - 두 번째 글자 이후부터 탐색할 때 **띄어쓰기를 제외한 단어를 저장하는 걸 고려하지 않음**

      ⇒ **띄어쓰기가 아니면서, 저장되지 않은 단어를 찾도록 변경**

      조건문에 띄어쓰기가 아닌 걸 추가하거나, 애초에 Set 자료구조에 띄어쓰기를 넣고 시작해도 될 것 같다!