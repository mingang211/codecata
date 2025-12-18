package codecata10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다.
 * 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다
 */
public class Quest102 {
    public static void main(String[] args) {
        int bridge_length = 2, weight = 10;
        int[] truck_weights = {7, 4, 5, 6};
        System.out.println(new Solution102().solution(bridge_length, weight, truck_weights));

        int bridge_length1 = 100, weight1 = 100;
        int[] truck_weights1 = {10};
        System.out.println(new Solution102().solution(bridge_length1, weight1, truck_weights1));

        int[] truck_weights2 = {10,10,10,10,10,10,10,10,10,10};
        System.out.println(new Solution102().solution(bridge_length1, weight1, truck_weights2));
    }
}

class Solution102 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> waiting_truck = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            int truck = truck_weights[i];
            waiting_truck.add(truck);
        }
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        int currentWeight = 0;
        while (!waiting_truck.isEmpty()) {
            answer++;

            currentWeight -= bridge.poll();
            if (currentWeight + waiting_truck.peek() <= weight) {
                int truck = waiting_truck.poll();
                bridge.add(truck);
                currentWeight += truck;
            } else {
                bridge.add(0);
            }

        }
        return answer + bridge_length;
    }
}