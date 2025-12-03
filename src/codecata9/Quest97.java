package codecata9;

import java.util.HashMap;
import java.util.Map;

public class Quest97 {
    public static void main(String[] args) {
        String word = "EIO";
        System.out.println(new Solution97().solution(word));
    }
}

class Solution97 {
    public int solution(String word) {
        //자리별 가중치 (등비수열의 합)
        int[] weights = {781, 156, 31, 6, 1};

        //모음 순서 매핑 (A=0, E=1, I=2, O=3, U=4)
        Map<Character, Integer> vowelIndex = new HashMap<>();
        vowelIndex.put('A', 0);
        vowelIndex.put('E', 1);
        vowelIndex.put('I', 2);
        vowelIndex.put('O', 3);
        vowelIndex.put('U', 4);

        int answer = 0;

        //단어의 각 글자를 순회하며 순서 계산
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            int charIdx = vowelIndex.get(current);
            int weight = weights[i];

            answer += (charIdx * weight) + 1;
        }

        return answer;
    }
}
