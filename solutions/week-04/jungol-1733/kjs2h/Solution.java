import java.io.*;
import java.util.*;

public class Solution {

    static final int SIZE = 19;
    static int[][] matrix = new int[SIZE][SIZE];

    // 우, 하, 우하, 우상
    static int[] dr = {0, 1, 1, -1};
    static int[] dc = {1, 0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //선언
        for (int row = 0; row < SIZE; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < SIZE; col++) {
                matrix[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        //판단
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (matrix[row][col] == 0) continue;

                if (isWin(row, col)) {
                    System.out.println(matrix[row][col]);
                    System.out.println((row + 1) + " " + (col + 1));
                    return;
                }
            }
        }

        System.out.println(0);
    }

    public static boolean isWin(int row, int col) {
        //흑돌, 백돌 확인
        int color = matrix[row][col];

        for (int d = 0; d < 4; d++) {
            // 현재 돌이 [2][3]에 있다고 한다면 진행 방향의 직전 돌을 검사해봐야 함
            // 왜냐하면 그 돌의 색이 나와 같으면 5개를 세면 안됨 (O [O] O O O O)
            // 여기에서 진행방향이 오른쪽으로 가정하면 5개잖아? 그렇다고 오목! 해버리면 안되거든
            // 진행방향의 반대쪽인 왼쪽을 보는거야, 그게 만약 나랑 색이 같아?
            // 그러면 그냥 넘어가
            // 근데 나랑 색이 달라?
            // 그럼 내가 시작점인거지, 비교를 해야한다는거야
            int pr = row - dr[d];
            int pc = col - dc[d];
            if (inRange(pr, pc) && matrix[pr][pc] == color) continue;

            // 여기는 내가 시작점일때만 올 수 있어
            int count = 1;
            int nr = row + dr[d];
            int nc = col + dc[d];

            // 나랑 색 같을때까지 개수를 세고
            while (inRange(nr, nc) && matrix[nr][nc] == color) {
                count++;

                //6목 이상이면 6에서 멈춤
                if (count > 5) break;

                nr += dr[d];
                nc += dc[d];
            }

            //5목인지 확인
            if (count == 5) return true;
        }

        return false;
    }

    public static boolean inRange(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }
}