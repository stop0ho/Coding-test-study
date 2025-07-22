## 📌 문제 탐색하기

### 선분 위의 점 문제

각 선분 위에 입력으로 주어진 점이 몇 개 있는지 구하기

### 조건

N: 점의 개수 **1 ≤ N ≤ 100,000**

M: 선분의 개수 **1 ≤ M ≤ 100,000**

- 두 점이 **같은 좌표를 가지는 경우는 없음**
- **1 ≤ 좌표값 ≤ 1,000,000,000**
    - int 연산 가능이라는 소리

### 풀이 및 시간 복잡도 고려

이분탐색 문제

- 어제 문제처럼 “점의 좌표는 **오름차순으로 입력**된다” 같은 조건은 없으므로 **정렬하기**

  → 이분탐색 문제는 정렬된 상황에서 해야 함


![image.png](attachment:e7c3b5c6-72e4-4357-8785-1dd29318dbeb:image.png)

딱 이 그림처럼, 점의 좌표와 선분의 시작점과 끝점을 비교해 만족하는 좌표의 개수를 구하면 됨

- 1 10 - 1, 3, 10 총 3개
- 20 60 - 20, 30 총 2개
- 4 8 - X 0개

선분의 시작점과 끝점이 입력될 때마다 이분탐색을 수행하면서 입력으로 주어진 점이 몇 개 있는지 구하기

다음과 같은 경우의 수가 존재

1. 입력으로 주어진 점 < 선분의 시작점
    - **end = mid - 1로 변경**해서 더 큰 좌표에서 만족하는 점이 있는지 탐색
2. 입력으로 주어진 점 > 선분의 끝점
    - **start = mid + 1로 변경**해서 더 작은 좌표에서 만족하는 점이 있는지 탐색
3. 선분의 시작점 ≤ 입력으로 주어진 점 ≤ 선분의 끝점
    - 정답 개수 증가
    - 여기가 문제
        - 입력으로 주어진 점이 저 사잇값은 맞는데 **`1 10`** 과 **`20 60`** 예시처럼 서로 반대 방향에 원하는 값이 있는 경우가 존재..

여기서 고민이 생겨서 이 문제를 풀기 위해 꼭 알아야 하는 개념이 있는지 Claude에 질문해보았다.

![image.png](attachment:2f1facb0-988b-46fd-9855-98310917930e:image.png)

lower bound와 upper bound 개념

- **이분 탐색을 기반으로 경곗값을 찾는 알고리즘**
- lower bound
    - **특정 값의 시작 위치**를 찾는 알고리즘 (이 문제에서는 선분의 시작점)
- upper bound
    - **특정 값보다 처음으로 큰 값의 위치**를 찾는 알고리즘(선분의 끝점)

⇒ 이걸 이용해서 풀이하기!

- 이분 탐색을 두 번 진행해서 lower bound와 upper bound를 구한다면 이 값의 차가 우리가 구하는 점의 개수가 될 것!
    - 우리가 구한 bound 값이 배열의 index 값이니까!

1. lower bound
    - 점 < 선분 시작점
        - start = mid + 1
    - 점 ≥ 선분 시작점
        - end = mid - 1
2. upper bound
    - 점 > 선분 끝점
        - end = mid - 1
    - 점 ≤ 선분 끝점
        - start = mid + 1

각 선분별로 이분탐색 두 번씩(upper, lower 각각 한 번) 진행하므로, **O(2 * M * log(N))**의 시간복잡도를 가짐

→ M * log(N) = 100,000 * log(100,000) = **500,000이므로 1초 내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력 받기
    - point 배열에 점의 좌표 저장 후 **정렬**
2. 선분 입력마다 2번의 이분탐색 진행
    - lower bound, upper bound 구하기
3. **upper bound - lower bound**를 계산해 점이 몇 개 있는지 출력

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
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            // 1. input 입력받기
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
    
            // 1-1. point 배열에 점의 좌표 저장
            int[] point = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                point[i] = Integer.parseInt(st.nextToken());
            }
    
            // 2. 선분 입력마다 2번의 이분탐색 진행
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());
    
                int start = 0, end = N - 1;
    
                // upper bound 구하기
                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (point[mid] > p2) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                }
                int upperBound = start;
    
                // lower bound 구하기
                start = 0;
                end = N - 1;
                while (start <= end)  {
                    int mid = (start + end) / 2;
                    if (point[mid] < p1) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
                int lowerBound = start;
    
                bw.write((upperBound - lowerBound) + "\n");
            }
    
            bw.flush();
            bw.close();
            br.close();
        }
    } 
    ```

- point 배열을 정렬하지 않음
    - 분명 위에서 정렬을 해야 한다고 썼는데 정렬하는 코드를 작성하지 않았다;;

  ⇒ **point 배열 정렬**


---

## 📌 문제 탐색하기

### 어두운 굴다리 문제

굴다리를 모두 비추기 위한 가로등의 최소 높이 구하기

- **가로등의 높이는 모두 같음**
- 가로등의 높이 **H** → **왼쪽으로 H, 오른쪽으로 H만큼 비춤**

### 조건

N: 굴다리의 길이 **1 ≤ N ≤ 100,000**

M: 가로등의 개수 **1 ≤ M ≤ N**

x: 가로등의 위치 **0 ≤ x ≤ N**

- 오름차순으로 입력
- 가로등의 개수만큼 들어옴

### 풀이 및 시간 복잡도 고려

1. 가로등의 높이를 1부터 시작해서, 모든 칸을 다 덮을 때까지 반복하기

→ N = 100,000 / M = 1일 때는 높이가 대충 50,000이어야지 가능함.. 50,000 * 100,000번 반복하니까.. **5 * 10^9 이므로 1초 내로 문제 풀이 불가능**

1. 이분탐색

   ![image.png](attachment:adbaf6ec-7307-4d5c-95ae-203a5a7712ae:image.png)

    - **첫 번째 가로등**은 무조건 **0번 길을 밝혀야 함**
    - **마지막 가로등**은 무조건 **N번 길을 밝혀야 함**
        - 내가 이걸 배열처럼 그려서 틀렸던 것..! N - 1번이 아니라 N번을 밝혀줘야 함

   → **두 조건 중 하나라도 만족하지 못하면 모든 길을 밝힐 수 없으므로, 높이를 올려야 함**

    - 가로등과 가로등 사이는 **앞 쪽 가로등 + H ≥ 뒤 쪽 가로등 - H** 조건을 만족해야 함

   → 최소한 같은 지점을 공유해야 그 사이는 전부 다 밝힐 수 있다는 것이니까. 넘어가도 상관은 없음!

   그렇다면 높이 H를 어떻게 설정할 수 있을까?

    - 여기서 **이분탐색**을 사용!
        - 굴다리의 길이 절반만큼부터 시작해서, 부족하다면 높이를 늘리고 만족한다면 높이를 줄이면서 최적의 높이를 찾아가기

      → **start = 0, end = N 으로 둔 후, H = (start + end) / 2로 시작**

      이후에 H 값을 변경하면서 목표값을 찾으면 됨


이분탐색의 시간복잡도 **O($log_2(N)$)**

- 모든 가로등이 굴다리의 길이를 밝힐 수 있는지 확인하는 과정(가로등의 개수만큼 반복)이 필요

→ **O(log_2(N) * M)**

→ log_2(100,000) * 100,000 ≈ **1,700,000이므로 1초 내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력 받기
2. 이분탐색
    - 높이가 H일 때 모든 길을 밝힐 수 있는지 확인
        - **모든 길을 밝히지 못한다면 높이가 부족**한 것이므로 높이를 증가(start = H + 1)
            - 이 과정에서 **최솟값을 계속 업데이트 해줘야 함**!! 현재 시점이 최솟값일 수 있으니까
        - **모든 길을 밝힌다면 높이를 줄여 최솟값 탐색**(end = H - 1)
3. 최솟값 반환

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
    
            // 1. 입력받기
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            
            int[] pos = new int[M];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pos[i] = Integer.parseInt(st.nextToken());
            }
            
            // 2. 이분탐색
            int start = 0, end = N;
            int answer = 0;
            while (start <= end) {
                int H = (start + end) / 2;
                
                if (isPossibleHeight(pos, H, N)) {
                    // 2-1. 모든 길을 밝힌다면 높이를 줄여 최솟값 탐색
                    answer = H; // 현재값이 최솟값일 수 있으니 가능할 때마다 값 업데이트
                    end = H - 1;
                } else {
                    // 2-2. 모든 길이 밝혀지지 않는다면 높이를 증가시켜 재탐색
                    start = H + 1;
                }
            }
            
            bw.write(answer + "");
            bw.flush();
            bw.close();
            br.close();
        }
        
        private static boolean isPossibleHeight(int[] pos, int H, int N) {
            // 0번 길을 밝힐 수 없으면 불가능한 높이
            if (pos[0] - H > 0) return false;
            
            // N - 1번 길을 밝힐 수 없으면 불가능한 높이
            if (pos[pos.length - 1] + H < N - 1) return false;
            
            for (int i = 0; i < pos.length - 2; i++) {
                // 가로등 사이 모든 길이 밝혀지지 않으면 불가능한 높이
                if (pos[i] + H < pos[i + 1] - H) return false;
            }
            
            // 모든 길이 밝혀지면 true 반환
            return true;
        }
    }
    ```

- 위의 그림을 배열처럼 그려서 발생한 문제
    - 문제에서는 **N까지 번호를 매겨야지만 풀 수 있게 되어있다**!!! **범위를 잘 확인**할 것!

  ⇒ **N - 1을 N으로 변경**해서 해결


---

## 📌 문제 탐색하기

### 나무 자르기 문제

M미터의 나무를 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값 구하기

- 나무의 높이가 **20, 15, 10, 17**이라고 할 때, **H = 15로 지정**한다면 **나무를 자른 뒤의 높이는 15, 15, 10, 15**가 되고 **총 7미터의 나무를 가져가게 됨**

### 조건

N: 나무의 수 **1 ≤ N ≤ 1,000,000**

M: 집으로 가져가려고 하는 나무의 길이 **1 ≤ M ≤ 2,000,000,000**

**0 ≤ N개의 나무의 높이 ≤ 1,000,000,000**

범위가 int형으로 고려한다고 했을 때 **충분히 오버플로우 될 수 있음**(10억 + 10억 + 10억 등)

⇒ **long 형으로 생각해보기**

### 풀이 및 시간 복잡도 고려

대략적인 풀이 흐름은 다음과 같을 것이다.

1. 절단기 설정 높이 H 구하기
2. 해당 높이대로 나무를 자름 (나무의 높이 - H ≤ 0인 경우는 자르지 않음)
3. 잘린 나무의 길이 총합에 따라 높이 H를 변경 후 원하는 길이가 나올 때까지 반복
    - 잘린 나무의 길이 총합 ≥ M
        - **나무의 길이가 더 짧아도 되므로 H를 증가시켜야 함**

          ⇒ **start = mid + 1**

        - 현재 길이가 최적일 수 있으므로 따로 저장
    - 잘린 나무의 길이 총합 < M
        - **나무의 길이가 더 길어야 하므로 H를 감소시켜야 함**

          ⇒ **end = mid - 1**


가장 중요한 절단기 높이를 어떻게 설정할까?

절단기 높이에서 가능한 높이는 다음과 같다.

- 최소 높이: 0
    - **모든 나무의 길이 총합**을 다 가져가는 것
- 최대 높이: 입력으로 들어온 나무 중 가장 긴 나무의 높이
    - **모든 나무를 자를 수 없음 = 0**

**start = 0, end = 나무의 높이 배열의 마지막 값**으로 설정한 후 위의 순서에 따라 이분탐색을 진행!

이분탐색에 사용되는 건 N개의 나무의 높이 → **O(log(나무의 최대 높이))**

매 이분탐색마다 모든 나무를 확인해 가져갈 수 있는 최대 높이를 구해야 함 → **O(N)**

⇒ **O(Nlog(나무의 최대 높이))**의 시간복잡도를 가짐

N * log(나무의 최대 높이) = 1,000,000 * log(1,000,000,000) = **9,000,000이므로 1초 내에 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력 받기
2. 이분탐색 진행
    - 최소 높이는 0, 최대 높이는 입력으로 들어온 나무의 최대 높이로 시작해서 이분탐색
    - 잘린 나무의 길이 총합에 따라 이분탐색 범위 조절
        - 잘린 나무의 길이 총합 ≥ M
            - start = mid + 1
        - 잘린 나무의 길이 총합 <  M
            - end = mid - 1
3. H 반환

---