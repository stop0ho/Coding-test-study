function solution(k, room_number) {
  const answer = [];
  const roomMap = new Map();

  function findEmptyRoom(room) {
    if (!roomMap.has(room)) {
      roomMap.set(room, room + 1);
      return room;
    }

    const nextRoom = findEmptyRoom(roomMap.get(room));
    roomMap.set(room, nextRoom + 1);
    return nextRoom;
  }

  for (let room of room_number) {
    const assignedRoom = findEmptyRoom(room);
    answer.push(assignedRoom);
  }

  return answer;
}
