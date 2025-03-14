## 폰켓몬
문제가 길지만 코드는 짧다. `N/2`마리를 가져갈 수 있는데, 중복된 거 제외하고 최대한 많은 종류여야한다. 하지만 종류별로 하나씩만 가져가는 게 아니고 종류 내의 포켓몬이 뽕나면 중복으로 꽉꽉 채워 가져가야 함.

따라서 `Set()`을 사용했다. 처음엔 Set()하고 `[...s].length` 방법을 사용했는데 `다른 사람의 풀이` 참고하니 `Set.size` 프로퍼티가 있다는 것을 알게되어 더 간결히 고쳤다.

### 이 문제가 왜 해시 카테고리에 있는가?
이 문제가 해시 문제로 분류되어 있는 이유가 궁금해서 알아봤다. 자바스크립트의 `Set`과 `Map`은 내부적으로 **해시 테이블을 기반으로 구현된** 자료구조라고 한다. 그래서 `Set`을 사용해 중복값을 제거하는 과정이 해시를 활용했다고 볼 수 있어서 해시 문제인 듯..

`Set`을 사용하면 중복 제거를 `O(1)`으로 할 수 있다. 해시 함수를 통해 각 값이 **고유한 해시 키**로 변환되어 저장되고, 동일한 값이 들어오면 이미 저장된 해시 값이 존재하므로 중복이 자동으로 방지되는 원리다. `키-값` 쌍이 아니고 `값` 자체가 key이기 때문에 가능한 일이다.