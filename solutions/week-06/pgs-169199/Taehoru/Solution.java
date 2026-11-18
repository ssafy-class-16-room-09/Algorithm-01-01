import java.util.*;
import java.io.*;

class Solution {
    
    static int r, c;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited;
    
    public int solution(String[] board) {
        r = board.length;
        c = board[0].length();
        
        visited = new boolean[r][c];
        
        // 시작점 찾기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                if (board[i].charAt(j) == 'R') {
                    return bfs(i, j, board);
                }
        }
        return -1;    
    }
    
    
    public int bfs(int x, int y, String[] board) {
        Queue<int[]> q = new ArrayDeque<>();
        
        
        // 큐 - 행, 열, 이동 횟수
        q.offer(new int[] {x, y, 0});
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt = cur[2];
            
            // 방향 탐색
            for (int dist = 0; dist < 4; dist++) {
                int nx = cur_x;
                int ny = cur_y;
                
                while (true) {
                    // 앞만 보고 미끄러지기
                    int nx2 = nx + dx[dist];
                    int ny2 = ny + dy[dist];
                
                    // 범위 밖 확인
                    if (nx2 < 0 || ny2 < 0 || nx2 >= r || ny2 >= c) {
                        break;
                    }
                    
                    // 장애물 확인
                    if (board[nx2].charAt(ny2) == 'D') {
                        break;
                    }

                    nx = nx2;
                    ny = ny2;
                    
                }

                //  G에 도착하면 이것도 이동으로 포함해야함 그래서 +1
                if (board[nx].charAt(ny) == 'G') {
                    return cnt + 1;
                }

                // 처음 도착하는게 최소 이동 횟수가 됨
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny, cnt + 1});
                }
            }
        }

        return -1;
        
    }
}