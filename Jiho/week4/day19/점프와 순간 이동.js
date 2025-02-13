function solution(n) {
  let answer = 0;

  while (n > 0) {
    if (n % 2 === 0) {
      n /= 2;
    } else {
      answer++;
      n -= 1;
    }
  }

  return answer;
}
