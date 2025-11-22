package codecata9;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 프로세스 실행 순서 시뮬레이션을 통해 특정 프로세스가 몇 번째로 실행되는지 계산합니다
 */

public class Quest92 {
    public static void main(String[] args) {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        Solution92 solution92 = new Solution92();
        System.out.println(solution92.solution(priorities, location));
    }
}

class Solution92 {
    // 내부 클래스: 프로세스의 우선순위와 초기 위치를 함께 관리
    class Item {
        int priority;
        int initialLocation;
        public Item(int priority, int initialLocation) {
            this.priority = priority;
            this.initialLocation = initialLocation;
        }
    }

    public int solution(int[] priorities, int location) {
        // 일반 큐와 PriorityQueue 초기화
        Queue<Item> processQueue = new LinkedList<>();
        // PriorityQueue를 내림차순으로 설정하여 최댓값이 peek()으로 나오게 함
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            processQueue.add(new Item(priorities[i], i));
            maxPriorityQueue.add(priorities[i]);
        }

        int answer = 0;

        // 프로세스 실행 시뮬레이션
        while (!processQueue.isEmpty()) {
            Item current = processQueue.poll(); // 일반 큐의 맨 앞 프로세스를 꺼냅니다.

            // 현재 꺼낸 프로세스의 우선순위가 전체 최고 우선순위인지 확인
            if (current.priority == maxPriorityQueue.peek()) {
                // 실행
                maxPriorityQueue.poll(); // 최고 우선순위 값 제거
                answer++; // 실행 순서 증가

                // 정답 체크
                if (current.initialLocation == location) {
                    return answer;
                }
            } else {
                // 최고 우선순위가 아니면 큐의 맨 뒤로 다시 삽입
                processQueue.add(current);
            }
        }
        return answer;
    }
}