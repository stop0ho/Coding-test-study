## 📌 문제 탐색하기

### 문제

모든 트럭이 다리를 건너는 최단시간 구하기

- **트럭의 순서는 바꿀 수 없음**
- 트럭의 무게는 서로 다를 수 있음
- 다리 위에는 **w대의 트럭만 동시에 올라갈 수 있음**
- 트럭은 **1 단위 시간 당 1 단위 길이만큼 이동**할 수 있음
- **다리 위에 올라가 있는 트럭들의 무게의 합 ≤ 다리 최대 하중 L**

### 조건

n: 다리를 건너는 트럭의 수 **1 ≤ n ≤ 1,000**

w: 다리의 길이 **1 ≤ w ≤ 100**

L: 다리의 최대 하중 **10 ≤ L ≤ 1,000**

둘째 줄에는 n개의 트럭의 무게가 차례대로 주어짐

### 풀이 및 시간 복잡도 고려

모든 트럭이 다 지나갈 때까지 반복이 필요

- 입력된 순서대로 진행하되, 다음과 같은 조건을 만족해야 함
    - **다리 위에는 최대 w대의 트럭**이 올라갈 수 있음(무게가 남아도 더 올라갈 수 없음)
    - **다리 위에 올라가 있는 트럭들의 무게의 합 ≤ 다리 최대 하중 L**

우선 가장 먼저 모든 트럭을 다 다리에 올릴 때 사용할 반복문을 어떻게 할 지 고민해야 한다.

1. 배열 사용하기

    ```java
    // int[] truck = new int[]{ 7, 4, 5, 6 } 과 같이 값이 들어있음
    for (int i = 0; i < truck.length; i++) { ... }
    ```

   이런 식으로 한다고 했을 때, 위의 조건을 만족하지 못하는 경우에 처리가 복잡하다.

    - 시간은 무조건 1 단위 시간씩 흘러야 하는데, 그 상황에서 다리 위로 올라가는 조건을 만족하지 못하는 경우는 i -= 1 해주고.. 등등의 처리가 복잡할 거라 판단
2. ArrayList 사용하기

    ```java
    ArrayList<Integer> truck = new ArrayList<>();
    // 선언 후 입력의 두 번째 줄의 데이터가 저장됨
    while (!truck.isEmpty()) {
    	time++; // 시간은 무조건 1 단위 시간만큼 흐를 수 있음
    	// 조건에 맞다면 진행이 가능함
    }
    ```

   이런 식으로 배열을 사용했을 때 문제점은 해결이 가능하다.

    - 다리 위로 올라가는 조건 등을 만족했을 때는 **`truck.remove(0);`** 과 같은 코드를 짜서 가장 앞에 있는 원소를 빼 모든 트럭이 다 올라가는 거까지는 짜기가 쉽다!

      → 그런데… 어차피 가장 앞에 원소를 제거할 거면 **Queue**를 사용하는게 더 좋지 않을까? arraylist 삭제 연산(**`remove()`** 메서드) 시 시간복잡도가 **O(n)**이지만, queue는 **`poll()`** 메서드로 **O(1)** 밖에 걸리지 않는다.

3. Queue 사용하기
    - **선입선출**이기때문에 문제 조건에서 원하는 것처럼 트럭의 순서대로 다리 위로 보내기도 적합
    - **poll() 메서드**의 시간복잡도는 **O(1)**로 빠름
    - ArrayList처럼 다리 위로 올라가는 조건을 만족하지 못하는 경우에도 쉽게 처리가 가능

⇒ 전체적인 문제 풀이를 Queue로 짜보기!

1. 입력의 두 번째 줄에 들어오는 트럭의 무게를 Queue에 저장 (truck)
2. 다리 위에 올라가 있는 트럭의 정보를 새로운 Queue에 저장 (bridge)

→ **두 queue가 모두 비어있을 경우 = 모든 트럭이 다리를 지나는 경우(전체 반복문 종료 조건)**

반복문이 한 번 돌 때마다 1 단위 시간만큼 증가해 조건에 맞게 수행 후, 반복문이 종료되는 시점이 최소 시간!

다리 위에 올라올 수 있는 조건 고려하기

1. 다리 위에 최대 w대의 트럭
    - 위에서 다리 위에 올라가 있는 트럭의 정보를 queue에 저장

   → **queue.size() < w** 인 경우에만 가능

2. 다리 위 트럭들의 무게의 합 ≤ 다리 최대 하중 L
    - 다리 위에 트럭들의 무게의 합을 가지는 변수 하나 생성

   → **무게의 합 변수 + 새로 올라올 트럭의 무게 ≤ L** 인 경우에만 가능


다리에서 내려가는 조건 고려하기

1. 트럭은 w 단위 시간이 지나면 다리 위에서 내려오게 됨
    - 다리 위에 트럭이 있을 경우, 매 단위 시간마다 내려올 수 있는 트럭이 있는지 검사
        - 아직 도착하지 않았을 수도 있으니 이 경우 **peek() 메서드**로 가져와서 확인해보기!

      → 그렇다면 queue에 어떤 값이 저장되어 있어야 하나?

      **현재 시간 = 다리에 진입한 시간 + w**인 경우 다리에서 내려와야 함

      다리에서 내려온 경우, **무게를 빼주어야 함**

      ∴ 다리에 진입한 시간과 무게를 알고 있어야 함


주의

- **트럭이 내려가는 시간에 바로 새로운 트럭이 올라올 수 있음** (지문에 4번째 그림 참고 - 7 트럭이 내려가면서 4 트럭이 바로 올라옴)

  ⇒ 반복문 안에서 **트럭이 내려가는 걸 먼저 고려하고, 그 다음에 새로운 트럭이 올라올 수 있는지 체크**해야 함


위 풀이대로 풀이할 경우 시간복잡도 계산해보기

시간복잡도를 어떻게 계산해야 할 지 모르겠으니 하나하나 뜯어서 생각해보자..

- 최악의 경우: 모든 트럭의 무게가 다리 하중과 동일한 경우

  → 한 트럭이 내리기 전까지 다음 트럭이 탈 수 없음

  한 트럭 당 걸리는 시간은 w + 1 단위 시간이고, 총 n 대의 트럭이 이동 ⇒ **O(n X w)**

- **while 문 내부에 연산**은 peek(), poll(), 단순 덧셈/뺄셈 연산일테니 **O(1)** 소요

⇒ n * w = 1,000 * 100 = **10^5이므로 1초 내에 문제 풀이 가능**

---

## 📌 코드 설계하기

1. queue 두 개 생성
    - 입력에서 사용할 큐 (각 트럭의 무게)
    - 다리 위에서 사용할 큐 (각 트럭이 다리에 진입한 시간과 무게)
2. input 입력받기
3. 모든 트럭이 다리를 건널 때까지(두 queue가 모두 빌 때까지) 반복
    - 내려갈 수 있는 트럭 먼저 고려
    - 그 이후 새로운 트럭이 올라갈 수 있는지 고려
4. 걸린 시간 출력

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
            
            // 1. queue 두 개 생성
            Queue<Integer> truck = new LinkedList<>();
            Queue<int[]> bridge = new LinkedList<>(); // { 다리에 올라온 시간, 해당 트럭의 무게 }
            
            // 2. input 입력받기
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());   
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                truck.add(Integer.parseInt(st.nextToken()));
            }
            
            // 3. 모든 트럭이 다리를 건널 때까지 반복
            int time = 0; // 시간
            int totalWeight = 0; // 다리 위 트럭 무게의 합
            while (!truck.isEmpty() || !bridge.isEmpty()) {
                time++;
                
                // 트럭이 내려갈 수 있는지 먼저 고려
                if (!bridge.isEmpty()) {
                    int[] info = bridge.peek();
                    int enterTime = info[0];
                    int weight = info[1];
                    
                    // 다리를 다 건너갔을 경우
                    if (enterTime + w == time) {
                        bridge.poll(); // 값을 위에서 저장해서 빼는 용도로만 사용
                        totalWeight -= weight;
                    }
                }
                
                if (!truck.isEmpty()) {
                    int nowWeight = truck.peek();
                    // 다리 위에 올라가있는 트럭의 수와 무게가 조건에 부합하면 새로운 트럭을 올리기
                    if (bridge.size() < w && totalWeight + nowWeight <= L) {
                        truck.poll(); // 값을 위에서 저장해서 그냥 빼는 용도로만 사용
                        bridge.add(new int[]{time, nowWeight});
                        totalWeight += nowWeight;
                    }
                }
            }
            
            bw.write(time + "");
            
            bw.flush();
            bw.close();
            br.close();
        }
    }
    ```

- 코드를 좀 더 단순화할 수 있다.

  → 현재 내 코드에서는 트럭이 올라간 시점을 저장해 다 지나갔는지 여부와 다리 위에 올라가있는 트럭의 수 모두 직접 확인했는데.. 멘토님 풀이를 보고 깨달음을 얻었다.

    1. bridge 큐에 다리 길이만큼 0의 값을 넣어준다. (트럭이 지나가지 않으면 무게는 0이니까)
    2. 트럭이 올라갈 수 있는지 조건을 확인한다.
        - 올라갈 수 있다면 큐에 해당 트럭의 무게를 넣어준다.
        - 올라갈 수 없다면 큐에 0의 값을 넣어준다.

  저런 식으로 그냥 매 반복문이 돌 때마다 bridge.poll()을 해주면 따로 트럭이 올라간 시점을 알 필요도 없이 자동으로 빠지고, totalWeight도 그냥 계속 빼주면 신경 쓸 것이 없다. (0은 빼도 아무 문제 없으니)


---