import java.util.*;

class Solution {    
    public int solution(int[][] info, int n, int m) {
        
        int answer = Integer.MAX_VALUE;
        int I=info.length;
        
        // column 값    : 물건 0~i-1 까지 물건 중 
        // row 값       : B가 가질 수 있는 흔적 누적 개수가 b개
        // 좌표(col, row)값 : B가 b개 미만 흔적을 가질 수 있고 물건 0~(i-1)이 존재할 때, 이때 A가 가질 수 있는 최소 흔적 갯수
        
        int[][] dp = new int[I][m+1];
        int curItemBClue = -1;
        int curItemAClue = -1;

        
            
        for(int i = 0; i < I ; i++ ){
            
            curItemAClue = info[i][0];
            curItemBClue = info[i][1]; 

            for(int b = 1; b <= m ; b++ ){
                
                // <1행 초기화>
                // 0번째 item을 b가 훔치면
                if( i == 0 && curItemBClue < b ){
                    dp[i][b] = 0;
                    continue;
                }
                // 0번째 item을 a가 훔치면
                else if( i == 0 && curItemBClue >= b && curItemAClue < n){
                    dp[i][b] = curItemAClue;
                    continue;
                }
                // 0번째 item을 아무도 못 훔치면
                else if( i == 0 && curItemBClue >= b && curItemAClue >= n){
                    dp[i][b] = Integer.MAX_VALUE;
                    continue;
                }
                // <1행 초기화 종료>
                
                dp[i][b]=Integer.MAX_VALUE;
                
                // A가 훔친 경우
                if(dp[i-1][b] + curItemAClue < n && dp[i-1][b] != Integer.MAX_VALUE){
                    dp[i][b] = dp[i-1][b] + curItemAClue;
                }   
                    
                 // B가 훔친 경우
                if( b > 1 && curItemBClue < b ){
                    dp[i][b] = Math.min( dp[i][b], dp[i-1][b-curItemBClue]);
                }
                dp[i][b] = Math.min( dp[i-1][b] + curItemAClue, dp[i-1][b-curItemBClue]);
                
                // 모두 못 훔친 경우
                // dp[i][b] = Math.min(dp[i][b], Integer.MAX_VALUE);
                
            }
        }
        return dp[I-1][m] == Integer.MAX_VALUE ? -1 : dp[I-1][m];
    }
}


// import java.util.*;

// class Solution {
//     static int N=-1;

    
//     public int solution(int[][] info, int n, int m) {
        
//         int answer = Integer.MAX_VALUE;
//         N=info.length;
        
//         Queue<Node> q=new ArrayDeque<>();
        
//         q.offer(new Node(0, 0, 0));
        
//         while(!q.isEmpty()){
//             Node node=q.poll();
//             // 마지막까지 도달하면
            
//             // System.out.printf("cnt %d A %d B %d\n", node.cnt, node.curA, node.curB);
            
//             if(node.cnt==N){
//                 answer=Math.min(answer,node.curA);
//                 continue;
//             }
            
//             if(node.curA + info[node.cnt][0] < n)
//                 q.offer(new Node(node.cnt+1, node.curA+info[node.cnt][0], node.curB));
//             if(node.curB + info[node.cnt][1] < m)
//                 q.offer(new Node(node.cnt+1, node.curA, node.curB+info[node.cnt][1]));
//         }
        
//         if(answer==Integer.MAX_VALUE)
//             answer=-1;
        
//         return answer;
//     }
// }

// class Node{
//     int cnt;
//     int curA;
//     int curB;
//     public Node(int c, int a, int b){
//         cnt=c;
//         curA=a;
//         curB=b;
//     }
// }

