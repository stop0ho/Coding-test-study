const fs = require("fs");
const [A, B] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map(BigInt);

function countOnes(n) {
  if (n < 0n) return 0n;

  let result = 0n;
  let bit = 0n;

  while (1n << bit <= n) {
    const blockSize = 1n << (bit + 1n); // 2^(bit+1)
    const fullBlocks = n / blockSize;
    const remainder = n % blockSize;

    result += fullBlocks * (blockSize / 2n); // 각 블록마다 1이 나오는 횟수
    result += remainder >= 1n << bit ? remainder - (1n << bit) + 1n : 0n;

    bit += 1n;
  }

  return result;
}

const answer = countOnes(B) - countOnes(A - 1n);
console.log(answer.toString());
