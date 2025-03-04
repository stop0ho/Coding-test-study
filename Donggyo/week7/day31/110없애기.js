function solution(s) {
  answer = [];
  for(let i = 0; i < s.length; i++){
      answer.push(buildAnswer(s[i]));
  }
  return answer;
}

function buildAnswer(str) {
  let stack = [];
  let count = 0;

  for (let i = 0; i < str.length; i++) {
      stack.push(str[i]);
      if (stack.length >= 3 && stack.slice(-3).join('') === "110") {
          stack.pop();
          stack.pop();
          stack.pop();
          count++;
      }
  }

  let insertIndex = stack.lastIndexOf("0") + 1; //가장 마지막 0이 나오는 위치
  let result = stack.slice(0, insertIndex).join('') // 0 이전 값
              + "110".repeat(count)  //110 삽입
              + stack.slice(insertIndex).join(''); //0 이후

  return result;
}
