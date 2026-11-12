import java.util.*;
import java.io.*;

class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[][] grid;

    static int[][] dirs = {
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static boolean isWall(int r, int c) {
        return grid[r][c] == 0;
    }
    
    // BFS 상태 저장 클래스
    static record State(
        int r, int c,
        int walls
    ) {}

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        grid = new int[N][];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().trim().toCharArray();
            grid[i] = new int[N];
            for (int j = 0; j < N; j++) grid[i][j] = line[j] - '0';
        }

        // 우선순위 큐를 사용한 BFS: 벽을 가장 적게 부순 상태부터 뽑기(= 검은 방을 가장 적게 바꾼 상태)
        PriorityQueue<State> q = new PriorityQueue<>(
            Comparator.comparingInt((State s) -> s.walls())
        );
        q.offer(new State(0, 0, 0));

        // (0, 0) 방문처리
        boolean[] visited = new boolean[N * N + 1];
        visited[0] = true;

        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            State curr = q.poll();
            int r = curr.r(), c = curr.c(), walls = curr.walls();

            // 목적지에 도착하는 순간 탐색 종료
            if (r == N - 1 && c == N - 1) {
                System.out.println(walls);
                return;
            }

            // 현재 칸의 4방 탐색
            for (int[] dir: dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                // 이웃이 맵 밖이거나 이미 방문한 경우 스킵
                if (!isInside(nr, nc) || visited[nr * N + nc]) continue;

                visited[nr * N + nc] = true;
                // 이웃이 검은 방인 경우 (부순 방 +1), 아니면 그대로 가기
                int newWalls = isWall(nr, nc) ? walls + 1: walls;

                q.offer(new State(nr, nc, newWalls));
            }
        }
    }
}