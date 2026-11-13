import java.util.*;

class Solution {
    char[][] map;
    int height, width;
    int[] R;
    int[] G;
    int[] directRow = {1, 0, 0, -1};
    int[] directCol = {0, -1, 1, 0};
    boolean[][] visit;
    
    public int solution(String[] board) {
        int answer = 0;
        height = board.length;
        width = board[0].length();
        map = new char[height][width];
        visit = new boolean[height][width];
        R = new int[2];
        G = new int[2];
        
        for(int i = 0; i<height; i++) {
            for(int j = 0; j<width; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    R[0] = i;
                    R[1] = j;
                }
                if (map[i][j] == 'G') {
                    G[0] = i;
                    G[1] = j;
                }
            }
        }
        
        answer = bfs();
        
        return answer;
    }
    
    int bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{R[0], R[1], 0});
        visit[R[0]][R[1]] = true;
        
        while (queue.size() > 0) {
            int[] cur = queue.pollFirst();
            if (cur[0] == G[0] && cur[1] == G[1])
                return cur[2];
            
            for(int i = 0; i<4; i++) {
                int nextRow = cur[0];
                int nextCol = cur[1];
            
                //다음 위치를 갈수 있다면(맵 범위 안에 있으면서 벽이 아닌 경우) 계속 이동
                while (isPossible(nextRow + directRow[i], nextCol + directCol[i]) && map[nextRow + directRow[i]][nextCol + directCol[i]] != 'D') {
                    nextRow += directRow[i];
                    nextCol += directCol[i];
                }
                //이미 이전에 방문한 곳이면 그게 최단거리 이므로 볼 필요 없음
                if (visit[nextRow][nextCol])
                    continue;
                visit[nextRow][nextCol] = true;
                queue.offerLast(new int[]{nextRow, nextCol, cur[2] + 1});
            }
        }
        return -1;
    }
    
    boolean isPossible(int row, int col) {
        if (row < 0 || col < 0 || row >= height || col >= width)
            return false;
        else
            return true;
    }
}