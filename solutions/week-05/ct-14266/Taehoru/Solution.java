import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Solution {

    static int n, m, k;
    static int[] nums;
    static boolean[] visited;
    static int[] position;
    static int score;
    static int MAX_score = -1;

    // 백트래킹
    static void dfs(int depth) {
        // 종료조건
        if(depth==n) {
            score = 0;

            // 말이 끝에 도달하면
            for (int i=1; i <= k; i++) {
                if (position[i] == m) {
                    score++;
                }
            }
            // 스코어 갱신
            MAX_score = Math.max(MAX_score, score);
            return;
        }

        // i = 말의 갯수
        // 말의 위치를 저장하고 prev
        for (int i = 1; i <= k; i++) {
            int prev = position[i];

            // 이번 턴에 고른 i번 말을 움직이고 새 위치 저장
            // m을 넘어가면 m에서 멈추게
            position[i] = Math.min(m, prev + nums[depth]);

            // ==debug==
            //  if (depth < 2) {
            //      System.out.println("depth=" + depth + " i=" + i + " " + Arrays.toString(position));
            //  }
            /* 디버깅 결과값
            depth=0 i=1 [0, 3, 1, 1]
            depth=1 i=1 [0, 6, 1, 1]
            depth=1 i=2 [0, 3, 5, 1]
            depth=1 i=3 [0, 3, 1, 5]           
            depth=0 i=2 [0, 1, 3, 1]           
            depth=1 i=1 [0, 5, 3, 1]           
            depth=1 i=2 [0, 1, 6, 1]           
            depth=1 i=3 [0, 1, 3, 5]           
            depth=0 i=3 [0, 1, 1, 3]          
            depth=1 i=1 [0, 5, 1, 3]         
            depth=1 i=2 [0, 1, 5, 3]        
            depth=1 i=3 [0, 1, 1, 6]
            ...
            */

            dfs(depth+1);
            
            // 다시 원위치
            position[i] = prev;

        
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        visited = new boolean[n+1];
        position = new int[k+1];

        // 모든 말을 1번 칸에 놓기
        for (int i = 1; i <= k; i++) {
            position[i] = 1;
        }

        dfs(0);
        System.out.println(MAX_score);

    }
}