function solution(s) {
  return s.map((str) => {
    let stack = [];
    let count = 0;

    for (let char of str) {
      stack.push(char);
      if (stack.length >= 3 && stack.slice(-3).join("") === "110") {
        stack.pop();
        stack.pop();
        stack.pop();
        count++;
      }
    }

    let insertIndex = stack.lastIndexOf("0") + 1;
    let result =
      stack.slice(0, insertIndex).join("") +
      "110".repeat(count) +
      stack.slice(insertIndex).join("");
    return result;
  });
}
