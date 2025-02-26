function solution(today, terms, privacies) {
  const answer = [];
  const dates = [];

  function toDateValue(dateStr) {
    const [y, m, d] = dateStr.split(".").map(Number);
    return y * 12 * 28 + m * 28 + d;
  }

  // 1. 유효기간 정보 객체로 가공(O(1)로 접근하기 위해)
  const termObj = {};
  for (const term of terms) {
    const [key, value] = term.split(" ");
    termObj[key] = Number(value);
  }

  // 2. 파기 날짜 계산
  for (let i = 0; i < privacies.length; i++) {
    let [date, type] = privacies[i].split(" ");
    let [year, month, day] = date.split(".").map((v) => Number(v));

    const tempMonth = month + termObj[type];
    year += Math.floor((tempMonth - 1) / 12);
    month = ((tempMonth - 1) % 12) + 1;

    date = [year, month, day].join(".");

    // 3. 파기날짜 지났으면 바로바로 answer에 추가
    if (toDateValue(date) <= toDateValue(today)) answer.push(i + 1);
  }

  return answer;
}
