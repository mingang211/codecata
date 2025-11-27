package codecata9;

/** 각 숫자에 + 또는 - 부호를 붙여 만들 수 있는 합계 중
 * target과 일치하는 경우가 몇 가지인지 찾는 문제 (DFS/BFS 완전 탐색)
 */

public class Quest94 {
    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};
        int target = 4;
        Solution94 solution94 = new Solution94();
        System.out.println(solution94.solution(numbers, target));
    }
}


class Solution94 {
    int answer;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);

        return answer;
    }

    public  void dfs(int[] numbers, int target, int index, int currentSum) {
        if(index == numbers.length) {
            if(currentSum == target) {
                answer++;
            }
            return;
        } else {
            dfs(numbers, target, index + 1, currentSum + numbers[index]);
            dfs(numbers, target, index + 1, currentSum - numbers[index]);
        }
    }
}
