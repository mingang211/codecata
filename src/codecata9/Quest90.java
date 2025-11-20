package codecata9;

import java.util.HashMap;

/**
 * 주어진 의상 목록에서 서로 다른 의상 종류의 조합으로 만들 수 있는
 * 총 가지 수를 계산 해야 한다.(단, 하나도 입지 않는 경우는 제외)
 */
public class Quest90 {
    public static void main(String[] args) {
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        Solution90 solution90 = new Solution90();
        System.out.println(solution90.solution(clothes));
    }
}

class Solution90 {
    public int solution(String[][] clothes) {
        // 의상 종류별 개수를 저장할 Hashmap 선언
        HashMap<String,Integer> map = new HashMap<>();
        // 입력 배열을 순회하며 의상 종류별 갯수를 카운트
        for(String[] clothe : clothes){
            String type = clothe[1];
            map.put(type, map.getOrDefault(type,0) +1);
        }
        // 조합의 수를 1로 초기화
        int answer = 1;

        // 조합의 수를 계산하여 answer에 저장
        for(String s : map.keySet()){
            answer *= map.get(s)+1;
        }
        // '모든 종류의 옷을 하나도 입지 않는 경우'를 제외
        return answer - 1;
    }
}
