import java.io.*;
import java.util.*;

public class Solution {
    static int n, l, r;
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<int[]> toavg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        //초기 값 할당
        matrix = new int[n][n];        
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                matrix[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true){
            // 변화가 없을 때까지 합의 평균 계산 1회씩 시행
            if(!sumavg()) break; 
            else cnt++;
        }
        
        System.out.println(cnt);        
    }

    static boolean sumavg(){

        // 변화 체크
        boolean isMoved = false;
        // 방문 배열
        visited = new boolean[n][n];
        
        for (int row = 0; row < n; row++) {            
            for (int col = 0; col < n; col++) {                
                if (visited[row][col]) continue; 
                
                // 이웃들의 좌표를 담을 ArrayList
                toavg = new ArrayList<>();
                toavg.add(new int[]{row, col});
                            
                int sum = bfs(row, col);
                
                // toavg는 자신을 항상 포함하므로
                // 1보다 큰 경우 dfs 결과 l < 이웃 < r 이 존재한다는 뜻
                if (toavg.size() > 1) {
                    isMoved = true;
                    // avg 계산한 뒤
                    int avg = sum / toavg.size();
                    // 모든 이웃들 (자신 포함) 값에 avg 대입한 뒤 이어서 탐색                     
                    for(int[] to : toavg) matrix[to[0]][to[1]] = avg;
                }
            }
        }
        return isMoved;
    }

    static boolean boundarycheck(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= n;
    }

    static boolean movecheck(int curr, int target) {
        int diff = Math.abs(curr - target);
        return l <= diff && diff <= r;
    }

    static int bfs(int startRow, int startCol) {
        // 이 visited는 1회의 sumavg 연산과 공유하는 방문 배열이어야'만' 함
        Queue<int[]> q = new LinkedList<>();
        visited[startRow][startCol] = true;
        q.add(new int[]{startRow, startCol});
        
        int sum = matrix[startRow][startCol];

        while (!q.isEmpty()) {
            int[] currPos = q.poll();
            int row = currPos[0];
            int col = currPos[1];
            int curr = matrix[row][col];

            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];
                
                if (boundarycheck(nr, nc)) continue;
                if (visited[nr][nc]) continue;

                int target = matrix[nr][nc];
                
                if (movecheck(curr, target)) {
                    visited[nr][nc] = true;
                    toavg.add(new int[]{nr, nc});
                    q.add(new int[]{nr, nc});
                    sum += target;
                }
            }
        }
        
        return sum;
    }
}