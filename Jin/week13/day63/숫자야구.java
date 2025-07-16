package Jin.week13.day63;

import java.io.*;
import java.util.*;

public class 숫자야구 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int N = Integer.parseInt(br.readLine());
        String[] guess = new String[N];
        int[][] sbResult = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            guess[i] = st.nextToken();
            sbResult[i][0] = Integer.parseInt(st.nextToken()); // 스트라이크 개수
            sbResult[i][1] = Integer.parseInt(st.nextToken()); // 볼 개수
        }

        // 2. 조건에 맞는 수 찾기
        int result = 0;
        for (int i = 100; i <= 999; i++) {
            String num = String.valueOf(i);

            if (!isPossibleNum(num)) continue;
            if (isCorrect(num, guess, sbResult)) result++;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    // 2-1. 서로 다른 숫자 세 개로 구성된 세 자리 수인지 판단
    private static boolean isPossibleNum(String num) {
        char first = num.charAt(0);
        char second = num.charAt(1);
        char third = num.charAt(2);

        // 1부터 9까지 숫자로 이루어져야 함
        if (first == '0' || second == '0' || third == '0') return false;

        return (first != second) && (first != third) && (second != third);
    }

    // 2-2. 입력받은 수와 스트라이크/볼 개수 비교해서 판단
    private static boolean isCorrect(String num, String[] guess, int[][] sbResult) {
        for (int i = 0; i < guess.length; i++) {
            int bCnt = 0, sCnt = 0;
            String guessNum = guess[i];

            for (int j = 0; j < 3; j++) {
                // contains 메서드를 이용해 볼 카운트 계산
                if (num.contains(String.valueOf(guessNum.charAt(j)))) bCnt++;

                // 각 자릿수 비교를 통해 스트라이크 카운트 계산
                if (num.charAt(j) == guessNum.charAt(j)) sCnt++;
            }
            // 스트라이크 위치는 볼로 카운트하면 안되므로 총 개수에서 빼주기
            bCnt -= sCnt;

            // 하나라도 다를 경우 조건에 맞는 수가 아님
            if (sbResult[i][0] != sCnt || sbResult[i][1] != bCnt) return false;
        }

        return true;
    }
}