package codecata9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Quest99 {
    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        int[] topping1 = {1, 2, 3, 1, 4};
        System.out.println(new Solution99().solution(topping));
        System.out.println(new Solution99().solution(topping1));
    }
}

class Solution99 {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> rightCounts = new HashMap<>();
        for(int toppingNum : topping) {
            rightCounts.put(toppingNum, rightCounts.getOrDefault(toppingNum, 0) + 1);
        }
        Set<Integer> leftCounts = new HashSet<>();

        for (int i = 0; i < topping.length - 1; i++) {
            int currentTopping = topping[i];
            leftCounts.add(currentTopping);
            rightCounts.put(currentTopping, rightCounts.get(currentTopping) - 1);

            if(rightCounts.get(currentTopping) == 0) {
                rightCounts.remove(currentTopping);
            }

            if(leftCounts.size() == rightCounts.size()) {
                answer++;
            }
        }
        return answer;
    }
}