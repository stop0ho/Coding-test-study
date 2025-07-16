## 📌 문제 탐색하기

### 자원 캐기 문제

왼쪽 위부터 오른쪽 아래까지 자원을 탐색할 때, 탐색할 수 있는 자원의 최대 숫자를 구하기

- 한 번에 오른쪽 또는 아래쪽으로 한 칸 이동할 수 있음
- 자원이 있는 경우에만 채취할 수 있음

### 조건

N: 세로 길이 **1 ≤ N ≤ 300**

M: 가로 길이 **1 ≤ M ≤ 300**

자원은 **1**, 빈 땅은 **0**으로 표시

### 풀이 및 시간 복잡도 고려

종료 지점에 도달했을 때 **자원의 개수가 최대**여야 함

- 오른쪽으로 한 칸 또는 아래쪽으로 한 칸씩 이동하며 시작지점에서 종료지점에 도달해야 함
    - 특정 지점에서 오른쪽 또는 아래쪽으로 이동했을 때 자원의 개수가 최대가 되도록 이동하기

<img width="761" height="347" alt="image" src="https://github.com/user-attachments/assets/eb8f7996-0c56-4235-a3db-68f41063e248" />

위 그림을 바탕으로 점화식은 아래와 같이 작성할 수 있다.

$$
dp[i][j] = dp[i][j] + max(dp[i - 1][j] + dp[i][j - 1])
$$

위처럼 한 칸씩 나아가며 어느 방향에서 올 때가 최댓값인지 구하는 방식은 **O(N * M)**의 시간복잡도를 가짐

→ 300 * 300 = **90,000이므로 2초 내에 문제 풀이 가능**

---

## 📌 코드 설계하기

1. 문제 input 입력 받기
2. (N + 1) * (M + 1) 크기의 2차원 배열 생성
    - 탐사 영역에 대한 정보는 (1, 1)부터 (N, M)까지 저장
    - 점화식을 위처럼 세웠기 때문에 행 또는 열의 index가 0인 부분을 따로 준비해 행 또는 열의 index가 1인 부분도 별도의 처리 없이 계산할 수 있게 함
3. 특정 위치에서의 최댓값을 구하며 (N, M)까지 반복
4. (N, M) 위치에 저장된 값 출력

---

## 📌 문제 탐색하기

### RGB 거리 문제

빨강, 초록, 파랑 중 하나의 색으로 1번 집부터 N번 집까지 색칠하기

- 1번 집 색 ≠ 2번 집 색
- N번 집 색 ≠ N-1번 집 색
- i번 집 색 ≠ i-1번 집 색 / i번 집 색 ≠ i+1번 집 색 (2 ≤ i ≤ N-1)

⇒ **인접한 집과 다른 색으로 칠하면서, 모든 집을 칠하는 비용이 최소가 되어야 함**

### 조건

N: 전체 집의 수 **2 ≤ N ≤ 1,000**

집을 R, G, B 중 하나의 색으로 칠하는 비용은 1,000 이하의 자연수

### 풀이 및 시간 복잡도 고려

집을 색칠할 때 고려해야 할 사항

- R, G, B 중 최소인 값을 골라서 칠한다고 **전체 결과가 무조건 최소가 되는 것이 아님**

  → 값이 최소이거나, 동일한 값이어도 선택한 색상에 따라서 뒤에 집 색에 영향을 미치기 때문에 전체 합이 최소가 아닐 가능성이 존재


⇒ dp 배열에 **각 선택의 결과에서 얻을 수 있는 최솟값을 저장**

<img width="1059" height="376" alt="image" src="https://github.com/user-attachments/assets/9d3d6e1c-31e6-4c8f-9583-24da6b030057" />

위 그림처럼 각 행별로 얻을 수 있는 최솟값을 구해서 dp 배열에 저장

<img width="743" height="383" alt="image" src="https://github.com/user-attachments/assets/e5e435db-2645-40b9-bf97-87cba10df6ea" />

최종적으로는 이렇게 만들어져서, **min(96, 172, 185) = 96**으로, **최솟값이 96**임을 알 수 있음

입력값 배열을 input이라 할 때 점화식은 아래와 같이 작성해볼 수 있다.

$$
dp[i][j] = input[i][j] + min(dp[i - 1][k]) (j ≠ k)
$$

j는 색상값! R, G, B 색상 순서대로 0, 1, 2의 인덱스 / j와 k는 0, 1, 2 중 하나이며 서로 같지 않음

배열의 크기가 N * 3개이고, 칸을 채우는 데에는 O(1)의 단순 연산만을 포함하므로, O(N * 3) = **O(N)**의 시간복잡도를 가짐

→ N의 최댓값이 **1,000이므로 0.5초 내에 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력 받기
    - input 배열 정보는 (1, 0)부터 (N + 1, 2)까지 저장
        - 어제와 마찬가지로 점화식을 위처럼 세웠기 때문에 행 또는 열의 index가 0인 부분을 따로 준비해 행 또는 열의 index가 1인 부분도 별도의 처리 없이 계산할 수 있게 하기 위함
2. dp 배열 생성
    - dp[i][j] = i번째 집에 j 색상을 칠했을 때의 최소 비용
3. N번째 행에서 최솟값 출력

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
    
            // 1. input 입력 받기
            int N = Integer.parseInt(br.readLine());
            
            int[][] input = new int[N + 1][3];
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 2. dp 배열 생성
            int[][] dp = new int[N + 1][3];
            
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        dp[i][j] = input[i][j] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                    } else if (j == 1) {
                        dp[i][j] = input[i][j] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                    } else {
                        dp[i][j] = input[i][j] + Math.min(dp[i - 1][0], dp[i - 1][1]);
                    }
                }
            }
            
            // 3. N번째 행에서 최솟값 출력
            int result = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
            bw.write(result + "");
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 틀리진 않았지만…
    - 제출 후 풀이를 확인하니 내가 2중 포문이 필요 없는데도 불구하고 사용했다는 걸 알아차렸다.

      Why? **j는 0부터 2까지 반복하는데, 0, 1, 2 모두 다른 점화식으로 동작**한다. (인접한 집의 색은 같은 색으로 칠할 수 없으므로)

      → 문제 풀이나 걸린 시간에 큰 영향을 줄 정도로 심각한 문제도 아니지만.. 깨닫고 나니 더 간결하고 명확해 보이는 코드로 너무 수정하고 싶었다…



---
## 📌 문제 탐색하기

### 내려가기 문제

내려가기 게임

- **N X 3** 행렬
- 다음 줄로 내려갈 때에는 바로 아래의 수 또는 아래의 수와 붙어 있는 수로만 이동 가능

  **i = 1 → 1 또는 2** / **i = 2 → 1, 2 또는 3** / **i = 3 → 2 또는 3**

- 칸에 주어진 숫자의 합으로 점수를 얻을 수 있을 때, 최대 점수와 최소 점수 구하기

### 조건

N: 줄의 개수 **1 ≤ N ≤ 100,000**

### 풀이 및 시간 복잡도 고려

어제 풀었던 [RGB 거리](https://www.notion.so/RGB-22e1aba2272880bd95bdc07b3098ecb8?pvs=21) 문제와 유사하게 풀이

- minDP / maxDP 와 같은 이름으로 dp 배열을 두 개 만들어서 각각에서 최솟값과 최댓값을 구하기!

<img width="1304" height="744" alt="image" src="https://github.com/user-attachments/assets/f9ab43cf-a709-4b6e-ad4f-7b055f1cdd9b" />

이런 식으로 배열의 열 위치에 맞게 최대/최소 점수를 구하는 DP 배열을 준비

점화식은 아래와 같이 작성할 수 있다.

```java
// minDP
minDP[i][0] = input[i][0] + min(minDP[i - 1][0], minDP[i - 1][1])
minDP[i][1] = input[i][1] + min(minDP[i - 1][0], minDP[i - 1][1], minDP[i - 1][2])
minDP[i][2] = input[i][2] + min(minDP[i - 1][1], minDP[i - 1][2])

// maxDP
maxDP[i][0] = input[i][0] + max(maxDP[i - 1][0], maxDP[i - 1][1])
maxDP[i][1] = input[i][1] + max(maxDP[i - 1][0], maxDP[i - 1][1], maxDP[i - 1][2])
maxDP[i][2] = input[i][2] + max(maxDP[i - 1][1], maxDP[i - 1][2])
```

배열의 총 칸 수는 N X 3개이므로 O(N * 3) = **O(N)**의 시간복잡도를 가짐

→ N의 최댓값이 **100,000이므로 1초 내에 문제 풀이 가능**

---

## 📌 코드 설계하기

1. 문제 input을 입력 받기
    - input 배열 정보를 **(1, 0)부터 (N + 1, 2)까지 저장**
        - **행이 0인 경우를 별도 처리 없이 계산할 수 있게 하기 위함**
2. dp 배열 두 개 생성
    - 최댓값과 최솟값을 구하기 위함
3. 각 배열의 N 번째 행에서 **최솟값과 최댓값을 구해 출력**

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
            int[][] input = new int[N + 1][3];
            
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 2. dp 배열 두 개 생성
            int[][] minDP = new int[N + 1][3];
            int[][] maxDP = new int[N + 1][3];
            
            for (int i = 1; i <= N; i++) {
                minDP[i][0] = input[i][0] + Math.min(minDP[i - 1][0], minDP[i - 1][1]);
                minDP[i][1] = input[i][1] + Math.min(Math.min(minDP[i - 1][0], minDP[i - 1][1]), minDP[i - 1][2]);
                minDP[i][2] = input[i][2] + Math.min(minDP[i - 1][1], minDP[i - 1][2]);
                
                maxDP[i][0] = input[i][0] + Math.max(maxDP[i - 1][0], maxDP[i - 1][1]);
                maxDP[i][1] = input[i][1] + Math.max(Math.max(maxDP[i - 1][0], maxDP[i - 1][1]), maxDP[i - 1][2]);
                maxDP[i][2] = input[i][2] + Math.max(maxDP[i - 1][1], maxDP[i - 1][2]);
            }
            
            int min = Math.min(Math.min(minDP[N][0], minDP[N][1]), minDP[N][2]);
            int max = Math.max(Math.max(maxDP[N][0], maxDP[N][1]), maxDP[N][2]);
            
            bw.write(max + " " + min);
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- Java언어
    - 문제가 골드 5라는게 믿기지 않을 정도로 어제와 풀이가 비슷했고 쉬웠다. 제출 후 풀이를 확인해보니 다른 언어와 달리 Java 언어였기에 메모리가 널널해서 쉽게 풀 수 있던 것이었다.

      문제에서 메모리 제한에 하단 참고 버튼을 누르면 딱 메모리 제한만 보여주기 때문에 알고리즘 분류를 보지 못하고 메모리 제한값만 보고 풀이를 해서 당연하고도 쉽게 문제 풀이를 했다.

    - 멘토님의 풀이와 문제에서 주어지는 알고리즘 분류를 읽으니 **슬라이딩 윈도우**를 사용해서 메모리를 아끼며 dp로 풀어야 하는 문제였다.

```java
// 2. dp 배열 4개 생성
int[] preMinDP = new int[3];
int[] preMaxDP = new int[3];
int[] nowMinDP = new int[3];
int[] nowMaxDP = new int[3];

for (int i = 1; i <= N; i++) {
    nowMinDP[0] = input[i][0] + Math.min(preMinDP[0], preMinDP[1]);
    nowMinDP[1] = input[i][1] + Math.min(Math.min(preMinDP[0], preMinDP[1]), preMinDP[2]);
    nowMinDP[2] = input[i][2] + Math.min(preMinDP[1], preMinDP[2]);

    nowMaxDP[0] = input[i][0] + Math.max(preMaxDP[0], preMaxDP[1]);
    nowMaxDP[1] = input[i][1] + Math.max(Math.max(preMaxDP[0], preMaxDP[1]), preMaxDP[2]);
    nowMaxDP[2] = input[i][2] + Math.max(preMaxDP[1], preMaxDP[2]);

    preMinDP = nowMinDP.clone();
    preMaxDP = nowMaxDP.clone();
}
```

- **dp 배열을 총 4개를 만들어 메모리를 절약**해보기
    - 원래 배열 2개에 공간복잡도 O(N * 3)이었는데, 배열 4개에 공간복잡도 O(3)으로 줄일 수 있음

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
            int[][] input = new int[N + 1][3];
    
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            // 2. dp 배열 4개 생성
            int[] minDP = new int[3];
            int[] maxDP = new int[3];
    
            for (int i = 1; i <= N; i++) {
                int min0 = minDP[0];
                int min1 = minDP[1];
                int min2 = minDP[2];
                minDP[0] = input[i][0] + Math.min(min0, min1);
                minDP[1] = input[i][1] + Math.min(Math.min(min0, min1), min2);
                minDP[2] = input[i][2] + Math.min(min1, min2);
    
                int max0 = maxDP[0];
                int max1 = maxDP[1];
                int max2 = maxDP[2];
                maxDP[0] = input[i][0] + Math.max(max0, max1);
                maxDP[1] = input[i][1] + Math.max(Math.max(max0, max1), max2);
                maxDP[2] = input[i][2] + Math.max(max1, max2);
            }
    
            int min = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);
            int max = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);
    
            bw.write(max + " " + min);
    
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- **배열 4개를 쓰던걸 2개로 줄여서 메모리 절약**해보기
    - **배열은 기존처럼 2개만 사용하되, min0, max2와 같은 임시 변수를 사용하기**

```java
// 2. dp 배열 4개 생성
int[] minDP = new int[3];
int[] maxDP = new int[3];

for (int i = 1; i <= N; i++) {
    int min0 = minDP[0];
    int min1 = minDP[1];
    int min2 = minDP[2];
    minDP[0] = input[i][0] + Math.min(min0, min1);
    minDP[1] = input[i][1] + Math.min(Math.min(min0, min1), min2);
    minDP[2] = input[i][2] + Math.min(min1, min2);

    int max0 = maxDP[0];
    int max1 = maxDP[1];
    int max2 = maxDP[2];
    maxDP[0] = input[i][0] + Math.max(max0, max1);
    maxDP[1] = input[i][1] + Math.max(Math.max(max0, max1), max2);
    maxDP[2] = input[i][2] + Math.max(max1, max2);
}
```

### 3회차

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
            int[][] input = new int[N + 1][3];
    
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            // 2. dp 배열 4개 생성
            int[] minDP = new int[3];
            int[] maxDP = new int[3];
    
            for (int i = 1; i <= N; i++) {
                int min0 = minDP[0];
                int min1 = minDP[1];
                int min2 = minDP[2];
                minDP[0] = input[i][0] + Math.min(min0, min1);
                minDP[1] = input[i][1] + Math.min(Math.min(min0, min1), min2);
                minDP[2] = input[i][2] + Math.min(min1, min2);
    
                int max0 = maxDP[0];
                int max1 = maxDP[1];
                int max2 = maxDP[2];
                maxDP[0] = input[i][0] + Math.max(max0, max1);
                maxDP[1] = input[i][1] + Math.max(Math.max(max0, max1), max2);
                maxDP[2] = input[i][2] + Math.max(max1, max2);
            }
    
            int min = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);
            int max = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);
    
            bw.write(max + " " + min);
    
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 입력 배열도 변경해보기

```java
// 1. input 입력받기
int N = Integer.parseInt(br.readLine());
int[] input = new int[3];

// 2. dp 배열 4개 생성
int[] minDP = new int[3];
int[] maxDP = new int[3];

for (int i = 0; i < N; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int j = 0; j < 3; j++) {
        input[j] = Integer.parseInt(st.nextToken());
    }
    
    int min0 = minDP[0];
    int min1 = minDP[1];
    int min2 = minDP[2];
    minDP[0] = input[0] + Math.min(min0, min1);
    minDP[1] = input[1] + Math.min(Math.min(min0, min1), min2);
    minDP[2] = input[2] + Math.min(min1, min2);

    int max0 = maxDP[0];
    int max1 = maxDP[1];
    int max2 = maxDP[2];
    maxDP[0] = input[0] + Math.max(max0, max1);
    maxDP[1] = input[1] + Math.max(Math.max(max0, max1), max2);
    maxDP[2] = input[2] + Math.max(max1, max2);
}
```

메모리 **52920 KB → 44032 KB** 개선 완료!

---
