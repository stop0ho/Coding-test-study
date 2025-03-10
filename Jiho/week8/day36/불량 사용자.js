// banned_id에 해당하는 사용자 중 한 명을 뽑아야 하며 모든 경우의 수를 뽑아야 하니 곱연산(그리디) 하면 될 것이라 생각
// -> 하지만 같은 응모자 아이디가 중복해서 제재 아이디 목록에 들어가는 경우는 없으므로 기존의 뽑기 결과가 이후의 뽑기에 영향을 미친다. 따라서 곱연산은 불가하다.
// 또한 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리한다.
// '*'의 경우에는 무슨 글자가 오든 일치한다고 보면 된다.
function solution(user_id, banned_id) {
  let answer = new Set();

  function checkId(banned, user) {
    if (banned.length !== user.length) return false;

    for (let i = 0; i < banned.length; i++) {
      if (banned[i] === "*") continue;
      if (banned[i] !== user[i]) return false;
    }

    return true;
  }

  function check(index, visited) {
    if (index === banned_id.length) {
      answer.add([...visited].sort().join(""));
      return;
    } // 종료조건: 제제 아이디 케이스를 모두 채웠을 때

    const banned = banned_id[index];
    const users = user_id.filter(
      (id) => checkId(banned, id) && !visited.includes(id)
    );
    if (users.length === 0) return; // back 조건: 조건에 해당되는 users가 없을 때

    for (const user of users) {
      visited.push(user);
      check(index + 1, visited);
      visited.pop();
    }
  }

  check(0, []);

  return answer.size;
}
