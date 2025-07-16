package Jin.week13.day62;

import java.io.*;
import java.util.*;

public class 로봇 {
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
            return (index - 1 + 4) % 4;
        }
    }

    //
    private static boolean isPossiblePosition(int x, int y, int M) {
        return (0 <= x && x <= M) && (0 <= y && y <= M);
    }
}