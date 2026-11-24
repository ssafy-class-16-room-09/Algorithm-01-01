import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int nextInt() {
        try {
            return Integer.parseInt(nextLine().trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    static int[] nextInts() {
        var st = new StringTokenizer(nextLine());
        return Collections.list(st).stream()
            .mapToInt(o -> Integer.parseInt(o.toString()))
            .toArray();
    }

    static int N;
    static int[][] adj;
    
    public static void main(String[] args) {
        N = nextInt();
        adj = new int[N][];
        for (int i = 0; i < N; i++) {
            adj[i] = nextInts();
        }

        // 회사(문제에서는 1번, 여기서는 0인덱스라 0번)부터 탐색 시작
        int start = 0;
        dfs(0, start, start, 1 << start, 0);
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }

    record Result(boolean success, int total) {
        static final Result FAIL = new Result(false, -1);
        static Result ok(int total) {
            return new Result(true, total);
        }
    }

    static int ans = Integer.MAX_VALUE;

    static void dfs(int depth, int start, int curr, int visited, int totalCost) {
        // 알려진 최소비용보다 커지는 DFS 경로라면 가지치기
        if (totalCost >= ans) return;
        
        // [기저] N 번 방문하고, 제자리로 돌아온 경우
        // 아래의 for 문에서 "N번째일때는 제자리로 돌아오는 경우" 만 DFS를 진행해서
        // 여기 if문에 그 조건은 따로 안 적음
        if (depth == N) {
            ans = Math.min(ans, totalCost);
            return;
        }

        // 비트마스크 플래그를 사용한 백트래킹 DFS
        for (int i = 0; i < N; i++) {
            if (adj[curr][i] == 0 // 갈 수 없는 장소이거나
    
                // OR 한바퀴 안 돌았는데,  i가 방문한 칸이면 스킵
                || (depth + 1 < N && (visited & (1 << i)) != 0)
    
                // OR 다음 번째가 한 바퀴 도는 거리인데, 다음칸이 시작점 아니면 한바퀴 안돈거니깐 이 칸은 스킵
                || (depth + 1 == N && i != start)
            ) continue;

            // 백트래킹
            // (depth+1) 번째 방문한 장소는 i
            dfs(depth + 1, start, i, visited | (1 << i), totalCost + adj[curr][i]);
        }
    }
}
