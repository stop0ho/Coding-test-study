function solution(s) {
  const answer = [];
  const tuples = [];

  for (const tuple of s.split("},")) {
    tuples.push(
      tuple
        .replaceAll("{", "")
        .replaceAll("}", "")
        .split(",")
        .map((v) => Number(v))
    );
  }

  tuples.sort((a, b) => a.length - b.length);

  for (const tuple of tuples) {
    answer.push(...tuple.filter((v) => !answer.includes(v)));
  }

  return answer;
}
