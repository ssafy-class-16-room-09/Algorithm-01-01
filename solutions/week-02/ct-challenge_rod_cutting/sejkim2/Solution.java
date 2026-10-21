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

        //dp 정의 : i 번째까지 원소를 포함하는 최대가치
        //현재 원소 길이가 1이면 길이 1일 때 가치 + 이전길이 중 최대가치(dp[i-1])이고
        //현재 원소 길이가 2이면 길이 2일 때 가치 + 이전 길이 중 최대가치(dp[i-2])이고 이걸 i==0일 때까지 반복하며 최대가치 갱신
        for (int i = 0; i < n; i++) {
            dp[i] = profit[i];
            for (int j = 0; j < i; j++)
                dp[i] = Math.max(dp[i], profit[j] + dp[i - j - 1]);    
        }
        System.out.println(dp[n - 1]);
    }
}
