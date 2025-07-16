## 📌 문제 탐색하기

### 문제

명령어를 따라 움직이는 로봇의 최종 위치 구하기

- **(0, 0) - (M, M)**
- 시작 방향은 **동쪽**
- 회전 방향
    - **0 왼쪽 90도**
    - **1 오른쪽 90도**
- 모든 명령어 열이 유효하다면 로봇의 최종 x, y 좌표를, 유효하지 않다면 -1 출력
    - **로봇이 범위 밖으로 나가면** 명령어 열이 **유효하지 않다**고 판단

### 조건

M: 정사각형 S의 한 변의 길이 **1 ≤ M ≤ 1,000**

n: 수행할 명령어 개수 **1 ≤ n ≤ 1,000**

명령어

- TURN dir **dir = 0 또는 dir = 1**
- MOVE d **1 ≤ d ≤ 1,000**

### 풀이 및 시간 복잡도 고려

구현 + 시뮬레이션 문제이기 때문에 BFS 탐색이나 DP 점화식 같은 거 없이 시키는 대로 구현해보기

1. TURN
    - 왼쪽 또는 오른쪽으로 90도 회전하는 명령어가 존재하므로, 회전 방향을 고려해 dx, dy 배열을 만들기

      동쪽에서 시작해 **왼쪽**으로 90도씩 회전: **동 → 북 → 서 → 남**

      동쪽에서 시작해 **오른쪽**으로 90도씩 회전: **동 → 남 → 서 → 북**

      [동, 북, 서, 남] 순서로 배열을 만들고, index를 조절해 회전

        - 왼쪽으로 이동할 땐 **`(index + 1) % 4`**
        - 오른쪽으로 이동할 땐 **`(index - 1) % 4` (단, index = 0인 경우에는 3으로 변경)**

          ⇒ 이렇게 나머지 연산으로 index를 관리하면 index = 0일 때 오른쪽으로 회전하는 경우를 제외하고 따로 처리해줄 것이 없음!

2. MOVE
    - dx, dy 배열의 index 위치에 맞게 방향이 설정되어 있으니 그 방향에 맞게 d 만큼 이동
3. 이동 위치가 유효한 열인지
    - **`isPossiblePosition`** 메서드를 만들어 범위가 **0 ≤ x ≤ M, 0 ≤ y ≤ M 를 만족**하는지 확인

      ⇒ 만족한다면 유효한 열이므로 해당 위치로 이동하고, 만족하지 않는다면 유효하지 않은 열이므로 반복문 종료 후 -1 반환


방향 전환이나 좌표값 이동에는 **O(1)**의 시간복잡도를 가짐

M X M 크기의 이차원 배열을 생성해서 탐색하는 과정 없이 명령어 n개만 전부 수행하면 되는 문제이므로 **O(n)**의 시간복잡도를 가짐

→ n의 최댓값은 **1,000이므로 1초 내에 문제 풀이 가능**

---

## 📌 코드 설계하기

1. input 입력받기
2. 명령어 구현하기
    - **turn**
        - dx, dy 배열에 사용할 index 값 변경용
        - **0** (왼쪽) - **`(index + 1) % 4`**
        - **1** (오른쪽) - **`(index - 1) % 4` (단, index = 0인 경우에는 3으로 변경)**
    - **move**
        - index가 방향에 맞게 설정되어있으므로, 해당 방향으로 d 만큼 이동

          → 이동할 위치(nextX, nextY)가 유효한지 **`isPossiblePosition`** 메서드로 판단

          유효하다면 이동, 유효하지 않다면 반복문 종료

3. 명령어를 모두 만족한다면 최종 x, y 위치 출력, 만족하지 못한다면 -1 출력

---

## 📌 시도 회차 수정 사항

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int[] dx = { 1, 0, -1, 0 };
        static int[] dy = { 0, 1, 0, -1 };
        
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 1. input 입력받기
            int M = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            
            // 초기 좌표 및 방향(동쪽)
            int x = 0, y = 0;
            int index = 0;
            
            boolean isCompleted = true;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int d = Integer.parseInt(st.nextToken());
                
                if (cmd.equals("TURN")) {
                    index = turn(index, d);
                } else {
                    // 해당 방향으로 d만큼 이동
                    int nextX = x + d * dx[index];
                    int nextY = y + d * dy[index];
                    
                    if (!isPossiblePosition(nextX, nextY, M)) {
                        isCompleted = false;
                        break;
                    }
                    x = nextX;
                    y = nextY;
                }
            }
            
            if (isCompleted) bw.write(x + " " + y);
            else bw.write("-1");
            
            bw.flush();
            bw.close();
            br.close();
        }
        
        private static int turn(int index, int dir) {
            if (dir == 0) {
                return (index + 1) % 4;
            } else {
                if (index == 0) return 3;
                return (index - 1) % 4;
            }
        }
        
        //
        private static boolean isPossiblePosition(int x, int y, int M) {
            return (0 <= x && x <= M) && (0 <= y && y <= M);
        }
    }
    ```

- 오른쪽 회전 방향 코드 수정
    - **`(index -1 + 4) % 4`** 로 0을 따로 처리하지 않고 코드를 수정할 수 있음

      → **- 1** 연산으로 오른쪽 방향으로 90도 회전을 구현가능

            **+ 4** 연산으로 음수 계산을 방지