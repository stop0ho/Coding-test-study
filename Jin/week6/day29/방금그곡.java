package Jin.week6.day29;

import java.util.*;

public class 방금그곡 {
    public String solution(String m, String[] musicinfos) {
        PriorityQueue<Melody> pq = new PriorityQueue<>();

        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            String startTime = info[0];
            String endTime = info[1];
            String songName = info[2];
            String song = info[3];

            int playTime = getPlayTime(startTime, endTime);
            String melody = getMelody(playTime, song);
            boolean isPossible = getPossible(melody, m);

            if (isPossible) {
                pq.add(new Melody(i, songName, playTime));
            }
        }

        if (!pq.isEmpty()) {
            return pq.peek().name;
        } else {
            return "(None)";
        }
    }

    private int getPlayTime(String startTime, String endTime) {
        int[] start = Arrays.stream(startTime.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] end = Arrays.stream(endTime.split(":")).mapToInt(Integer::parseInt).toArray();

        int HH = end[0] - start[0];
        int MM = end[1] - start[1];

        if (MM < 0) {
            HH -= 1;
            MM += 60;
        }

        return HH * 60 + MM;
    }

    private String getMelody(int playTime, String song) {
        song = replaceSharp(song);

        // 길이가 더 짧을 경우, 잘라주기
        if (song.length() >= playTime) {
            return song.substring(0, playTime);
        }

        StringBuilder melody = new StringBuilder();
        // 멜로디 길이가 playTime이 될 때까지 추가
        for (int i = 0; i < playTime; i++) {
            melody.append(song.charAt(i % song.length()));
        }

        return melody.toString();
    }

    private boolean getPossible(String melody, String m) {
        m = replaceSharp(m);
        return melody.contains(m);
    }

    private String replaceSharp(String melody) {
        return melody.replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a")
                .replaceAll("B#", "b");
    }

    static class Melody implements Comparable<Melody> {
        int idx;            // 입력 순서
        String name;        // 노래 제목
        int playTime;       // 재생 시간

        public Melody(int idx, String name, int playTime) {
            this.idx = idx;
            this.name = name;
            this.playTime = playTime;
        }

        @Override
        public int compareTo(Melody o) {
            // 재생 시간 비교 - 내림차순
            if (this.playTime != o.playTime) return o.playTime - this.playTime;

            // 입력 순서 비교 - 오름차순
            return this.idx - o.idx;
        }
    }
}
