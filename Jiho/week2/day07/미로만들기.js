const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const n = Number(input[0]);
const maze = input.slice(1).map((line) => line.split("").map(Number));

const directions = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];

const bfs = () => {
  const dist = Array.from({ length: n }, () => Array(n).fill(Infinity));
  const deque = [];
  dist[0][0] = 0;
  deque.push([0, 0]);

  while (deque.length > 0) {
    const [x, y] = deque.shift();

    for (const [dx, dy] of directions) {
      const nx = x + dx;
      const ny = y + dy;

      if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

      const cost = dist[x][y] + (maze[nx][ny] === 0 ? 1 : 0);

      if (cost < dist[nx][ny]) {
        dist[nx][ny] = cost;

        if (maze[nx][ny] === 1) {
          deque.unshift([nx, ny]);
        } else {
          deque.push([nx, ny]);
        }
      }
    }
  }

  return dist[n - 1][n - 1];
};

console.log(bfs());
