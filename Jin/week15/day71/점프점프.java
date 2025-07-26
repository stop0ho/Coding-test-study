package Jin.week15.day71;

import java.io.*;
import java.util.*;

public class 점프점프 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int N = Integer.parseInt(br.readLine());
        int[] maze = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            maze[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 탐색 시작
        int[] memo = new int[N];
        for (int i = 0; i < N; i++) {
            // 첫 번째 칸을 제외하고 memo[i]가 0이라면 방문하지 못하는 칸이므로 다음 칸 탐색
            if (i != 0 && memo[i] == 0) continue;

            int jump = maze[i];
            for (int j = i + 1; j <= i + jump; j++) {
                // 배열의 인덱스를 벗어나는 경우 종료
                if (j >= N) break;

                memo[j] = (memo[j] == 0) ? memo[i] + 1 : Math.min(memo[j], memo[i] + 1);
            }
        }

        // 3. 마지막 칸 값 출력
        if (N == 1) bw.write("0");
        else {
            int result = (memo[N - 1] != 0) ? memo[N - 1] : -1;
            bw.write(result + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
*
* BFS 풀이
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int N = Integer.parseInt(br.readLine());
        int[] maze = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            maze[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 탐색 시작
        int[] memo = new int[N];
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int nowIdx = queue.poll();
            int jump = maze[nowIdx];

            for (int i = nowIdx + 1; i <= nowIdx + jump; i++) {
                if (i >= N) break; // 배열의 인덱스를 벗어나는 경우 종료
                if (visited[i]) continue; // 이미 방문한 칸이면 방문하지 않음

                queue.add(i);
                memo[i] = memo[nowIdx] + 1;
                visited[i] = true;
            }
        }

        // 3. 마지막 칸 값 출력
        if (N == 1) bw.write("0");
        else {
            int result = (memo[N - 1] != 0) ? memo[N - 1] : -1;
            bw.write(result + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
* */