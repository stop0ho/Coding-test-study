package Jin.week8.day38;

import java.util.*;

public class 거리두기확인하기 {
    static int placeLength = 5;
    static ArrayList<int[]> position;
    public int[] solution(String[][] places) {
        int[] answer = new int[placeLength];

        // 하나씩 돌아가면서 거리두기 여부 확인 후, 값 삽입
        for (int i = 0; i < placeLength; i++) {
            // 매 반복마다 초기화해주기
            position = new ArrayList<>();
            char[][] place = getMatrix(places[i]);

            // 규칙을 잘 지키고 있다면 1, 아니라면 0을 반환
            if (isValid(place)) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }

    // 입력받은 문자열 배열을 2차원 배열로 변경하는 함수
    private char[][] getMatrix(String[] placeStr) {
        char[][] place = new char[placeLength][placeLength];

        for (int i = 0; i < placeLength; i++) {
            for (int j = 0; j < placeLength; j++) {
                place[i][j] = placeStr[i].charAt(j);

                if (place[i][j] == 'P') {
                    position.add(new int[]{i, j});
                }
            }
        }

        return place;
    }

    // 규칙에 유효한지 확인하는 함수
    private boolean isValid(char[][] place) {

        // 응시자가 없다면 바로 true 리턴
        if (position.size() == 0) return true;

        for (int i = 0; i < position.size() - 1; i++) {
            for (int j = i + 1; j < position.size(); j++) {
                int[] pos1 = position.get(i);
                int[] pos2 = position.get(j);

                // 거리가 2보다 크다면, 거리두기가 지켜지고 있음
                if (getDistance(pos1, pos2) > 2) continue;
                else {
                    // 파티션의 존재 유무로 거리두기 확인
                    if (isExisted(place, pos1, pos2)) continue;
                    else return false;
                }
            }
        }
        return true;
    }

    // 맨해튼 거리를 구하는 함수
    private int getDistance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }

    // 두 응시자 사이 파티션 존재 유무 확인
    private boolean isExisted(char[][] place, int[] pos1, int[] pos2) {
        if (pos1[0] == pos2[0]) {
            // 행이 같다면, 둘 사이에 존재하는 열에 파티션이 존재하는지 확인해야 함
            int columnIdx = pos2[1] - 1;
            if (place[pos1[0]][columnIdx] == 'X') return true;
        }

        if (pos1[1] == pos2[1]) {
            // 열이 같다면, 둘 사이에 존재하는 행에 파티션이 존재하는지 확인해야 함
            int rowIdx = pos2[0] - 1;
            if (place[rowIdx][pos1[1]] == 'X') return true;
        }

        // 행과 열이 다르다면, 대각선 방향으로 파티션이 존재하는지 확인해야 함
        if (place[pos2[0]][pos1[1]] == 'X' && place[pos1[0]][pos2[1]] == 'X') return true;

        // 위 경우에 해당하지 않는다면 거리두기가 지켜지지 않는 것
        return false;
    }
}
