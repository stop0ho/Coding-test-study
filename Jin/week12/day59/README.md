## 📌 문제 탐색하기

### 문제

그림이 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때의 구역 수를 구하기

### 조건

N: 한 변의 길이 **1 ≤ N ≤ 100**

상하좌우로 인접해 있는 경우에 같은 구역에 속한다고 함(**색상의 차이를 거의 느끼지 못하는 경우**도 같은 색상으로 판단 → **R = G로 판단**한다는 뜻)

적록색약이 **없는** 사람: R, G, B 총 **3가지 색으로 인식**

적록색약이 **있는** 사람: R = G, B 총 **2가지 색으로 인식**

### 풀이 및 시간 복잡도 고려

적록색약이 **있는** 경우와 **없는** 경우 **2번 탐색**해야 하므로, **방문 배열을 따로 만들어 관리**

1. 적록색약이 **없는** 경우
    - 현재 위치에서 인접한 칸을 탐색 → 같은 색인 칸만 하나의 구역으로 인식
    - 방문한 칸은 다시 방문하지 않도록 값 변경
2. 적록색약이 **있는** 경우
    - 없는 경우와 동일하지만, R과 G 색상의 경우는 동일한 색상으로 판단해 구역을 구함

DFS 시간 복잡도는 **O(V + E)**

- 정점의 수 **V = N * N** (2차원 배열의 한 칸이 전부 다 정점)
- 간선의 수 E: 각 칸마다 최대 4개의 간선이 발생 **E ≤ 4V**

**O(V + E) = O(N * N + N * N) = O(N^2)**

→ 100 * 100 = **10,000이므로 1초 내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. 문제 input을 입력 받음
2. N * N 크기의 2차원 배열에 그림 정보 저장하기
    - 같은 크기의 방문 배열 2개 준비
3. 적록색약 여부에 따라 2번 탐색
    - 간단한 구현을 위해 DFS 메서드를 2개 만들기
        - 적록색약이 **없는** 경우
            - 현재 방문한 칸이 인접한 칸의 색상이 **완전히 일치**할 때만 탐색
        - 적록색약이 **있는** 경우
            - 현재 칸과 인접한 칸의 색상이 다음과 같을 때에만 탐색
                - 둘 다 R 또는 G인 경우
                - 둘 다 B인 경우
4. 구역의 개수 출력

---

## 📌 시도 회차 수정 사항

오늘은 첫 시도 만에 바로 정답이긴 했으나, 코드가 전체적으로 복잡해 보여서 조금씩 수정해 보고 BFS 탐색으로도 짜보기!

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int[] dx = { 1, -1, 0, 0 };
        static int[] dy = { 0, 0, -1, 1 };
        
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
            // 1. input 입력
            int N = Integer.parseInt(br.readLine());
            
            // 2. 그림 정보 저장하기
            String[][] photo = new String[N][N];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    photo[i][j] = String.valueOf(input.charAt(j));
                }
            }
            
            // 2-1. 같은 크기의 방문 배열 2개 준비
            boolean[][] notRGVisited = new boolean[N][N];
            boolean[][] RGVisited = new boolean[N][N];
            
            // 3. 적록색약 여부에 따라 2번 탐색
            int notRGCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!notRGVisited[i][j]) {
                        notRGCnt++;
                        notRG(photo, i, j, notRGVisited);
                    }
                }
            }
            
            int RGCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!RGVisited[i][j]) {
                        RGCnt++;
                        RG(photo, i, j, RGVisited);
                    }
                }
            }
            
            bw.write(notRGCnt + "\n" + RGCnt);
            
            bw.flush();
            bw.close();
            br.close();
        }
        
        private static void notRG(String[][] matrix, int x, int y, boolean[][] notRGVisited) {
            notRGVisited[x][y] = true;
            
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                
                // 3-1. 적록색약 X - 색상이 완전히 일치할 때만 탐색
                if (isPossiblePosition(matrix, nextX, nextY) && !notRGVisited[nextX][nextY] && 
                    matrix[x][y].equals(matrix[nextX][nextY])) {
                    notRG(matrix, nextX, nextY, notRGVisited);
                }
            }
        }
        
        private static void RG(String[][] matrix, int x, int y, boolean[][] RGVisited) {
            RGVisited[x][y] = true;
            
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                
                // 3-2. 적록색약 O
                if (isPossiblePosition(matrix, nextX, nextY) && !RGVisited[nextX][nextY] &&
                   isRGCase(matrix, x, y, nextX, nextY)) {
                    RG(matrix, nextX, nextY, RGVisited);
                }
            }
        }
        
        private static boolean isPossiblePosition(String[][] matrix, int x, int y) {
            return (0 <= x && x < matrix.length) && (0 <= y && y < matrix.length);
        }
        
        private static boolean isRGCase(String[][] matrix, int nowX, int nowY, int nextX, int nextY) {
            // B인 경우
            if (matrix[nowX][nowY].equals("B") && matrix[nextX][nextY].equals("B"))
                return true;
            
            // R 또는 G인 경우
            if ((matrix[nowX][nowY].equals("R") || matrix[nowX][nowY].equals("G")) &&
               (matrix[nextX][nextY].equals("R") || matrix[nextX][nextY].equals("G")))
                return true;
            
            return false;
        }
    }
    ```

- 수정할 부분
    1. 그림 배열을 **전역 변수로 변경**
        - 함수마다 다 사용 중이어서 전역 변수로 변경
    2. String[][] 배열에서 **char[][] 배열로 변경**
        - 어차피 R, G, B 세 가지만 있어서 굳이 String으로 할 필요가 없음

        ```java
        // 변경 전
        matrix = new String[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = String.valueOf(input.charAt(j));
            }
        }
        
        // 변경 후
        matrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }
        ```

    3. 일부 **변수/메서드명 변경**
        - DFS는 **`dfsRG`** 처럼 변경해서 어떤 역할을 하는 건지 더 이해하기 쉽도록 변경

          코드 짤 때부터 너무 헷갈렸어서 무조건 바꿔야 함

        ```java
        // 변경 전
        boolean[][] notRGVisited = new boolean[N][N];
        boolean[][] RGVisited = new boolean[N][N];
        
        // 변경 후
        boolean[][] visitedNotRG = new boolean[N][N];
        boolean[][] visitedRG = new boolean[N][N];
        
       
        // 변경 전 - 이름만 보고 DFS인지 알 수 없음
        private static void notRG() { }
        private static void RG() { }
        
        // 변경 후
        private static void dfsNotRG() { }
        private static void dfsRG() { }
        
       
        // 변경 전 - 이름만 보고 적록색약용 색상 비교 함수인지 알 수 없음
        private static boolean isRGCase() { }
        
        // 변경 후
        private static boolean isSameColorForRG() { }
        ```

    4. 색상 비교 함수 **파라미터 간소화**
        - 지금은 2차원 배열, 현재 좌표, 그 다음 좌표 모두 받아서 비교하고 있는데 그냥 색상 값만 받아서 비교하도록 변경!
        
        ```java
        // 변경 전
        private static boolean isSameColorForRG(int nowX, int nowY, int nextX, int nextY) {
            // B인 경우
            if (matrix[nowX][nowY] == 'B' && matrix[nextX][nextY] == 'B')
                return true;
            
            // R 또는 G인 경우
            if ((matrix[nowX][nowY] == 'R' || matrix[nowX][nowY] == 'G') &&
               (matrix[nextX][nextY] == 'R' || matrix[nextX][nextY] == 'G'))
                return true;
            
            return false;
        }
        
        // 변경 후
        private static boolean isSameColorForRG(char now, char next) {
            // B인 경우
            if (now == 'B' && next == 'B') return true;
            
            // R 또는 G인 경우
            if ((now == 'R' || now == 'G') && (next == 'R' || next == 'G')) return true;
            
            return false;
        }
        ```