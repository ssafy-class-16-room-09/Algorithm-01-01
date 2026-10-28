import java.util.*;

//이 코드에는 비밀이 존재합니다.
//비밀을 알아낸 사람에게는 상품이...

class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        int len = enemy.length;
        int[][] dp = new int[len + 1][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        dp[0][0] = n;
        // dp[0][0] = n-enemy[0];
        // dp[0][1] = n;
        //         
        // dp[1][0] = dp[0][0] - enemy[1];
        // dp[1][1] = Math.max(dp[0][1] - enemy[1], dp[0][0]);
        // dp[1][2] = n;
        for (int stage = 1; stage <= len; stage++) {
            boolean flag = false;

            for (int immune = 0; immune <= Math.min(stage, k); immune++) {

                // 무적권을 아낌
                if (dp[stage - 1][immune] >= enemy[stage - 1]) {
                    
                    dp[stage][immune] = dp[stage - 1][immune] - enemy[stage - 1];
                    
                }

                // 무적권을 사용함
                if (immune > 0 && dp[stage - 1][immune - 1] >= 0) {
                    
                    dp[stage][immune] = Math.max(
                        dp[stage][immune],
                        dp[stage - 1][immune - 1]
                    );
                    
                }
                // 모든 경우에 죽지 않았다면 계속 진행
                if (dp[stage][immune] >= 0) flag = true;
            }

            if (!flag){
                System.out.println(Arrays.deepToString(dp));
                return stage - 1;
            } 
        }
        
        System.out.println(Arrays.deepToString(dp));
        
        return len;
    }
}