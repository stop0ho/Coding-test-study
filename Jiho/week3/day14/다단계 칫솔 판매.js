function solution(enroll, referral, seller, amount) {
  let tree = {};

  // 전체 값 넣는 객체 초기화
  let totalAmount = {};
  for (const name of enroll) {
    totalAmount[name] = 0;
  }

  // 트리 초기화=> 판매자:본인 끌어들인 애
  for (let i = 0; i < enroll.length; i++) {
    const referName = referral[i];
    const enrollName = enroll[i];
    tree[enrollName] = referName;
  }

  function dfs(name, amount) {
    if (name === "-") return;
    const amount1 = Math.ceil(amount * 0.9);
    const amount2 = Math.floor(amount * 0.1);
    if (amount2 < 1) {
      // 본인이 꿀꺽스: 본인 + amount
      totalAmount[name] += amount;
      return;
    }
    // 본인 + amount1
    totalAmount[name] += amount1;

    dfs(tree[name], amount2);
  }

  for (let i = 0; i < seller.length; i++) {
    dfs(seller[i], amount[i] * 100);
  }

  return Object.values(totalAmount);
}
