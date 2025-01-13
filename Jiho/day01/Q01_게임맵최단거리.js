function solution(maps) {
  N = maps.length;
  M = maps[0].length;

  let dist = Array.from(Array(N), () => new Array(M).fill(-1));

  let dy = [-1, 1, 0, 0];
  let dx = [0, 0, -1, 1];

  let q = [[0, 0]];
  maps[0][0] = 0;
  dist[0][0] = 1;

  while (q.length !== 0) {
    let [y, x] = q.shift();

    for (let k = 0; k < 4; k++) {
      let newY = dy[k] + y;
      let newX = dx[k] + x;

      if (0 <= newY && newY < N && 0 <= newX && newX < M && maps[newY][newX]) {
        maps[newY][newX] = 0;
        dist[newY][newX] = dist[y][x] + 1;
        q.push([newY, newX]);
      }
    }
  }

  return dist[maps.length - 1][maps[0].length - 1];
}
