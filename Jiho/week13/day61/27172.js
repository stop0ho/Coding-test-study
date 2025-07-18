const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(input[0]);
const cards = input[1].split(" ").map((v) => +v);
const max = 1000001;

const exists = new Array(max).fill(false);
for (let card of cards) {
  exists[card] = true;
}

const score = new Array(max).fill(0);

for (let i = 1; i < max; i++) {
  if (!exists[i]) continue;
  for (let j = i * 2; j < max; j += i) {
    if (exists[j]) {
      score[i] += 1;
      score[j] -= 1;
    }
  }
}

let result = cards.map((card) => score[card]);
console.log(result.join(" "));
