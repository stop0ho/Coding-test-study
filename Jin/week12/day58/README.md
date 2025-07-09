## 📌 문제 탐색하기

### 문제

나무판에서 버섯 농사 지을 때 농사가 가능할지 판단하고, 가능하다면 남은 버섯 포자의 개수를 출력

- 나무 판에는 **버섯이 자랄 수 있는 칸과 없는 칸이 존재**
- 버섯 포자는 버섯이 자랄 수 있는 칸에만 심을 수 있음
- 포자를 **여러 개 겹쳐서 심을 수 있음**
    - x개의 버섯을 겹쳐 심으면 **해당 칸을 포함해 최대 x * K개 칸에 버섯**이 자람
- **버섯 포자를 하나라도 사용하고** 버섯이 자랄 수 있는 모든 칸에 버섯이 전부 자랐을 때 농사가 가능하다고 정의

### 조건

N: 나무판 한 변의 길이 **1 ≤ N ≤ 100**

M: 버섯 포자 개수 **1 ≤ M ≤ 1,000,000**

K: 포자가 심어진 칸을 포함해 해당 숫자만큼 연결된 칸에 버섯을 자라게 함 **1 ≤ K ≤ 10^8**

- 상하좌우로 적어도 한 변을 공유하는 칸에만 버섯을 자라게 함

버섯이 자랄 수 **있는** 칸은 **0**, 버섯이 자랄 수 **없는** 칸은 **1**

### 풀이 및 시간 복잡도 고려

가장 고려해야 할 부분은 **K** 값

- K의 값만큼 연결된 칸에 버섯을 자라게 하므로, **0**(버섯이 자랄 수 있는 칸)으로 연결된 칸의 개수를 세서, 그 구역에 필요한 버섯 수를 구하기

  → 연결된 구역에 포자를 나눠서 심는거나 겹쳐서 심는거나 필요한 최소 개수는 동일할테니까!


어제 [**섬의 개수**](https://www.notion.so/2291aba227288093be58d8d06ad1c45a?pvs=21) 문제와 비슷하게 한 지점에서 연결된 칸을 세는 것이므로 **DFS로 연결된 영역이 몇 개인지 한번에 탐색**하는 것이 자연스러울 것이라 판단

DFS 시간 복잡도는 **O(V + E)**

- 정점의 수 **V = N * N** (2차원 배열의 한 칸이 전부 다 정점)
- 간선의 수 E: 각 칸마다 최대 4개의 간선이 발생 **E ≤ 4V**

**O(V + E) = O(N * N + N * N) = O(N^2)**

→ 100 * 100 = **10,000이므로 2초 내로 문제 풀이 가능**

---

## 📌 코드 설계하기

1. 문제 input을 입력 받음
2. N * N 크기의 2차원 배열에 나무판의 정보 저장하기
    - **버섯이 자랄 수 있는 칸이 0임에 유의**(보통 1으로 생각해서,, 개인 실수 방지)
3. 2차원 배열을 **DFS 탐색**하며 **구역 별로 연결된 칸의 개수** 구하기
    - 약간 [단지 번호 붙이기](https://www.notion.so/22b1aba227288008a777cb8d27b750e2?pvs=21) 문제처럼 각각 구역에 몇 개의 칸에 버섯을 심을 수 있는지 세기!
    - 해당 구역의 개수를 K로 나누면 해당 구역에 몇 개의 포자가 필요한지 알 수 있음!

      (풀이 완료 후) 여기서 두루뭉술하게 생각해서 올림 계산을 의심하지 않은 것 같다. 앞으로는 더 꼼꼼하게 생각해보기

4. 심어야 할 포자 개수와 M의 값을 비교해 출력
    - 심어야 할 포자 개수 > M: IMPOSSIBLE 출력
    - 심어야 할 포자 개수 ≤ M: POSSIBLE 및 남은 개수 출력

      (풀이 완료 후) 여기에서도 IMPOSSIBLE의 조건을 제대로 적어두지 않음. 설계 단계에서 조건을 한번 더 확인하는 습관 기르기


---

## 📌 시도 회차 수정 사항

### 1회차

- 코드

    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int[] dx = { 1, -1, 0, 0 };
        static int[] dy = { 0, 0, -1, 1 };
    
        public static void main(String[] args) throws Exception {
            // ...
    
            // 3. DFS 탐색
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 3-1. 각 구역이 몇 칸으로 이루어져있는지 구하기
                    int areaSize = DFS(matrix, i, j);
                    // 3-2. 각 구역별로 몇 개의 포자를 심어야 하는지 구하기
                    result += getSporeNum(areaSize, K);
                }
            }
            
            // ...
        }
        
        // ...
        
        private static int getSporeNum(int area, int K) {
            return (area / K) + (area % K);
        }
    }
    ```

- 정말 중요한 실수를 했다.
    1. 처음 DFS 탐색을 시작할 때 **0인 구역에서 탐색을 하게 해야** 하는데.. 그냥 모든 구역에서 다 할 수 있게 함

       ⇒ **if문을 추가**해 해결

    2. 포자 개수 구하는 식을 **나머지가 1 또는 0인 케이스**만 생각해서 짰는데(문제 예제에서 보인 것만 생각함) 나머지가 2 이상인 경우는 이렇게 계산하면 오류가 발생함

       ⇒ **올림 함수를 사용**해 해결


### 2회차

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
    
            // 1. input 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
    
            // 2. 나무판의 정보 저장하기
            int[][] matrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            // 3. DFS 탐색
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == 0) {
                        // 3-1. 각 구역이 몇 칸으로 이루어져있는지 구하기
                        int areaSize = DFS(matrix, i, j);
                        // 3-2. 각 구역별로 몇 개의 포자를 심어야 하는지 구하기
                        result += getSporeNum(areaSize, K);
                    }
                }
            }
    
            if (result > M) bw.write("IMPOSSIBLE\n");
            else {
                bw.write("POSSIBLE\n");
                bw.write(M - result + "");
            }
    
            bw.flush();
            bw.close();
            br.close();
        }
    
        private static int DFS(int[][] matrix, int x, int y) {
            // 다시 방문하지 못하게 변경
            matrix[x][y] = 1;
    
            int count = 1;
    
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
    
                if (isPossiblePosition(matrix, nextX, nextY) && matrix[nextX][nextY] == 0) {
                    count += DFS(matrix, nextX, nextY);
                }
            }
            return count;
        }
    
        private static boolean isPossiblePosition(int[][] matrix, int x, int y) {
            return (0 <= x && x < matrix.length) && (0 <= y && y < matrix.length);
        }
    
        private static int getSporeNum(int area, int K) {
            return (int) Math.ceil((double) area / K);
        }
    }
    ```

- 반례

    ```java
    // 입력
    2 2 1
    1 1
    1 1
    
    // 출력
    POSSIBLE
    2
    
    // 정답
    IMPOSSIBLE
    ```

    - 문제 조건에 bold 처리까지 되어있고.. 심지어 문제 조건 정리할 때 나도 bold 처리를 했지만!! 신경쓰지 않은 부분.

      **버섯 포자를 하나라도 사용하고** 버섯이 자랄 수 있는 모든 칸에 버섯이 전부 자랐을 때 농사가 가능하다고 정의

      버섯 포자를 하나도 사용하지 않았기 때문에 농사가 불가능함

      ⇒ **포자를 하나도 사용하지 않았을 경우**에 **IMPOSSIBLE**을 출력할 수 있게 변경


---

### 💭 회고

어제는 문제 풀이하면서 입력값을 덜 받는 실수가 있었다. 백준 풀면 꽤 자주 하는 실수인데.. 기업 코딩테스트나 프로그래머스와 같은 곳에서 문제 풀이할 때는 입력값을 따로 받지 않아도 되기 때문에 잠시 놓쳤던 것 같다. 뭐 이런 실수는 직접 예제를 확인해 보려고 할 때부터 오류가 나기 때문에 빠르게 확인할 수 있는 문제이다.

그러나 오늘 했던 실수를 생각해 보자.

1. 현재 칸이 0이 아니어도 DFS가 실행됨
    - 이건 정답에 직접적인 영향이 가서 빠르게 알아차렸다. 그나마 다행인 경우..
2. 포자 개수 구하는 공식을 잘못 세움
    - 이건 정말 몰랐다.. 생각도 안 해봤다..

      예제에서 나머지가 1 또는 0인 경우만 있기도 했고, 예제 말고 다른 케이스도 생각해보자고 종이에 쓴 것도.. 지금 보니 다 나머지가 1인 경우만 있었다.

      제출하자마자 틀렸다고 하길래 전혀 모르겠기에 Claude에 어디가 틀렸는지 대충 힌트를 던져달라고 부탁했다.
      답변 보고 **아!** 하는 생각밖에 나지 않았다… 정말 간단하고도 중요한 문제였는데.. 실제 코딩테스트에서 이런 문제가 나왔다면 예제만 풀고 “문제가 정말 쉽게 나왔네!” 하고 넘어갈 수도 있던 일이어서 조금 상심이 컸다..ㅎ

3. 문제 조건을 제대로 처리하지 않음
    - 이건 버섯 포자를 하나도 사용하지 않았을 경우를 처리하지 않았다. 문제 조건을 꼼꼼히 읽는다고 노션에 쓰고, bold 처리도 하고..  실제로 코드 쓸 때 이걸 생각을 안 하면 어쩌자는 거지 싶었다…ㅎㅎ 그냥 꾸미기를 좋아한 거 아닐까 하는 회의감도 들었다…

      지금까지 코딩테스트 문제 풀던 걸 생각하면 문제 조건을 제대로 안읽 어서 틀렸던 문제들이 여럿 있었다. 이거는 더 연습하면서 꼼꼼히 읽는 수밖에 없겠지 싶다.ㅠㅠ


문제를 옮겨쓰고 정리한다고 무조건 꼼꼼하게 읽고 생각한다는 게 아닌 걸 알았으니! 내일부터는 정신 똑바로 차리고 문제 풀자! 화이팅!