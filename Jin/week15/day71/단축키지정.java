package Jin.week15.day71;

import java.io.*;
import java.util.*;

public class 단축키지정 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. input 입력받기
        int N = Integer.parseInt(br.readLine());

        String[] option = new String[N];
        for (int i = 0; i < N; i++) {
            option[i] = br.readLine();
        }

        // 2. 옵션을 순회하며 단축키 지정
        // 2-1. set 자료구조를 사용해 단축키를 중복 없이 지정할 수 있게 하기
        Set<Character> useAlphabet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            // 2-2. split 메서드로 단어 별로 분리
            String o = option[i];
            String[] words = o.split(" ");

            boolean isPossibleAlphabet = false;
            int idx = 0;

            // 2-3. 단축키 지정하기
            for (int j = 0; !isPossibleAlphabet && j < words.length; j++) {
                // 첫 글자가 단축키로 가능한지 탐색
                char first = Character.toUpperCase(words[j].charAt(0));

                if (!useAlphabet.contains(first)) {
                    isPossibleAlphabet = true;
                    useAlphabet.add(first);
                    break;
                }

                // 다음 단어의 첫 번째 인덱스는 현재 단어의 길이 + 1(공백)
                idx += words[j].length() + 1;
            }

            for (int j = 0; !isPossibleAlphabet && j < o.length(); j++) {
                // 두 번째 글자 이후부터 단축키로 가능한지 탐색
                // = 어차피 첫 글자들은 if 조건문 통과 못하기 때문에 단어별로 처음부터 끝까지 확인하면 됨
                char c = Character.toUpperCase(o.charAt(j));

                if (c != ' ' && !useAlphabet.contains(c)) {
                    isPossibleAlphabet = true;
                    idx = j;
                    useAlphabet.add(c);
                }
            }

            // 옵션에서 단축키가 등록된 경우, 대괄호를 붙여 저장
            if (isPossibleAlphabet) {
                String result = "";
                if (idx == 0) {
                    result = "[" + o.charAt(idx) + "]" + o.substring(idx + 1);
                } else {
                    result = o.substring(0, idx) + "[" + o.charAt(idx) + "]" + o.substring(idx + 1);
                }

                option[i] = result;
            }
        }

        for (int i = 0; i < N; i++) {
            bw.write(option[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}