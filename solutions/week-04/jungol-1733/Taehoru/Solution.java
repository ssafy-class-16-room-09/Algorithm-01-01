import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
도움을 받은 부분 - 오목 특성 상 꺾이는 지점에 대해 어떻게 해결해야할지 생각이 안남
어떻게 해결했는지 - prevR, prevC와 bfs에 인자 2개 더 추가해서 이 값을 전달하고 고정해서 해결한듯,,,? 

상하좌우 대각선 방향을 각각 만들어두고
보드판 19x19 만들고 방문 3차원 배열로 만들어서 방향까지 관리한다
메인 함수에서 0이면 그냥 패스하고 흑백이면 바로 시작점으로 지정

bfs에 인자가 5개 들어가는데 시작점행열, 이동할행열, 방향이다
1. 보드에서 1이나 2인 돌을 시작점 후보로 잡는다.
2. 8방향 중 하나를 고른다. 시작점 바로 뒤에 같은 색 돌이 있으면 줄의 중간이므로 건너뛴다.
3. 선택한 방향의 다음 돌만 큐에 넣으며 같은 색 돌을 센다. 방문 기록은 방향별로 남기고, 가장 왼쪽 돌의 좌표도 기억한다.
4. 직선이 끝났을 때 정확히 5개면 성공으로 반환한다. 아니면 다른 방향과 시작점을 검사한다.
*/





public class Solution {
    static final int N = 19;
    
    // dr1, dr2 = 상하좌우, dr2, dc2 = 대각선
    static int[] dr1 = {-1, 1, 0, 0};
    static int[] dr2 = {-1, -1, 1, 1};
    static int[] dc1 = {0, 0, -1, 1};
    static int[] dc2 = {-1, 1, -1, 1};

    static int[][] board = new int[N][N];
    static boolean[][][] visited = new boolean[8][N][N];

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }



    static int[] bfs(int startR, int startC, int nextR, int nextC, int direction) {
        if (visited[direction][startR][startC]) {
            return null;
        }


        int color = board[startR][startC];
        
        // 진행 방향 반대쪽에 같이 돌이 있으면 pass
        int prevR = startR - nextR;
        int prevC = startC - nextC;

        if (inRange(prevR, prevC) && board[prevR][prevC] == color) {
            return null;
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startR, startC});
        visited[direction][startR][startC] = true;
        
        int count = 0;
        int leftR = startR;
        int leftC = startC;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];

            count++;

            // 더 왼쪽에 있는 돌을 만나면
            if (c < leftC || (c == leftC && r < leftR)) {
                leftR = r;
                leftC = c;
            }

            // 탐색
            int nr = r + nextR;
            int nc = c + nextC;

            if (!inRange(nr, nc)) {
                continue;
            }

            if (visited[direction][nr][nc] || board[nr][nc] != color) {
                continue;
            }

            visited[direction][nr][nc] = true;
            q.offer(new int[]{nr, nc});
        }

            // 조건 만족
            if (count == 5) {
                return new int[]{color, leftR, leftC};
            }
            return null;
        }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int c= 0; c < N; c++) {
            for (int r=0; r < N; r++) {
                if (board[r][c] == 0) continue;

                for (int group = 0; group < 2; group++) {
                    int[] dr = (group == 0) ? dr1 : dr2;
                    int[] dc = (group == 0) ? dc1 : dc2;

                    for (int dir = 0; dir < 4; dir++) {
                        int direction = group * 4 + dir;
                        int[] result = bfs(r, c, dr[dir], dc[dir], direction);

                        if (result != null) {
                            System.out.println(result[0]);
                            System.out.println(
                                (result[1]+1) + " " + (result[2] + 1)
                                );
                                return;
                        }
                    }
                }
            }
        }
        // 아무것도 못찾으면 0 뱉기
        System.out.println(0);
    }
}