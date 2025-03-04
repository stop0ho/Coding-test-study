package week01.day05;

import java.util.*;

public class Q06_표편집 {

    private final static TreeSet<Integer> activeRows = new TreeSet<>();
    private final static Stack<Integer> deleted = new Stack<>();
    private static int ptr;
    private static int total;

    public String solution(int n, int k, String[] cmd) {
        ptr = k;
        total = n;
        for (int i = 0; i < n; i++) {
            activeRows.add(i);
        }

        for (String c : cmd) {
            String[] operation = c.split(" ");
            apply(operation);
        }

        char[] result = new char[n];
        Arrays.fill(result, 'X');
        for (int index : activeRows) {
            result[index] = 'O';
        }
        return new String(result);
    }

    private void apply(String[] operation) {
        switch (operation[0]) {
            case "U":
                move(-(Integer.parseInt(operation[1]) % total));
                break;
            case "D":
                move(Integer.parseInt(operation[1]) % total);
                break;
            case "C":
                delete();
                break;
            case "Z":
                rollback();
                break;
        }
    }

    private void move(int cnt) {
        while (cnt != 0) {
            if (cnt > 0) {
                ptr = activeRows.higher(ptr) != null ? activeRows.higher(ptr) : activeRows.first();
                cnt--;
            } else {
                ptr = activeRows.lower(ptr) != null ? activeRows.lower(ptr) : activeRows.last();
                cnt++;
            }
        }
    }

    private void delete() {
        deleted.push(ptr);
        activeRows.remove(ptr);
        ptr = activeRows.higher(ptr) != null ? activeRows.higher(ptr) : activeRows.lower(ptr);
    }

    private void rollback() {
        activeRows.add(deleted.pop());
    }
}
