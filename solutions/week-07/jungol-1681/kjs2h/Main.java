import java.util.*;
import java.io.*;

public class Main {

    static int N, MIN;
    static int[][] cost;
    static boolean[] visited;
    static int totalCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());
        cost = new int[N][N];
        visited = new boolean[N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                cost[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        MIN = Integer.MAX_VALUE;
        totalCost = 0;
        visited[0] = true;

        dfs(0, 1);

        System.out.println(MIN);
    }

    public static void dfs(int from, int depth) {
        if (totalCost >= MIN) return;

        if (depth == N) {
            if (cost[from][0] != 0) {
                MIN = Math.min(MIN, totalCost + cost[from][0]);
            }
            return;
        }

        int[] curr = cost[from];

        for (int i = 0; i < N; i++) {
            if (visited[i] || curr[i] == 0) continue;

            visited[i] = true;
            totalCost += curr[i];

            dfs(i, depth + 1);

            totalCost -= curr[i];
            visited[i] = false;
        }
    }
}
