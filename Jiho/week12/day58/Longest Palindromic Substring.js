/**
 * @param {string} s
 * @return {string}
 */
let longestPalindrome = function (s) {
  function expandAroundCenter(left, right) {
    while (left >= 0 && right < s.length && s[left] === s[right]) {
      left--;
      right++;
    }
    return s.slice(left + 1, right);
  }

  let result = "";

  for (let i = 0; i < s.length; i++) {
    const oddPalindrome = expandAroundCenter(i, i);
    const evenPalindrome = expandAroundCenter(i, i + 1);

    if (oddPalindrome.length > result.length) {
      result = oddPalindrome;
    }
    if (evenPalindrome.length > result.length) {
      result = evenPalindrome;
    }
  }

  return result;
};
