package codecata9;

import java.util.Arrays;
import java.util.Stack;

public class Quest98 {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 3, 5};
        int[] numbers1 = {9, 1, 5, 3, 6, 2};
        System.out.println(Arrays.toString(new Solution98().solution(numbers)));
        System.out.println(Arrays.toString(new Solution98().solution(numbers1)));
    }
}

class Solution98 {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = -1;
        }

        // 스택에는 '아직 오큰수를 찾지 못한' 원소의 인덱스를 저장
        Stack<Integer> stack = new Stack<>();

        // 배열을 순회 (i는 현재 검토하는 인덱스)
        for (int i = 0; i < n; i++) {

            // 스택이 비어있지 않고,
            // 스택의 최상단 인덱스가 가리키는 원소(numbers[stack.peek()])보다
            //  현재 원소(numbers[i])가 더 크다면 (오큰수를 찾은 경우)
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                // 오큰수를 찾은 인덱스 (idx)를 스택에서 꺼냄
                int idx = stack.pop();

                // 해당 인덱스의 오큰수는 현재 원소 numbers[i]임
                answer[idx] = numbers[i];
            }

            // 현재 인덱스 i를 스택에 push
            // (i의 오큰수는 i보다 뒤에 있는 수 중 하나일 것이므로)
            stack.push(i);
        }

        return answer;
    }
}
