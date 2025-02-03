function solution(n) {
  const results = [];
  const board = Array.from({ length: n }, () => Array(n).fill("."));

  const columns = new Set();
  const diagonal1 = new Set();
  const diagonal2 = new Set();

  function backtrack(row) {
    if (row === n) {
      results.push(board.map((row) => row.join("")));
      return;
    }

    for (let col = 0; col < n; col++) {
      if (
        columns.has(col) ||
        diagonal1.has(row + col) ||
        diagonal2.has(row - col)
      ) {
        continue;
      }

      board[row][col] = "Q";
      columns.add(col);
      diagonal1.add(row + col);
      diagonal2.add(row - col);

      backtrack(row + 1);

      board[row][col] = ".";
      columns.delete(col);
      diagonal1.delete(row + col);
      diagonal2.delete(row - col);
    }
  }

  backtrack(0);
  return results.length;
}
