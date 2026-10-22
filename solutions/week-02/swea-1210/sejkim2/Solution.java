import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final int SIZE = 100;
	static int[][] map = new int[SIZE][SIZE];
	static int[] directCol = {-1, 1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int tc = Integer.parseInt(br.readLine());
			for(int i = 0; i<SIZE; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<SIZE; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			boolean flag = false;
			answer = 0;
			//위에서 하나씩 사다리 태워서 정답 찾으면 종료
			for(int i = 0; i<SIZE; i++) {
				if (map[0][i] == 1)
					flag = find(0, i);
				if (flag)
					break;
			}
			bw.write("#"+test_case+" "+answer+"");
			bw.newLine();
		}
		bw.flush();
	}

	//위에서 아래로 내리면서 사다리 타기
	static boolean find(int row, int col) {
		int start = col;
		while (row < SIZE - 1) {
			//양옆을 확인해서 이동 가능하면 쭉 이동 시키고 종료
			for(int i = 0; i<2; i++) {
				int nextCol = col + directCol[i];
				if (nextCol < 0 || nextCol >= SIZE || map[row][nextCol] == 0)
					continue;
				while ((nextCol + directCol[i] >= 0) && (nextCol + directCol[i] < SIZE) && (map[row][nextCol + directCol[i]] >= 1))
					nextCol = nextCol + directCol[i];
				col = nextCol;
				break;
			}
			//아래로 내림
			row++;
		}
		if (map[row][col] == 2) {
			answer = start;
			return true;
		}
		else
			return false;
	}
}
