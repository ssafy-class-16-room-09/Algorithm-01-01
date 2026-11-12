
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int MAX_VALUE = 100;
    static final int INF = 1_000_000_000;

    static int n;
    static int[][] grid;
    static int[][][] dp;

    static final int[] dr = {0, 1};
    static final int[] dc = {1, 0};

    static void init(Scanner sc) {
        n = sc.nextInt();
        grid = new int[n][n];
        dp = new int[n][n][MAX_VALUE + 1];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                grid[r][c] = sc.nextInt();
                Arrays.fill(dp[r][c], INF);
            }
        }
    }

    static int solve() {
        // 시작점 초기화
        int start = grid[0][0];

        // (0, 0) ~ (0, 0)까지의 경로에서 최솟값이 start일때 최댓값은 start
        dp[0][0][start] = start;

        // 두 경로와 경로 내 최소, 최대값이 있다.
        // A(1, 10), B(1, 20)
        // 이 경로에 이어서 두 칸 x, y를 이을 때 값이 어떻든 A를 고르는게 개이득이다
        // 이를 dp[r][c][min] = max 로 잡고 DP bottom-up

        // 각 칸과 가능한 최솟값을 순회
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // 최솟값을 1부터 늘려가며 bottom-up
                for (int m = 1; m <= MAX_VALUE; m++) {

                    // 도달 불가능한 상태
                    if (dp[r][c][m] == INF) continue;

                    int currentMax = dp[r][c][m];

                    // 오른쪽, 아래쪽으로 이동
                    for (int d = 0; d < 2; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= n || nc >= n) continue;

                        // 이웃 칸의 값
                        int x = grid[nr][nc];
                        // 지금까지의 경로 내 최솟값 vs 이웃칸 중 최소 찾기
                        int nextMin = Math.min(m, x);
                        // 지금까지의 경로 내 최댓값 vs 이웃칸 중 최대 찾기
                        int nextMax = Math.max(currentMax, x);

                        // (0, 0) ~ (nr, nc)의 최적의 경로에서 nextMin 값을 최소로 가질 때,
                        // 최댓값은 이미 계산된적이 있는 값 vs nextMax 중 더 작은 값(차가 작으므로)
                        dp[nr][nc][nextMin] = Math.min(
                            dp[nr][nc][nextMin],
                            nextMax
                        );
                    }
                }
            }
        }

        // 도착점에서 최소 차이 탐색
        int ans = INF;

        // (0, 0) ~ (n-1, n-1)의 경로 중 (최소: m, 최대: dp[][][m]) 값 중
        // 차가 최소인 값 찾기
        for (int m = 1; m <= MAX_VALUE; m++) {
            int max = dp[n - 1][n - 1][m];

            if (max == INF) continue;

            ans = Math.min(ans, max - m);
        }

        return ans;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            init(sc);
            System.out.println(solve());
        }
    }
}
