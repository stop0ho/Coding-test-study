package Jin.week7.day32;

public class 쿼드압축후개수세기 {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];

        quad(arr, 0, 0, arr.length);

        return answer;
    }

    // 시작지점부터 size를 더한 곳까지 다 같은지 확인하고, 다 같다면 압축하기
    private boolean zip(int[][] arr, int startX, int startY, int size) {
        // 첫번째 수랑 같은지 다른지 비교할 거니까
        int num = arr[startX][startY];

        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                // 첫번째 숫자 기준으로 비교하는 거니까, 첫번째 위치는 continue 해주기 (비교할 필요 없으니까)
                if (i == startX && j == startY) continue;

                // 값이 하나라도 다르면 false 반환 (압축 불가능)
                if (num != arr[i][j]) return false;
            }
        }

        // 반복문을 모두 수행했다면, 모든 숫자가 같다는 뜻이니까 true 반환 (압축 가능)
        return true;
    }

    private void quad(int[][] arr, int x, int y, int size) {
        if (zip(arr, x, y, size)) {
            if (arr[x][y] == 1) answer[1]++;
            else answer[0]++;

            // 압축 가능할 경우, 분할하지 않아도 됨
            return;
        }

        // 압축이 불가능한 경우에만 분할 진행
        quad(arr, x, y, size / 2);
        quad(arr, x + size / 2, y, size / 2);
        quad(arr, x, y + size / 2, size / 2);
        quad(arr, x + size / 2, y + size / 2, size / 2);
    }
}
