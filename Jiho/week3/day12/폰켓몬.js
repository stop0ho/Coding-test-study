function solution(nums) {
  let s = new Set(nums);
  return Math.min(s.size, nums.length / 2);
}
