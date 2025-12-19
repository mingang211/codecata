package codecata10;

import java.util.Arrays;

/**
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요
 */
public class Quest103 {
    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        int[] numbers1 = {3, 30, 34, 5, 9};
        System.out.println(new Solution103().solution(numbers));
        System.out.println(new Solution103().solution(numbers1));
    }
}

class Solution103 {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);

        }
        Arrays.sort(strNumbers, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        if (strNumbers[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strNumbers) {
            sb.append(s);
        }
        answer = sb.toString();
        return answer;
    }
}