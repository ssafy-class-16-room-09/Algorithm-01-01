import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N;
	static int[][] factory;
	static boolean[] visit;
	static int min;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			factory = new int[N][N];
			visit = new boolean[N];
			min = Integer.MAX_VALUE;

			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++)
					factory[i][j] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0);
			bw.write("#"+test_case+" "+min+"");
			bw.newLine();
			
		}
		bw.flush();
	}

	static void dfs(int depth, int cost) {
		if (cost > min)
			return;

		if (depth == N) {
			min = Math.min(cost, min);
			return;
		}

		for(int i = 0; i<N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				dfs(depth + 1, cost + factory[depth][i]);
				visit[i] = false;
			}
		}
	}
}


// def dfs(depth, cost):
//     global min_cost

//     if cost > min_cost:
//         return

//     if depth == N:
//         min_cost = min(cost, min_cost)
//         return

//     for i in range(N):
//         if not visit[i]:
//             visit[i] = True
//             dfs(depth + 1, cost + factory[depth][i])
//             visit[i] = False


// T = int(input())

// for test_case in range(1, T + 1):
//     N = int(input())
//     factory = [list(map(int, input().split())) for _ in range(N)]
//     visit = [False] * N
//     min_cost = float('inf')

//     dfs(0, 0)

//     print(f"#{test_case} {min_cost}")
