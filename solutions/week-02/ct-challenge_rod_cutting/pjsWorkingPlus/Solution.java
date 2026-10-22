import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] profit = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            profit[i] = sc.nextInt();
            dp[i] = profit[i];
        }

        for (int length = 1; length <= n; length++) {
            for (int piece = 1; piece <= length; piece++) {
                dp[length] = Math.max(dp[length], dp[length - piece] + profit[piece]);
            }
        }

        System.out.println(dp[n]);
        sc.close();
    }
}
