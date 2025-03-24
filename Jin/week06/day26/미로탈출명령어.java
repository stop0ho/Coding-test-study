package Jin.week06.day26;

public class 미로탈출명령어 {
    static int N, M, K;
    static String answer = "impossible";
    static StringBuilder sb;

    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        sb = new StringBuilder();

        int distance = getDistance(x, y, r, c);
        if (!isPossibleCase(distance)) return answer;

        DFS(x, y, r, c, 0);
        return answer;
    }

    private void DFS(int x, int y, int r, int c, int count) {
        if (!answer.equals("impossible")) return;

        if (count == K) {
            // k번 이동이 끝난 후, 종료지점이라면 정답
            if (x == r && y == c) {
                answer = sb.toString();
            }
            return;
        }

        // 무조건 사전 순으로 탐색하게 해서 k번 이동 + 사전 순으로 가장 빠른걸 return 할 수 있게
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 미로 범위를 벗어나면 안되니까
            if (!isValid(nx, ny)) continue;

            // 현재 위치부터 종료지점까지 거리가 k보다 크면 절대 안되는 경우니까
            if (getDistance(nx, ny, r, c) + (count + 1) > K) continue;

            sb.append(dir[i]);
            DFS(nx, ny, r, c, count + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 미로를 벗어나면 안됨
    private boolean isValid(int x, int y) {
        return 1 <= x && x <= N && 1 <= y && y <= M;
    }

    private int getDistance(int nowX, int nowY, int endX, int endY) {
        return Math.abs(nowX - endX) + Math.abs(nowY - endY);
    }

    // k 값만 보고 아예 불가능한 경우인지 판단하기
    private boolean isPossibleCase(int distance) {
        // k가 최단거리보다 작은 경우
        if (distance > K) return false;

        // 최단거리와 k가 모두 홀수이거나 짝수인 경우에만 도달가능
        // 같은 종류끼리 덧셈 연산을 하면 짝수임을 이용해, 짝수가 아닌 경우 false 반환
        return (distance + K) % 2 == 0;
    }
}
