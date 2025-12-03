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
        final int MAX_LENGTH = 5; // 단어 최대 길이
        final int NUM_VOWELS = 5; // 모음 개수 (A, E, I, O, U)

        // 등비수열의 합을 for문으로 계산하여 weights 배열 채우기
        int[] weights = new int[MAX_LENGTH];

        for (int i = 0; i < MAX_LENGTH; i++) {
            int lengthToSum = MAX_LENGTH - i;
            int currentWeight = 0;

            // 5^0 부터 5^(lengthToSum - 1) 까지 합산
            for (int j = 0; j < lengthToSum; j++) {
                // Math.pow는 double을 반환하므로 int로 형변환
                currentWeight += (int) Math.pow(NUM_VOWELS, j);
            }
            weights[i] = currentWeight;
        }

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
