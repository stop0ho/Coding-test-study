function solution(cap, n, deliveries, pickups) {
  let answer = 0;

  let deliveryIndex = n - 1;
  let pickupIndex = n - 1;

  while (deliveryIndex >= 0 || pickupIndex >= 0) {
    while (deliveryIndex >= 0 && deliveries[deliveryIndex] === 0)
      deliveryIndex--;
    while (pickupIndex >= 0 && pickups[pickupIndex] === 0) pickupIndex--;

    let maxDistance = Math.max(deliveryIndex, pickupIndex) + 1;
    if (maxDistance === 0) break;

    answer += maxDistance * 2;

    let currentDelivery = 0;
    let currentPickup = 0;

    while (deliveryIndex >= 0 && currentDelivery < cap) {
      let amount = Math.min(cap - currentDelivery, deliveries[deliveryIndex]);
      deliveries[deliveryIndex] -= amount;
      currentDelivery += amount;
      if (deliveries[deliveryIndex] === 0) deliveryIndex--;
    }

    while (pickupIndex >= 0 && currentPickup < cap) {
      let amount = Math.min(cap - currentPickup, pickups[pickupIndex]);
      pickups[pickupIndex] -= amount;
      currentPickup += amount;
      if (pickups[pickupIndex] === 0) pickupIndex--;
    }
  }

  return answer;
}
