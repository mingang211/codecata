package codecata9;

import java.util.*;

/**
 * 각 기능의 개발 진도와 개발 속도가 주어졌을 때, 각 배포마다 몇 개의
 * 기능이 배포되는지를 계산 해야 한다.
 * [조건]
 * 1. 100% 완료되어야 배포될 수 있다.
 * 2. 뒤에 있는 기능이 완료되더라도 앞에 있는 기능이 완료되어야 배포가 가능하다.
 */
public class Quest91 {
    public static void main(String[] args) {
        int[] progress = {93, 30, 55};
        int[] speed = {1, 30, 5};
        Solution91 solution91 = new Solution91();
        System.out.println(Arrays.toString(solution91.solution(progress, speed)));
    }
}

class Solution91 {
    public int[] solution(int[] progresses, int[] speeds) {
        // 완료 일수를 저장할 Queue를 LinkedList로 구현.
        Queue<Integer> daysQueue = new LinkedList<>();

        // 각 기능별 완료까지 걸리는 최소 일수를 계산하여 Queue에 추가
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i];

            // Math.ceil을 사용하여 남은 일수를 올림 처리
            int requiredDays = (int) Math.ceil((double) remainingProgress / speeds[i]);
            daysQueue.offer(requiredDays);
        }

        List<Integer> answerList = new ArrayList<>();

        // Queue가 비어있을 때까지 반복
        while (!daysQueue.isEmpty()) {
            int currentMaxDay = daysQueue.poll();
            int deployCount = 1; // 첫 번째 기능은 무조건 배포 그룹에 포함되므로 1부터 시작

            // Queue의 다음 요소들을 확인하며 currentMaxDay보다 일찍 완료되었거나 같은 날 완료된 기능들을 그룹에 포함
            while (!daysQueue.isEmpty() && daysQueue.peek() <= currentMaxDay) {
                daysQueue.poll(); // Queue에서 제거 (그룹에 포함 완료)
                deployCount++;    // 배포 개수 증가
            }

            // 한 번에 배포된 기능의 개수를 결과 리스트에 추가
            answerList.add(deployCount);
        }

        // List를 int 배열로 변환하여 반환
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}