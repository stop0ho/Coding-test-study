package Jin.week08.day37;

public class k진수에서소수개수구하기 {
    static int answer = 0;

    public int solution(int n, int k) {
        // 진수 변환
        String convertNum = Integer.toString(n, k);
        String[] convertArr = convertNum.split("0");

        // 소수가 몇 개인지 확인하기
        check(convertArr);

        return answer;
    }

    private void check(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("")) continue;

            long number = Long.parseLong(arr[i]);
            if (isPrime(number)) answer++;
        }
    }

    private boolean isPrime(long number) {
        // 1은 소수가 아님
        if (number == 1) return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }

        return true;
    }
}
