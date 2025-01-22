function solution(orders, course) {
  let answer = [];

  const getCombinations = (arr, length) => {
    if (length === 1) return arr.map((v) => [v]);
    const result = [];
    for (let i = 0; i < arr.length; i++) {
      const fixed = arr[i];
      const rest = arr.slice(i + 1);
      const combinations = getCombinations(rest, length - 1);
      for (const comb of combinations) {
        result.push([fixed, ...comb]);
      }
    }
    return result;
  };

  const combCount = {};

  orders = orders.map((order) => order.split("").sort().join(""));

  for (const courseLength of course) {
    for (const order of orders) {
      const combs = getCombinations(order.split(""), courseLength);
      for (comb of combs) {
        const key = comb.join("");
        if (!combCount[key]) combCount[key] = 0;
        combCount[key]++;
      }
    }
  }

  for (const courseLength of course) {
    const temp = [];
    for (const [key, count] of Object.entries(combCount)) {
      if (key.length === courseLength && count >= 2) {
        temp.push([key, count]);
      }
    }

    if (temp.length > 0) {
      temp.sort((a, b) => b[1] - a[1]);
      const maxCount = temp[0][1];
      const maxCountArray = temp
        .filter((arr) => arr[1] == maxCount)
        .map((arr) => arr[0]);
      answer.push(...maxCountArray);
    }
  }

  return answer.sort();
}
