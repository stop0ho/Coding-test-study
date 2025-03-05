package week07.day32;

public class Q36_쿼드압축후개수세기 {

    private static final int[] answer = new int[] { 0, 0 };

    public int[] solution(int[][] arr) {
        divideAndConquer(0, 0, arr, arr.length);
        return answer;
    }

    private void divideAndConquer(int x, int y, int[][] arr, int n) {
        int target = arr[x][y];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[x + i][y + j] != target) {
                    int len = n / 2;
                    divideAndConquer(x, y, arr, len);
                    divideAndConquer(x + len, y, arr, len);
                    divideAndConquer(x, y + len, arr, len);
                    divideAndConquer(x + len, y + len, arr, len);
                    return;
                }
            }
        }
        answer[target]++;
    }
}
