package codecata10;

import java.util.LinkedList;
import java.util.Queue;

public class Quest100 {
    public static void main(String[] args) {
        int x = 10, y = 40 , n = 5;
        int x1 = 10, y1 = 40, n1 = 30;
        int x2 = 2, y2 = 5, n2 = 4;
        System.out.println(new Solution100().solution(x, y, n));
        System.out.println(new Solution100().solution(x1, y1, n1));
        System.out.println(new Solution100().solution(x2, y2, n2));
    }
}

class Solution100 {
    public int solution(int x, int y, int n) {
        if (x == y) return 0;

        // 1. Queue와 Visited 배열 초기화
        // Queue: 다음에 탐색할 [현재 숫자, 횟수]를 저장
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, 0});

        // Visited: 이미 방문한 숫자를 기록하여 중복 탐색 및 무한 루프 방지
        boolean[] visited = new boolean[y + 1];
        visited[x] = true;

        // 2. BFS 탐색 시작
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNum = current[0];
            int currentCount = current[1];

            // 3. 가능한 세 가지 연산으로 다음 노드 탐색
            int[] nextNums = {currentNum + n, currentNum * 2, currentNum * 3};

            for (int nextNum : nextNums) {
                // y에 도달했는지 확인
                if (nextNum == y) {
                    return currentCount + 1; // 최소 횟수 발견 즉시 반환
                }

                // 범위 체크 및 방문 여부 확인
                if (nextNum < y && nextNum >= x && !visited[nextNum]) {
                    visited[nextNum] = true;
                    // 다음 횟수는 현재 횟수 + 1
                    queue.offer(new int[]{nextNum, currentCount + 1});
                }
            }
        }

        // 4. y에 도달하지 못한 경우
        return -1;
    }
}
