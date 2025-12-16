package codecata10;

import java.util.Arrays;

public class Quest101 {
    public static void main(String[] args) {
        long[] numbers = {2, 7};
        System.out.println(Arrays.toString(new Solution101().solution(numbers)));
    }
}

class Solution101 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long val = numbers[i];

            if (val % 2 == 0) {
                // 짝수인 경우: 마지막 비트가 0이므로 +1만 하면 됨
                answer[i] = val + 1;
            } else {
                // 홀수인 경우: 가장 오른쪽에 있는 0을 찾음
                // 예: 1011(11) -> 0을 찾으면 100(4) 위치임
                long lastZero = ~val & (val + 1);

                // 1. 0이었던 자리를 1로 바꿈 (val | lastZero)
                // 2. 그 오른쪽 자리(lastZero >> 1)를 0으로 바꿈
                answer[i] = (val | lastZero) & ~(lastZero >> 1);
            }
        }
        return answer;
    }
}