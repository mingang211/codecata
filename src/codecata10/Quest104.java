package codecata10;

import java.util.HashSet;
import java.util.Set;

public class Quest104 {
    public static void main(String[] args) {
        String numbers = "17";
        String numbers1 = "011";
        System.out.println(new Solution104().solution(numbers));
        System.out.println(new Solution104().solution(numbers1));
    }
}


class Solution104 {
    public int solution(String numbers) {
        int answer = 0;
        Set<Integer> numberSet = new HashSet<>();
        recursive("", numbers, new boolean[numbers.length()], numberSet);
        for (int number : numberSet) {
            if (checkPrime(number)) {
                answer++;
            }
        }
        return answer;
    }

    public void recursive(String currentStr, String numbers, boolean[] visited, Set<Integer> numberSet) {
        if (!currentStr.equals("")) {
            numberSet.add(Integer.parseInt(currentStr));
        }
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                recursive(currentStr + numbers.charAt(i), numbers, visited, numberSet);
                visited[i] = false;
            }
        }
    }

    public boolean checkPrime(long n) {
        if (n <= 1) {
            return false;
        }

        long limit = (long) Math.sqrt(n);
        for (long i = 2; i <= limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}