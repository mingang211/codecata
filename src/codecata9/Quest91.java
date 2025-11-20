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
        List<Integer> answerList = new ArrayList<>();
        int days = 0; // 작업이 완료될 때까지 걸린 일수
        int deployIndex = 0; // 다음에 배포해야 할 기능의 인덱스

        // 모든 기능이 배포될 때까지 반복
        while (deployIndex < progresses.length) {

            // 배포되어야 할 가장 앞 기능의 완료까지 걸리는 일수를 계산
            // progresses[deployIndex] : 현재 진행도
            // 100 - progresses[deployIndex] : 남은 진행도
            // Math.ceil()을 사용하여 남은 일수를 정수 올림 처리
            int remainingProgress = 100 - progresses[deployIndex];
            // 남은 일수 계산
            int requiredDays = (int) Math.ceil((double) remainingProgress / speeds[deployIndex]);

            // 이미 지난 작업 일수보다 새로 계산된 일수가 더 길다면, 작업 일수를 업데이트
            if (requiredDays > days) {
                days = requiredDays;
            }

            // 당일 배포 가능한 기능 카운트하는 변수 선언
            int deployCount = 0;

            // 현재 배포 대기 중인 기능부터 시작하여 순차적으로 확인
            for (int i = deployIndex; i < progresses.length; i++) {
                // 현재 기능이 '가장 앞 기능의 완료일'(days)까지 완료되었는지 확인
                if (progresses[i] + speeds[i] * days >= 100) {
                    deployCount++;
                } else {
                    // 앞의 기능이 완료되었더라도, 현재 기능이 완료되지 않았다면 그 뒤의 기능들은 배포될 수 없음
                    break;
                }
            }

            if (deployCount > 0) {
                answerList.add(deployCount);
                // 배포된 개수만큼 다음 배포 시작 인덱스를 이동.
                deployIndex += deployCount;
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }
}