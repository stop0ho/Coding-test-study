function solution(sequence, k) {
  let start_index = 0;
  let end_index = 0;
  let sum = sequence[0];
  let answer = null;

  while (end_index < sequence.length) {
    if (sum === k) {
      if (!answer || end_index - start_index < answer[1] - answer[0]) {
        answer = [start_index, end_index];
      }
    }

    if (sum < k) {
      end_index++;
      if (end_index < sequence.length) {
        sum += sequence[end_index];
      }
    } else {
      sum -= sequence[start_index];
      start_index++;
    }
  }

  return answer;
}
