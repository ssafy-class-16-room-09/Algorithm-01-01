import java.util.*;

class Solution {
    static final char EMPTY = '.';
    static final char WALL = 'D';
    static final char ROBOT = 'R';
    static final char GOAL = 'G';
    
    static char[][] grid;
    static int rows, cols;
    
    static int[] pos_robot = new int[2];
    static int[] pos_goal = new int[2];
    
    static int[][] dirs = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    
    static boolean[][] visited;
    
    static boolean isInside(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
    
    static boolean isGoal() {
        return pos_robot[0] == pos_goal[0] && pos_robot[1] == pos_goal[1];
    }
    
    record State(
        int r, int c, int cost, int lastDir
    ) {}

    public int solution(String[] board) {
        setup(board);
        
        // 비용=진행한 횟수가 작은 순으로 PQ에서 뽑기
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::cost));
        pq.add(new State(pos_robot[0], pos_robot[1], 0, -1));
        
        // 초기 Visit 처리
        for (int i = 0; i < dirs.length; i++) {
            visited[pos_robot[0] * cols + pos_robot[1]][i] = true;
        }
                
        while (!pq.isEmpty()) {
            State curr = pq.poll();
            
            int r = curr.r(), c = curr.c(), cost = curr.cost();
            int lastDir = curr.lastDir();
            
            // Goal에 도착하면 탐색 종료            
            if (r == pos_goal[0] && c == pos_goal[1]) {
                return cost;
            }
            
            // 4방 탐색
            for (int i = 0; i < dirs.length; i++) {
                int[] dir = dirs[i];
                // 내가 현재 칸으로 온 방향(=이전 칸에서 날라온 방향)이면 스킵
                if (lastDir == i) continue;
                
                int dr = dir[0], dc = dir[1];
                int tempR = r, tempC = c;
                
                // 옆 칸이 밖이면 스킵
                if (!isInside(tempR + dr, tempC + dc)) continue;
                
                // 옆 칸이 벽이 아니고, 격자 내부인 동안 그 방향으로 쭉 나아가기
                // = 벽에 부딫힐 때까지 날라가기
                while (
                    isInside(tempR + dr, tempC + dc) &&
                    grid[tempR + dr][tempC + dc] != WALL
                ) {
                    tempR += dr;
                    tempC += dc;
                }
                
                // 날라온 칸이 이미 방문한 칸이라면 
                if (visited[tempR * cols + tempC][i]) continue;
                
                // 현재 위치(행, 열) + 날라온 방향을 방문한 기준으로 체크
                visited[tempR * cols + tempC][i] = true;
                
                // PQ에 삽입
                pq.offer(new State(tempR, tempC, cost + 1, i));
            }
        }
        
        return -1;
    }
    
    void setup(String[] board) {
        grid = new char[board.length][];
        
        for (int r = 0; r < board.length; r++) {
            grid[r] = board[r].toCharArray();
        }
        
        rows = grid.length;
        cols = grid[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                switch (grid[r][c]) {
                    case 'R' -> {
                        pos_robot[0] = r;
                        pos_robot[1] = c;
                    }
                    case 'G' -> {
                        pos_goal[0] = r;
                        pos_goal[1] = c;
                    }
                }
            }
        }
        
        visited = new boolean[rows * cols][dirs.length];
    }
}