function solution(cards1, cards2, goal) {
  const answer = [];

  for (const str of goal) {
    if (cards1.length > 0 && cards1[0] === str) answer.push(cards1.shift());
    if (cards2.length > 0 && cards2[0] === str) answer.push(cards2.shift());
  }

  return answer.length === goal.length ? "Yes" : "No";
}
