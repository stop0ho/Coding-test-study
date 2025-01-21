package week1.day02;

import java.util.*;

public class Q02_네트워크 {

    private static int[] parents;

    public int solution(int n, int[][] computers) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 0 || find(i, j)) {
                    continue;
                }
                union(i, j);
            }
        }
        return calculateParentCount(n);
    }

    private int getParent(int index) {
        if (parents[index] == index) {
            return index;
        }
        return parents[index] = getParent(parents[index]);
    }

    private boolean find(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);
        return parentA == parentB;
    }

    private void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if (parentA < parentB) {
            parents[parentB] = parentA;
        } else {
            parents[parentA] = parentB;
        }
    }

    private int calculateParentCount(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(getParent(i));
        }
        return set.size();
    }
}
