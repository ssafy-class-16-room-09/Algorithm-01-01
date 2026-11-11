import java.util.Scanner;
import java.util.*;

public class Solution {

    static int[] rowD = {1,0};
    static int[] colD = {0,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                grid[i][j] = sc.nextInt();

            }
                
            
        // Please write your code here.

        // gap 0~99까지 조건 반복
        // gap이 정해질 때, min = 1 ~ (min + gap) 만큼 반복
        //  min이 정해지면, 격자에서 경로를 그을 때 해당 경로는 min ~ (min + gap) 사이의 값들로만 이뤄진 경로임을 검사
        // 이를 반복하다 처음으로 n-1,n-1 좌표에 닿으면 해당 gap 값을 가지고 min 값을 가지는 상황에서 경로를 그릴 수 있음

        int[] tempNode = null;
        int curRow = -1;
        int curCol = -1;
        int nextRow = -1;
        int nextCol = -1;

        // 현재 경로를 그리는데 최소와 최대의 차이가 gap 만큼이다.
        for(int gap = 0; gap < 100; gap++){
            
            // 현재 그리는 경로는 gap만큼의 (최대-최소) 경로를 그려야하는데 최소값은 min이다.
            for(int min=1 ; min + gap <= 100 ; min ++){
                
                // q는 bfs로 경로를 그릴 자료구조
                // visited는 현재 gap과 min 값을 지닌 상황에서 (row,col) 좌표에 도달했는가? t/f
                Queue<int[]> q = new ArrayDeque<>();
                boolean[][] visited = new boolean[n][n];    

                // 0,0 좌표의 숫자 x가 min <= x <= min + gap (max) 안에 속하는가?
                if(grid[0][0] >= min && grid[0][0] <= min + gap){
                    visited[0][0]=true;
                    q.offer(new int[]{0,0});
                }
                    
                // 현재 gap, min 상황에서 bfs로 탐색
                while(!q.isEmpty()){
                    tempNode = q.poll();
                    curRow = tempNode[0];
                    curCol = tempNode[1];

                    // 기저 조건, 도착지에 도착할 수 있는가?
                    if(curRow == n-1 && curCol == n-1 && visited[n-1][n-1]){
                        // gap은 0부터 증가하는 형태로 탐색하기 때문에 처음 기저조건에 도달하면 자동으로 답을 반환
                        System.out.println(gap);
                        return;
                    }

                    // 아래, 오른쪽 탐색
                    for(int i = 0; i < 2; i++ ){
                        nextRow = curRow + rowD[i];
                        nextCol = curCol + colD[i];

                        // 범위 이탈 여부
                        if(!(nextRow >= 0 && nextRow < n && nextCol >=0 && nextCol < n))
                            continue;
                        
                        // 다음 좌표가 min <= (다음 좌표에 위치한 숫자) <= max 를 충족하고, 미방문 이라면, 경로 선택
                        if(grid[nextCol][nextRow] >= min && grid[nextCol][nextRow] <= min + gap && visited[nextCol][nextRow] == false ){
                            visited[nextCol][nextRow] = true;
                            q.offer(new int[]{nextRow, nextCol});
                        }
                    }
                }
            
            }

        }
        
    }
}

// 뭔가 dp로 풀려했던 흔적이지만 부분 최적 조건을 만족하지 못하여 탈락한 코드

// import java.util.Scanner;
// import java.util.*;

// public class Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
//         int[][] grid = new int[n][n];
//         for (int i = 0; i < n; i++)
//             for (int j = 0; j < n; j++)
//                 grid[i][j] = sc.nextInt();
//         // Please write your code here.

//         // dp[0]: min, dp[1]: max, dp[2]: abs(max-min)의 최소
//         int[][][] dp=new int[n][n][3];
//         int minNum=Integer.MAX_VALUE;
//         int maxNum=Integer.MIN_VALUE;

//         dp[0][0][0] = grid[0][0];
//         dp[0][0][1] = grid[0][0];
//         dp[0][0][2] = 0;

//         for(int col=1 ; col < n ; col++){
//             dp[col][0][0] = Math.min( dp[col-1][0][0], grid[col][0] );
//             dp[col][0][1] = Math.max( dp[col-1][0][1], grid[col][0] );
//             dp[col][0][2] = dp[col][0][1] - dp[col][0][0];
//         }

//         for(int row=1 ; row < n ; row++){
//             dp[0][row][0] = Math.min( dp[0][row-1][0], grid[0][row] );
//             dp[0][row][1] = Math.max( dp[0][row-1][1], grid[0][row] );
//             dp[0][row][2] = dp[0][row][1] - dp[0][row][0];
//         }

        
//         for(int gap=0;gap<100; gap++){
//             for(int col=1 ; col < n ; col ++){
//                 for(int row=1; row < n ; row++){

//                     int minUp = dp[col-1][row][0];
//                     int maxUp = dp[col-1][row][1];
//                     int gapUp = dp[col-1][row][2];

//                     int minLeft = dp[col][row-1][0];
//                     int maxLeft = dp[col][row-1][1];
//                     int gapLeft = dp[col][row-1][2];

//                     // int curValue=grid[col][row];

//                     // 현재 위치 포함
//                     // 윗 경로와 왼쪽 경로주 dp값이 더 작은 경로 선택
                    
//                     // // 왼쪽 경로 범위가 더 작음
//                     // if(gapLeft<gapUp){
//                     //     dp[col][row][0]=Math.min( grid[col][row], minLeft );
//                     //     dp[col][row][1]=Math.max( grid[col][row], maxLeft );
//                     //     dp[col][row][2]= dp[col][row][1] - dp[col][row][0];
//                     //     continue;
//                     // }
//                     // // 위쪽 경로 범위가 더 작음
//                     // else if(gapLeft>gapUp){
//                     //     dp[col][row][0]=Math.min( grid[col][row], minUp );
//                     //     dp[col][row][1]=Math.max( grid[col][row], maxUp );
//                     //     dp[col][row][2]= dp[col][row][1] - dp[col][row][0];
//                     //     continue;
//                     // }
                    
//                     // 두 경로의 최소_최대 범위 같음
//                     // 현재 칸의 숫자를 포함한 왼쪽 경로와 위쪽 경로 gap으로 설정
                    
//                     // 위쪽 경로 계산
//                     minUp=Math.min( grid[col][row], minUp );
//                     maxUp=Math.max( grid[col][row], maxUp );
//                     gapUp=maxUp-minUp;
                    
//                     // 왼쪽 경로 계산
//                     minLeft=Math.min( grid[col][row], minLeft );
//                     maxLeft=Math.max( grid[col][row], maxLeft );
//                     gapLeft=maxLeft-minLeft;

//                     if(gap < gapLeft){

//                     }
//                     else if(gap < gapUp){

//                     }
//                     else{

//                     }

//                     // 왼쪽 경로 범위가 더 작음
//                     if(gapLeft<gapUp){
//                         dp[col][row][0]=minLeft;
//                         dp[col][row][1]=maxLeft;
//                         dp[col][row][2]= gapLeft;
//                         continue;
//                     }
//                     // 위쪽 경로 범위가 더 작음
//                     else if(gapLeft>gapUp) {
//                         dp[col][row][0]= minUp;
//                         dp[col][row][1]= maxUp;
//                         dp[col][row][2]= gapUp;
//                         continue;
//                     }
//                     // 둘이 같을 경우
//                     else{
//                         // 문제점
//                     }
//                 }
//             }
//         }
 
        
//         System.out.println("<<minArr>>");
//         for(int col=0 ; col < n ; col ++){
//             for(int row=0; row < n ; row++){
//                 System.out.printf("%2d ",dp[col][row][0]);
//             }
//             System.out.println();
//         }
//         System.out.println();

//         System.out.println("<<maxArr>>");
//         for(int col=0 ; col < n ; col ++){
//             for(int row=0; row < n ; row++){
//                 System.out.printf("%2d ",dp[col][row][1]);
//             }
//             System.out.println();
//         }
//         System.out.println();

//         System.out.println("<<dpArr>>");
//         for(int col=0 ; col < n ; col ++){
//             for(int row=0; row < n ; row++){
//                 System.out.printf("%2d ",dp[col][row][2]);
//             }
//             System.out.println();
//         }
//         System.out.println();
        

//         System.out.println(dp[n-1][n-1][2]);
//     }
// }