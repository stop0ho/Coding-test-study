function solution(board) {
  let answer = 0;
  const dp = board.map((v) => v);

  if (board.length === 1 && board[0].length === 1 && board[0][0] === 1)
    return 1;

  for (let i = 1; i < board.length; i++) {
    for (let j = 1; j < board[0].length; j++) {
      if (board[i][j] !== 0) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1;
        if (dp[i][j] > answer) answer = dp[i][j];
      }
    }
  }

  return answer * answer;
}
