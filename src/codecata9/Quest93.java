package codecata9;

/**
 * 현재 피로도와 각 던전의 정보를 이용하여 탐험할 수 있는 최대 던전 수를 계산해야 한다.
 *
 * [조건]
 * 1. 던전을 탐험하려면 '최소 필요 피로도' 이상의 현재 피로도가 있어야 한다.
 * 2. 던전을 탐험하면 '소모 피로도'만큼 현재 피로도가 줄어든다.
 * 3. 탐험 순서에 따라 탐험 가능한 던전의 개수가 달라지므로, 가능한 모든 순서를 고려해야 한다.
 */

public class Quest93 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons ={{50,40},{80,20},{30,10}};
        Solution93 solution = new Solution93();
        System.out.println(solution.solution(k, dungeons));

    }
}

class Solution93 {

    // 정답을 저장할 변수 (탐험 가능한 최대 던전 수)
    private int answer = 0;

    // 방문 여부를 체크할 배열
    private boolean[] visited;

    /**
     * @param k 초기 피로도
     * @param dungeons [최소 필요 피로도, 소모 피로도] 배열
     * @return 탐험할 수 있는 최대 던전 수
     */
    public int solution(int k, int[][] dungeons) {
        // 던전 수만큼 방문 배열 초기화
        visited = new boolean[dungeons.length];

        // DFS 시작 (현재 피로도 k, 현재까지 탐험한 던전 수 0)
        dfs(k, 0, dungeons);

        return answer;
    }

    /**
     * DFS 재귀 함수
     * @param currentFatigue 현재 남아있는 피로도
     * @param count 현재까지 탐험한 던전 수
     * @param dungeons 던전 정보 배열
     */
    private void dfs(int currentFatigue, int count, int[][] dungeons) {
        // 1. 매 호출마다 최대 던전 수 업데이트
        answer = Math.max(answer, count);

        // 2. 모든 던전을 순회
        for (int i = 0; i < dungeons.length; i++) {
            int minRequired = dungeons[i][0]; // 최소 필요 피로도
            int fatigueCost = dungeons[i][1]; // 소모 피로도

            // 3. 탐험 조건 확인
            // (1) 아직 방문하지 않았고, (2) 현재 피로도가 최소 필요 피로도 이상
            if (!visited[i] && currentFatigue >= minRequired) {
                // 탐험 가능:

                // (a) 방문 처리 (상태 변경)
                visited[i] = true;

                // (b) 다음 깊이로 재귀 호출
                // 피로도는 소모 피로도만큼 줄어들고, count는 1 증가
                dfs(currentFatigue - fatigueCost, count + 1, dungeons);

                // (c) 백트래킹 (상태 복원)
                // 다른 경로를 탐색하기 위해 방문 상태를 되돌림
                visited[i] = false;
            }
        }
    }
}
