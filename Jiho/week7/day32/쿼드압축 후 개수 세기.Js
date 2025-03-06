function solution(arr) {
  let answer = [0, 0];

  function compress(x, y, size) {
    let first = arr[x][y];

    for (let i = x; i < x + size; i++) {
      for (let j = y; j < y + size; j++) {
        if (arr[i][j] !== first) {
          // 4등분
          const half = size / 2;
          compress(x, y, half);
          compress(x, y + half, half);
          compress(x + half, y, half);
          compress(x + half, y + half, half);
          return;
        }
      }
    }

    answer[first]++;
  }

  compress(0, 0, arr.length);

  return answer;
}
