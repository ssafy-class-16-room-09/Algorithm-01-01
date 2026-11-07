import java.util.*;
import java.io.*;

public class Solution{
    static int startRow, startCol, finRow, finCol;

    static int N;    
    static int[][] matrix;

    // r, c에 도달하기 위해 부숴야 하는 검은 방의 최소
    static int[][] dp;              

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};            

    public static void solution() throws IOException{
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();           
        
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        dp = new int[N][N];        

        startRow = 0; startCol = 0; finRow = N - 1; finCol = N - 1;

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < N; j++){
                int num = line.charAt(j)-'0';
                matrix[i][j] = num;
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[0][0] = 0;

        // r좌표, c좌표, 부순 방의 개수
        Deque<int[]> dq = new ArrayDeque<>();        
        dq.offer(new int[]{startRow, startCol, 0});

        while(!dq.isEmpty()){            
            
            int[] curr = dq.poll();
            int r = curr[0];
            int c = curr[1];
            int opened = curr[2];

            // 부순 방의 개수가 너무 많으면? 그냥 탐색하지마
            if (opened > dp[r][c]) continue;
            
            // 각 방향에 대해
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (inRange(nr,nc)){
                    
                    // 검은 방을 부수고 진행, 비용 1, (우선순위 낮음), dq의 마지막에 넣음
                    if (matrix[nr][nc] == 0){
                        if (dp[nr][nc] > opened + 1){
                            dp[nr][nc] = opened + 1;                        
                            dq.offer(new int[]{nr, nc, opened + 1});
                        }

                    // 흰색 방으로 우회해서 진행, 비용 0, (우선순위 높음), dq의 처음에 넣음
                    }else{
                        if (dp[nr][nc] > opened){
                            dp[nr][nc] = opened;                        
                            dq.offerFirst(new int[]{nr, nc, opened});
                        }
                    }
                }
            }
        }
        
        System.out.println(dp[N-1][N-1]==Integer.MAX_VALUE?0:dp[N-1][N-1]);
    }    
    
    public static boolean inRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

}

