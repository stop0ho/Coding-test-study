package week05.day22;

import java.util.*;

public class Q25_호텔방배정 {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Set<Long> set = new HashSet<>();
        TreeSet<Long> tset = new TreeSet<>();

        for (int i = 0; i < room_number.length; i++) {
            long room = room_number[i];
            while (set.contains(room)) {
                tset.remove(room);
                room = tset.higher(room);
            }

            set.add(room);
            tset.add(room + 1);
            answer[i] = room;
        }
        return answer;
    }
}
