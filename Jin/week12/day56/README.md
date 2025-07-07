## 📌 문제 탐색하기

### 문제

길이가 N인 징검다리가 있을 때 a 위치에서 시작해서 b 위치에 도달할 수 있는 최소 횟수 구하기

### 조건

N: 징검다리의 개수 **1 ≤ N ≤ 10,000**

a: 시작 위치

b: 도착 위치

징검다리에 쓰여있는 정수는 N과 범위 동일

### 풀이 및 시간 복잡도 고려

BFS/DFS 시간 복잡도는 **O(V + E)**

- V: 정점 수 / E: 간선 수

  → V = N(각 위치가 정점)

  → E: 각 위치에서 갈 수 있는 다른 위치의 연결 선이므로, 입력 예시처럼 정점에 적혀있는 숫자가 1인 경우 모든 위치로 이동이 가능 ⇒ 최악의 경우 각 정점에서 **N-1개**의 간선을 갖게 됨 (자기 자신을 제외한 모든 곳)

  ∴ E = N(N - 1)


**O(V + E) = O(N + N^2) = O(N^2)**

→ 10,000 * 10,000 = **10^8이므로 2초 내로 문제 풀이 가능**

추가로 실제 코드로 작성한다면 방문 배열을 관리하여 탐색하는 것이 일반적이므로 **중복된 위치를 방문하지 않음** ⇒ 실제 연산 시간이 더 짧으므로 **충분히 풀이 가능**

위 문제는 위치 a에서 b까지 이동하는데 필요한 **최소 횟수**를 구하는 문제이므로 **BFS**로 접근

- 방문 배열을 만들어 이미 방문한 위치에 다시 방문하지 않게 하기
- 새로운 위치에 도달하면 이동 횟수를 증가
    - b 위치에 도달한 경우 종료

---

## 📌 코드 설계하기

1. 입력 저장
    - 징검다리 정보를 int형 배열로 저장
    - 시작 위치와 도착 위치 저장
2. BFS 초기 세팅
    - BFS에 사용할 큐, 방문 배열 생성
    - 큐에는 각 위치까지 점프 횟수 저장
    - 큐에 시작 위치인 a와 점프 횟수 0회를 배열로 저장
3. BFS 시작
4. BFS 종료 후 결과 반환
    - 도착 위치에 도달했다면 점프 횟수 반환, 도달하지 못했다면(b 위치에 도달하지 못하고 탐색이 종료된 경우) -1 반환

---

## 📌 시도 회차 수정 사항

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
            N = Integer.parseInt(br.readLine());
            int[] bridge = new int[N + 1];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                bridge[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 위치
            int b = Integer.parseInt(st.nextToken()); // 도착 위치
            
            int answer = BFS(a, b, bridge);
            
            bw.write(answer + "");
            bw.flush();
            bw.close();
            br.close();
        }
        
        private static int BFS(int a, int b, int[] bridge) {
            Queue<int[]> queue = new LinkedList<>();       // BFS 탐색에 사용할 큐 생성
            boolean[] visited = new boolean[N + 1]; // 방문 배열 생성
            
            queue.offer(new int[]{a, 0});
            visited[a] = true;
            
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int pos = current[0];
                int jumpCnt = current[1];
                
                // b 위치에 도달 시 점프횟수 반환
                if (pos == b) return jumpCnt;
                
                // 현재 위치에 작성된 수의 배수로만 뛸 수 있음
                for (int i = 1; pos + i * bridge[pos] <= N; i++) {
                    int nextPos = pos + i * bridge[pos];
                    if (!visited[nextPos]) {
                        queue.offer(new int[]{nextPos, jumpCnt + 1});
                    }
                }
            }
            
            return -1;
        }
    }
    ```

- 반례

    ```java
    // 입력
    3
    6 1 3
    2 1
    
    // 출력
    -1
    
    // 정답
    1
    ```

    - **1은 1의 배수가 맞음**에도 불구하고 **해당 위치로 점프하지 않음**

      → 코드에서 배수임을 확인하는 반복문에서 다음 점프할 위치를 오른쪽으로만(index가 더 큰 경우) 확인하고 있어서 위와 같이 왼쪽으로(index가 더 작은 경우) 점프해야 할 경우 오답이 생김

    ⇒ **현재 index보다 작은 위치도 확인**하게 변경하기


### 2회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            N = Integer.parseInt(br.readLine());
            int[] bridge = new int[N + 1];
    
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                bridge[i] = Integer.parseInt(st.nextToken());
            }
    
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 위치
            int b = Integer.parseInt(st.nextToken()); // 도착 위치
    
            int answer = BFS(a, b, bridge);
    
            bw.write(answer + "");
            bw.flush();
            bw.close();
            br.close();
        }
    
        private static int BFS(int a, int b, int[] bridge) {
            Queue<int[]> queue = new LinkedList<>(); // BFS 탐색에 사용할 큐 생성
            boolean[] visited = new boolean[N + 1]; // 방문 배열 생성
    
            queue.offer(new int[]{a, 0});
            visited[a] = true;
    
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int pos = current[0];
                int jumpCnt = current[1];
    
                // b 위치에 도달 시 점프횟수 반환
                if (pos == b) return jumpCnt;
    
                // 현재 위치에 작성된 수의 배수 index로만 뛸 수 있음
                for (int i = 1; pos + i * bridge[pos] <= N; i++) {
                    int nextPos = pos + i * bridge[pos];
                    if (!visited[nextPos]) {
                        queue.offer(new int[]{nextPos, jumpCnt + 1});
                    }
                }
    
                for (int i = 1; pos - i * bridge[pos] >= 1; i++) {
                    int nextPos = pos - i * bridge[pos];
                    if (!visited[nextPos]) {
                        queue.offer(new int[]{nextPos, jumpCnt + 1});
                    }
                }
            }
    
            return -1;
        }
    }
    ```

- 반례

    ```java
    // 입력
    10
    8 6 4 4 2 1 2 3 8 3
    8 4
    
    // 출력
    시간 초과
    
    // 정답
    -1
    ```

    - [testcase.ac](http://testcase.ac) 사이트에서 반례가 있는지 확인하던 중 **시간 초과** 발생

      (백준에서는 정답 처리가 되긴 했다.. 시간 초과가 날 만큼 움직이는 케이스가 없는 듯하다.)

      → visited 배열에 시작 위치인 a만 저장하고 그 이후에는 큐에 들어오는 위치들은 저장하지 않아서 시간초과 발생

    ⇒ visited 배열에 저장하는 로직 추가하기