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