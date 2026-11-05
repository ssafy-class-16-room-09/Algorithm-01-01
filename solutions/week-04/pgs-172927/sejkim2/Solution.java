import java.util.*;

class Solution {
    int len;
    int answer;
    int[][] costTable = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    Map<String, Integer> map;
    
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        len = minerals.length;
        map = new HashMap<>();
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        
        dfs(picks, minerals, 0, 0);
        return answer;
    }
    
    void dfs(int[] picks, String[] minerals, int cost, int idx) {
        //idx는 minerals 크기만큼 가능
        //종료 조건은
        //1. 광물을 다 캠 (idx >= len)
        //2. 곡갱이 다 씀 (비트 연산 | 을 사용해서 하나라도 0 이 아니면 0이 안나오게 함)
        if (idx >= len || (picks[0] | picks[1] | picks[2]) == 0) {
            answer = Math.min(answer, cost);
            return;
        }
        
        //만약 더 캘수 있는데 이미 비용이 이전에 구한 최소값보다 크면 더이상 볼 필요 없음
        if (cost > answer)
            return;
        
        for(int i = 0; i<3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                int j = idx;
                int nextCost = cost;
                //비용 계산 해주고 dfs 재호출
                while (j < len && j < idx + 5) {
                    nextCost += costTable[i][map.get(minerals[j])];
                    j++;
                }
                dfs(picks, minerals, nextCost, j);
                picks[i]++;
            }
        }
    }
}