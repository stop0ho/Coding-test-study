function solution(arr) {
  let answer = [arr[0]];

  for (let index = 1; index < arr.length; index++) {
    if (answer[answer.length - 1] !== arr[index]) answer.push(arr[index]);
  }

  return answer;
}
