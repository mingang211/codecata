package codecata9;

public class Quest95 {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;
        Solution95 solution95 = new Solution95();
        System.out.println(solution95.solution(n, k));
    }
}

class Solution95 {
    public int solution(int n, int k) {
        int answer = 0;
        String changeNum = changeNum(n, k);
        String[] numbers = changeNum.split("0");
        for (String numStr : numbers){
            if(!numStr.isEmpty()){
                long num = Long.parseLong(numStr);

                if(checkPrime(num)){
                    answer++;
                }
            }
        }
        return answer;
    }

    public String changeNum (int n, int k){
        return Integer.toString(n,k);
    }

    public boolean checkPrime(long n){
        if(n <= 1){
            return false;
        }

        long limit = (long) Math.sqrt(n);
        for (long i = 2; i <= limit; i++) {
            if(n % i == 0){
                return false;
            }
        }
        return  true;
    }
}