import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            profit[i] = sc.nextInt();
        }
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = profit[i];
            for (int j = 0; j < i; j++)
                dp[i] = Math.max(dp[i], profit[j] + dp[i - j - 1]);
        }
        System.out.println(dp[n - 1]);
    }
}