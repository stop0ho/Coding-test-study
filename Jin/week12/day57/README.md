## 📌 문제 탐색하기

### 문제

지도 내 섬의 개수 세기

- 가로, 세로, 대각선으로 연결되어있으면 하나의 섬으로 간주

### 조건

ｗ: 지도의 너비 **1 ≤ N ≤ 50**

ｈ: 지도의 높이 **1 ≤ M ≤ 50**

지도 내부 값에서 **1은 땅, 0은 바다**

입력 종료 조건: **0 0** 입력

### 풀이 및 시간 복잡도 고려

1. 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
    - 한 지점에서 총 8방향으로 탐색해 연결되어 있는 사각형이 있는지 확인
    - 한 지점에서 연결된 섬이라면 하나의 섬이기 때문에 **DFS로 연결된 영역을 한번에 탐색**하는 것이 자연스러울 것이라 판단
        - 방문 여부를 관리해 한번 발견한 섬은 다시 탐색하지 않도록 처리 필요
2. 지도 밖으로 나갈 수 없다.
    - 지도를 2차원 배열로 만들어서 저장할 예정인데, 8방향으로 탐색 시 지도 밖(index가 0 이하 혹은 w/h 이상이 될 경우)으로 나가는 경우가 존재할 수 있음 → 2차원 배열 내부가 맞는지 확인 필요

DFS 시간 복잡도는 **O(V + E)**

- 정점의 수 **V = w * h** (2차원 배열의 한 칸이 전부 다 정점)
- 간선의 수 E: 각 칸마다 최대 8개의 간선이 발생 **E ≤ 8V**

**O(V + E) = O(w * h + w * h) = O(w * h)**

→ 50 * 50 = **2,500이므로 1초 내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. 문제 input을 입력 받음
    - while 반복문으로 입력된 값이 **0 0** 이 아닐 때까지 반복
2. w와 h 값에 맞게 2차원 배열 생성
3. 2차원 배열을 **DFS 탐색**하며 섬의 개수 세기
    - **따로 방문 배열을 만들지 않고**, 섬에 도착한다면 **해당 위치를 0으로 바꾸어 다음 탐색 때는 방문하지 않도록 하기**
    - 8방향 탐색 시 **지도 밖으로 벗어나지 않도록 범위 확인**하기
4. 탐색 종료 시 결과 반환

### ❓ 궁금한 점

이번 코드를 설계할 때는 방문 배열을 만들지 않고 관리했습니다.

문제 종류에 따라 방문 배열을 꼭 생성하지 않고도 다양한 방식으로 풀이가 가능하다는 것은 알고 있지만, 어떻게 보면 지도 데이터라는 원본 데이터의 변형이 일어나는 것인데 이렇게 풀이해도 좋을까요?

아니면 방문 배열을 만들어서 풀이하는게 안정성이 있으니 그렇게 하는 것이 좋을까요?

---

## 📌 시도 회차 수정 사항

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int[] dx = { 0, -1, 0, 1, -1, -1, 1, 1 };
        static int[] dy = { 1, 0, -1, 0, 1, -1, 1, -1 };
        
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
            // 1. input 입력
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int w = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
                
                // 1-1. 0 0 입력 시 종료
                if (w == 0 && h == 0) break;
                
                // 2. 2차원 배열 생성
                int[][] island = new int[w + 1][h + 1];
                
                int result = 0;
                // 탐색 시작
            }
        }
    }
    ```

- 2차원 배열 생성만 하고 값을 하나도 넣지 않고 탐색을 돌림
    - 생각보다 이런 실수를 좀 자주하는 거 같음. 물론 실행해보면 바로 알 수 있고 해결할 수 있는 문제이긴 하지만.. 좀 더 꼼꼼해질 필요가 있음

### 2회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int[] dx = { 0, -1, 0, 1, -1, -1, 1, 1 };
        static int[] dy = { 1, 0, -1, 0, 1, -1, 1, -1 };
    
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            // 1. input 입력
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int w = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
    
                // 1-1. 0 0 입력 시 종료
                if (w == 0 && h == 0) break;
    
                // 2. 2차원 배열 생성
                int[][] island = new int[w][h];
                for (int i = 0; i < w; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < h; j++) {
                        island[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
    
                int result = 0;
                // 3. 탐색하기
                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        // 섬인 경우 탐색 시작
                        if (island[i][j] == 1) {
                            result++;
                            DFS(island, i, j);
                        }
                    }
                }
    
                bw.write(result + "\n");
            }
    
            // 4. 결과 반환
            bw.flush();
            bw.close();
            br.close();
        }
    
        // 3-1. 8방향 탐색
        private static void DFS(int[][] island, int x, int y) {
            // 섬에 방문하면 0으로 변경
            island[x][y] = 0;
    
            for (int i = 0; i < 8; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
    
                // 지도를 벗어나지 않으면서 이동이 가능할 섬일 경우 계속 탐색 진행
                if (isPossiblePosition(island, nextX, nextY) && island[nextX][nextY] == 1) {
                    DFS(island, nextX, nextY);
                }
            }
        }
    
        // 3-2. 2차원 배열 내 범위가 맞는지 확인
        private static boolean isPossiblePosition(int[][] island, int x, int y) {
            int W = island.length;
            int H = island[0].length;
    
            return (0 <= x && x < W) && (0 <= y && y < H);
        }
    }
    ```

- 반례

    ```java
    // 입력
    4 6
    1 1 1 1
    1 1 1 1
    1 1 1 1
    1 1 1 1
    1 1 1 1
    1 1 1 1
    3 7
    1 1 1
    1 1 1
    1 1 1
    1 1 0
    0 0 1
    1 1 1
    1 0 0
    0 0
    
    // 출력
    런타임 에러
    
    // 정답
    1
    1
    ```

    - 입력이 3개 이상 들어왔음에도 2개 이하로 출력하거나, 위와 같이 런타임 에러가 발생하는 경우가 존재
        1. w와 h가 항상 같은 **정사각형 형태가 아님**
        2. 문제에서 **h개 줄에 걸쳐서** 지도가 주어진다고 함

      → 따라서 문제에 따르면 배열을 생성할 때 **`new int[h][w]`** 와 같은 형태로 생성해야 하는데, 그냥 아무 생각 없이 반대로 생성해서 위와 같은 에러가 발생

        - 지도 데이터인데 0 0 입력된 걸 보고 바로 종료하는 경우
        - 더 이상 들어올 입력이 없는데 읽으려고 해서 런타임 에러가 발생하는 경우

      ⇒ **w와 h 순서를 바꿔서 해결**