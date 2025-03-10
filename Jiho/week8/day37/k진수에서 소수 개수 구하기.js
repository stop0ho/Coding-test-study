function isPrime(num) {
  if (num <= 1) return false;
  for (let i = 2; i <= Math.floor(Math.sqrt(num)); i++) {
    if (num % i == 0) return false;
  }
  return true;
}

function solution(n, k) {
  let answer = 0;

  // 1. 정수 n을 k진수로 변환한다.
  let NToK = n.toString(k);
  // 2. 변환된 수를 0을 기준으로 나눈다. 그러면 조건에 맞는 수만 반환된다.
  let numList = NToK.split("0").map((v) => +v);
  // 3. 소수인 것들 카운트
  return numList.filter((v) => isPrime(v)).length;
}
