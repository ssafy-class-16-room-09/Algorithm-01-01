import java.util.*;

class Solution {

    static Map<Integer, Integer>[] graph;
    static boolean[] virus;
    static int MAX;
    static int N;

    public int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        graph = new HashMap[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new HashMap<>();
        }

        // 비트마스킹(파이프 종류 1, 2, 3 -> 비트 1, 2, 4)
        for (int[] edge : edges) {
            int u1 = edge[0];
            int u2 = edge[1];
            int value = edge[2];
            int bit = 0;

            switch (value) {
                case 1:
                    bit = 1;
                    break;
                case 2:
                    bit = 2;
                    break;
                case 3:
                    bit = 4;
                    break;
            }

            graph[u1].put(u2, graph[u1].getOrDefault(u2, 0) | bit);
            graph[u2].put(u1, graph[u2].getOrDefault(u1, 0) | bit);
        }

        virus = new boolean[N + 1];
        virus[infection] = true; // 초기 감염 설정
        MAX = 1;

        dfs(0, k, 0);

        return MAX;
    }

    public void dfs(int depth, int limit, int prevPipe) {
        // ============기저조건=============
        if (depth == limit) {
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                if (virus[i]) sum += 1;
            }
            MAX = Math.max(MAX, sum);
            return;
        }
        // ============기저조건=============

        // ============분기처리=============
        // A(1), B(2), C(4) 파이프 순서대로 열기
        for (int pipe : new int[]{1, 2, 4}) {
            if (pipe == prevPipe) continue; // 방금 연 파이프는 연속으로 열지 않음

            // 이번 행동으로 영향 받은 노드들이 인덱스인 배열
            boolean[] curr = open(pipe);
            for (int i = 1; i <= N; i++) {
                if (curr[i]) virus[i] = true;
            }

            dfs(depth + 1, limit, pipe);

            // 백트래킹 (이번 턴에 새로 감염된 노드만 다시 false로 복구)
            for (int i = 1; i <= N; i++) {
                if (curr[i]) virus[i] = false;
            }
        }
        // ============분기처리=============
    }


    public boolean[] open(int pipe) {
        boolean[] curr = new boolean[N + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        // 감염된 노드들을 모두 출발점으로
        for (int i = 1; i <= N; i++) {
            if (virus[i]) dq.offer(i);
        }

        // ============BFS=============
        while (!dq.isEmpty()) {
            int now = dq.poll();

            for (Map.Entry<Integer, Integer> each : graph[now].entrySet()) {
                int next = each.getKey();
                int edgePipe = each.getValue();

                // 기존에 감염된 적 없고(!virus)
                // 이번 턴에 아직 방문 안 했고(!curr)
                // 해당 파이프가 연결되어 있다면 (비트마스킹)
                if (!virus[next] && !curr[next] && (edgePipe & pipe) != 0) {
                    curr[next] = true;
                    dq.offer(next);
                }
            }
        }
        // ============BFS=============

        return curr;
    }
}
