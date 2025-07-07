function solution(s) {
  let answer = Infinity;

  for (let unit = 1; unit <= s.length / 2; unit++) {
    let tempStr = "";
    let start = 0;
    let end = unit;
    let now = s.slice(start, end);
    let count = 1;

    while (end <= s.length) {
      start += unit;
      end += unit;

      let temp = s.slice(start, end);
      if (now === temp) count++;
      else {
        if (count > 1) tempStr += `${count}${now}`;
        else tempStr += now;

        now = temp;
        count = 1;
      }
    }
    // 남는 문자열이 있으면 덧붙이도록 처리
    if (s.length % unit !== 0) tempStr += s.slice(end - unit);
    if (answer > tempStr.length) answer = tempStr.length;
  }

  return s.length === 1 ? 1 : answer;
}
