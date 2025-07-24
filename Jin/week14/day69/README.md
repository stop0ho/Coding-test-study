## 📌 문제 탐색하기

### 문제

K명에게 최대한 많은 양의 막걸리를 분배할 수 있는 용량 구하기

- 주전자의 용량은 동일하나, 안에 들어 있는 막걸리 용량은 랜덤
- 분배 후 주전자에 막걸리가 조금 남아 있다면 막걸리 버림(다른 주전자와 합칠 수 없음)

### 조건

N: 막걸리 주전자의 개수 **1 ≤ N ≤ 10,000**

K: 사람 수 **1 ≤ K ≤ 1,000,000 /  N ≤ K**

**0 ≤ 막걸리 용량 ≤ 2^31 - 1(int형 최대 범위)**

### 풀이 및 시간 복잡도 고려

이분탐색으로 풀면 어떨까 싶었다.

전체 풀이 흐름은 다음과 같이 생각해보았다.

1. 최초 중간값은 **모든 막걸리 용량의 총합 / 사람 수**
    - start와 end 값은 입력받은 막걸리 용량 중 최소와 최대 저장해두기
2. 실제로 사람들에게 나누어 주기
    - 그냥 순서대로 막걸리 주전자에서 나눠주기
    - 사람에게 나누어줄 때마다 막걸리 받은 사람의 수를 증가
3. 탐색 위치 변경하기
    - 막걸리 받은 사람의 수가 K가 됐을 경우, 현재 값을 저장하고 우측 탐색 시작
    - 막걸리 받은 사람의 수가 K보다 작을 경우, 좌측 탐색 시작
4. 최적의 용량 출력

이런 식으로 풀이하면 시간복잡도는 다음과 같다.

- 이분탐색: O(log(막걸리 용량)) = O(log(2^31)) = 31
- 매 이분탐색마다 막걸리 나눠주기: O(N) = 10,000
    - 막걸리 주전자 개수를 전부 다 탐색해봐야하기 때문

⇒ **O(N * log(막걸리 용량))**의 시간복잡도를 가짐

10,000 * log(2 ^ 31) = 31 * 10,000 = **310,000이므로 1초 내로 문제 풀이 가능**

시간복잡도도 풀이 가능한 수준으로 나왔으니 자세한 풀이 짜보기

예제 입력을 확인했을 때 내 풀이에서 **말이 안되는 부분**이 있음

- **start와 end 값은 입력받은 막걸리 용량 중 최소와 최대 저장해두기**

→ 정말 웬만한 경우 전부 다 막걸리 용량의 최소보다 작다. 정말 몇몇 경우에서나 최소보다 크거나 같게 나올 것이다. (제일 작은 용량이 1이고, 제일 큰 용량이 2^31 - 1일 경우 제일 큰 거에서 다 나눠주면 되기 때문..)

⇒ start 값은 0으로, end 값을 제일 큰 용량으로 저장하기

⇒ 추가로, 어차피 이분탐색으로 찾을 거니까 최초 중간값 또한 늘 계산하던 (start + end) / 2로 하기!

또 생각해볼게, 막걸리 용량이 0부터 int 최대 범위까지이므로, 오버플로우가 일어날 가능성이 매우 높다.

⇒ **long으로 계산**

### 최종 풀이

1. start = 0, end = 입력받은 막걸리 중 최댓값으로 이분탐색 시작
2. 매 이분탐색마다 사람들에게 나눠보기
    - 배열 순서대로 막걸리 나눠주기

      **값이 0 이하가 된다면 빼지 말고 다음 주전자로 넘어가기**

    - 한번 뺄 때마다 **막걸리를 받은 사람 수 증가시키기**
3. 탐색 위치 변경하기
    - 막걸리 받은 사람 수 ≥ K
        - 용량을 더 늘려봐도 됨 → **start = mid + 1** / **현재 값이 최적일 수도 있으므로 값 저장**
    - 막걸리 받은 사람 수 < K
        - 용량을 줄여야 됨 → **end = mid - 1**
4. 정답 출력

---

## 📌 코드 설계하기

1. input 입력받기
2. 이분탐색 시작
    - start = 0, end = 입력받은 막걸리 용량의 최댓값
    - for 반복문으로 직접 사람들에게 나눠주기
        - 불필요한 탐색을 막기 위해 **막걸리 받은 사람 수가 K 이상이 되면 반복문 종료 후 탐색 위치 변경**
            - 막걸리 받은 사람 수 ≥ K ⇒ **start = mid + 1**
            - 막걸리 받은 사람 수 < K ⇒ **end = mid - 1**
3. 정답 출력

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
            int K = Integer.parseInt(st.nextToken());
            
            int[] volume = new int[N];
            int MAX_VALUE = 0;
            for (int i = 0; i < N; i++) {
                volume[i] = Integer.parseInt(br.readLine());
                MAX_VALUE = Math.max(MAX_VALUE, volume[i]);
            }
            
            // 2. 이분탐색 시작
            long start = 0, end = MAX_VALUE;
            long result = 0;
            while (start <= end) {
                long mid = (start + end) / 2;
                
                int count = 0;
                for (int i = 0; i < N; i++) {
                    long nowVol = volume[i];
                    while (nowVol - mid >= 0) {
                        nowVol = nowVol - mid;
                        count++;
                    }
                    
                    if (count >= K) break;
                }
                
                if (count >= K) {
                    result = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            
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
    1 1
    0
    
    // 출력
    시간 초과
    
    // 정답
    0
    ```

    - 아래 코드에서 무한 루프가 발생해 시간 초과가 됨
        - 0 - 0 ≥ 0이 항상 참이므로.. while문을 벗어나지 못함

    ```java
    for (int i = 0; i < N; i++) {
      long nowVol = volume[i];
      
      // 여기 while문이 무한 루프 발생
      while (nowVol - mid >= 0) {
          nowVol = nowVol - mid;
          count++;
      }
    
      if (count >= K) break;
    }
    ```

  ⇒ 뺄셈 이후 **nowVol = 0**이 된다면 break


### 2회차

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
            int K = Integer.parseInt(st.nextToken());
            
            int[] volume = new int[N];
            int MAX_VALUE = 0;
            for (int i = 0; i < N; i++) {
                volume[i] = Integer.parseInt(br.readLine());
                MAX_VALUE = Math.max(MAX_VALUE, volume[i]);
            }
            
            // 2. 이분탐색 시작
            long start = 0, end = MAX_VALUE;
            long result = 0;
            while (start <= end) {
                long mid = (start + end) / 2;
                
                int count = 0;
                for (int i = 0; i < N; i++) {
                    long nowVol = volume[i];
                    while (nowVol - mid >= 0) {
                        nowVol = nowVol - mid;
                        count++;
                        
                        if (nowVol == 0) break;
                    }
                    
                    if (count >= K) break;
                }
                
                if (count >= K) {
                    result = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            
            bw.write(result + "");
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- **막걸리 용량 = 2^31 - 1, mid = 1인 경우가 최악의 경우**

  → while문이 2^31 - 1번 실행되므로 시간 초과 발생


⇒ 나누기 연산으로 사람 수 셀 수 있도록 변경

근데 왜 이렇게 풀이했는지 모르겠다.. 이분탐색마다 N번 반복할 거라고 시간복잡도도 다 계산했는데 왜 이런 빼기 연산을 했던거지? 조금 더 생각하면서 문제를 풀어야겠다..;;

### 3회차

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
            int K = Integer.parseInt(st.nextToken());
            
            int[] volume = new int[N];
            int MAX_VALUE = 0;
            for (int i = 0; i < N; i++) {
                volume[i] = Integer.parseInt(br.readLine());
                MAX_VALUE = Math.max(MAX_VALUE, volume[i]);
            }
            
            // 2. 이분탐색 시작
            long start = 0, end = MAX_VALUE;
            long result = 0;
            while (start <= end) {
                long mid = (start + end) / 2;
                
                long count = 0;
                for (int i = 0; i < N; i++) {
                    if (mid > 0) {
                        count += (volume[i] / mid);
                    }
                    
                    if (count >= K) break;
                }
                
                if (count >= K) {
                    result = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            
            bw.write(result + "");
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- **mid = 0인 경우 모든 사람들한테 다 나누어줄 수 있지만, 현재 코드에선 그걸 고려하지 못함**

⇒ mid가 0인 경우 따로 처리