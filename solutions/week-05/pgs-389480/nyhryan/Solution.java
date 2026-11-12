import java.util.*;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int[][] info, int n, int m) {
        int items = info.length;
        int lim_a = n, lim_b = m;
        
        // dp[i][L] = i번째 물건 고려할 때, A의 흔적 합이 딱 L일 때
        // 그때의 B의 흔적 최솟값
        int[][] dp = new int[items+1][lim_a];
        for (int r = 0; r < dp.length; r++) {
            Arrays.fill(dp[r], INF); // 초기상태 = 무한대로 불가능을 표시
        }
        
        // 아무 물건도 안 훔칠 때, A의 흔적 합은 딱 0이고, 그때의 B의 최소 흔적값도 0
        dp[0][0] = 0;
        
        // 첫번째 물건부터 고려
        for (int i = 1; i <= items; i++) {
            int[] item = info[i - 1];
            int trace_a = item[0], trace_b = item[1];

            // A가 남기는 흔적이 0부터 걸리지 않는 제한 lim_a - 1 까지 Bottom-up 계산
            for (int L = 0; L < lim_a; L++) {
                if (dp[i - 1][L] == INF) continue;
                
                // i번째를 A가 훔쳐가는 것을 고려
                if (L + trace_a < lim_a) {
                    // i 번째를 A가 훔쳐감 = B의 흔적합은 안 늘어남
                    dp[i][L + trace_a] = dp[i - 1][L];
                }
                
                // i번째를 B가 훔쳐가는 것을 고려
                if (dp[i - 1][L] + trace_b < dp[i][L]) {
                    // (i번째를 B가 훔치지 않는 경우, 훔치는 경우) 중 최솟값
                    dp[i][L] = Math.min(dp[i][L], dp[i - 1][L] + trace_b);
                }
            }
        }
        
        // 모든 물건을 고려했을 때, B의 흔적 합이 제한 미만이면서(= 체포 안되면서)
        // 가장 작은 A의 흔적 합을 리턴
        for (int a = 0; a < lim_a; a++) {
            if (dp[items][a] < lim_b) return a;
        }
        // 모두 붙잡히는 경우
        return -1;
    }
}