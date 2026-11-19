import java.io.*;
import java.util.*;

class Solution {
    // ===========bfs용 정석 자료구조===========
    static int ROW, COL;
    static int[][] matrix;
    static int sr,sc,fr,fc;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static boolean[][] visited;
    // ===========bfs용 정석 자료구조===========

    public int solution(String[] board) throws IOException{
        // ===========입력과 초기화===========
        ROW = board.length;
        COL = board[0].length();
        matrix = new int[ROW][COL];
        visited = new boolean[ROW][COL];

        for(int row = 0; row < ROW; row++){
            for(int col = 0; col < COL; col++){
                char curr =  board[row].charAt(col);
                if (curr == 'G'){
                    fr = row;
                    fc = col;
                }
                if (curr == 'R'){
                    sr = row;
                    sc = col;
                }
                if (curr == 'D') matrix[row][col] = 1;
                else matrix[row][col] = 0;
            }
        }
        // ===========입력과 초기화===========

        // ==================bfs===============
        Deque<int[]> queue = new ArrayDeque<>();
        // queue에는 row, col, 이동거리를 담음
        queue.offer(new int[]{sr,sc,0});
        visited[sr][sc] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int path = curr[2];

            for(int d = 0; d < 4; d++){
                int nr = row + dr[d];
                int nc = col + dc[d];
                if (isSafe(nr,nc)){
                    // =======swea 사다리처럼 끝까지 가=======
                    int[] next = move(nr,nc,d);
                    // =======swea 사다리처럼 끝까지 가=======

                    // ===목표지점에 도달했다면 이동거리를 return해===
                    if (next[0] == fr && next[1] == fc) return path+1;
                    // ===목표지점에 도달했다면 이동거리를 return해===

                    if (visited[next[0]][next[1]]) continue;
                    visited[next[0]][next[1]] = true;
                    queue.offer(new int[]{next[0], next[1], path+1});
                }
            }
        }
        // ==================bfs===============

        // ===도달 못했다면 -1을 return해===
        return -1;
        // ===도달 못했다면 -1을 return해===
    }

    public boolean isSafe(int row, int col){
        return row >= 0 && row < ROW && col >= 0 && col < COL && matrix[row][col]==0;
    }

    public int[] move(int row, int col, int dir){
        int nr = row + dr[dir];
        int nc = col + dc[dir];
        if (!isSafe(nr,nc)) return new int[]{row, col};
        else return move(nr,nc,dir);
    }
}
