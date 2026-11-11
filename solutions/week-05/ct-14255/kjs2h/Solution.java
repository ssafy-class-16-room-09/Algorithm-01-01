import java.util.*;
import java.io.*;

/*
 * [시도 1] boolean[N][N][V][V]  → 메모리 초과
 *  dp[row][col][min][max] 
 *  (0,0)에서 (row,col)까지 오는 경로 중 
 *  최솟값이 min, 최댓값이 max인 경로가 있으면 true
 *
 *  예제에서 dp[1][1][1][4], dp[1][1][1][5]가 true이고,
 *  다음 칸에서는 dp[1][2][1][4], dp[1][2][1][5], dp[1][2][1][7]이 true가 된다.
 *  100 x 100 x 100 x 100 = 1억 칸이므로 약 100MB가 필요하다.
 *
 * [시도 2] HashSet<String>[N][N]  → 메모리 증가, 시간 증가
 *   실제로 가능한 "min max" 쌍만 저장해서 메모리를 줄이려 했다.
 *   하지만 원소 하나가 String 객체, HashMap.Key 등으로 1개1개가 커져버렸다
 *
 *
 * [시도 3] int[N][N][V]  → 통과 
 *   dp[row][col][min]
 *  (0,0)에서 (row,col)까지 오는 경로 중
 *  최솟값이 min인 경로들의 "최댓값 중 가장 작은 값"
 *
 *  min이 같다면 max는 작을수록 항상 유리하다.
 *  - 두 경로 A(min, max=5), B(min, max=9)가 같은 칸에 있다고 하자.
 *  - 이후 어떤 칸들을 지나든 두 경로의 새 min은 똑같이 변한다.
 *  - 새 max는 A가 B보다 항상 같거나 작다. (max(5, x) <= max(9, x))
 *  - 따라서 A의 (max - min)은 끝까지 B보다 같거나 작다.
 *  - B는 정답이 될 수 없으므로 버려도 되고, max 차원을 저장할 필요가 없다.
 */
public class Solution {

    static int N, MAX, MIN;
    static int[][] matrix;
    static int[][][] dp;    

    public static void main(String[] args) throws IOException {
        // 입출력과 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        MAX = Integer.MIN_VALUE;
        MIN = Integer.MAX_VALUE;
        
        StringTokenizer st;
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                MAX = Math.max(MAX, matrix[i][j]);
                MIN = Math.min(MIN, matrix[i][j]);
            }
        }
        
        dp = new int[N][N][MAX + 1];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {                
                for (int min = 0; min <= MAX; min++) {
                    dp[row][col][min] = Integer.MAX_VALUE;
                }
            }
        }

        // 시작칸은 자기 자신 하나만 지나므로 min = max = 시작값
        int start = matrix[0][0];
        dp[0][0][start] = start;

        // 왼쪽 위부터 차례로, 현재 칸의 상태를 오른쪽/아래 칸으로 전파
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                
                for (int min = MIN; min <= MAX; min++) {
                    int max = dp[row][col][min];                    
                    // 오른쪽 칸으로 이동
                    if (col + 1 < N) {
                        int next = matrix[row][col + 1];
                        int newMin = Math.min(min, next);
                        int newMax = Math.max(max, next);
                        // 같은 newMin이라면 더 작은 max만 남김
                        if (newMax < dp[row][col + 1][newMin]) dp[row][col + 1][newMin] = newMax;
                    }
                    // 아래 칸으로 이동
                    if (row + 1 < N) {
                        int next = matrix[row + 1][col];
                        int newMin = Math.min(min, next);
                        int newMax = Math.max(max, next);
                        // 같은 newMin이라면 더 작은 max만 남김
                        if (newMax < dp[row + 1][col][newMin]) dp[row + 1][col][newMin] = newMax;
                    }
                }
            }
        }        
        int answer = Integer.MAX_VALUE;
        for (int min = MIN; min <= MAX; min++) {
            int max = dp[N - 1][N - 1][min];                        
            answer = Math.min(answer, max - min);
        }
        System.out.println(answer);
    }
}
