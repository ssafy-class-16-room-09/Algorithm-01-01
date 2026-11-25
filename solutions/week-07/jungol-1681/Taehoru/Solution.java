import java.io.*;
import java.util.*;

// 백트래킹 완탐
// 외판원 순외 문제

public class Solution {
	
	static int N;
	static int[][] grid;
	static boolean[] visited;
	static int minCost = Integer.MAX_VALUE;


	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		grid = new int[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}	
		}

		visited[0] = true;
		dfs(0, 0, 0);

		System.out.println(minCost);
	}

	// 현위치, 지금까지의 배달 횟수, 배달 비용
	static void dfs(int depth, int count, int cost) {
		
		// 가지치기
		if (cost >= minCost) {
			return;
		}

		// 종료조건
		if (count == N - 1) {

			// 마지막 장소에서 회사로 돌아갈 수 있는지 확인
			if(grid[depth][0] != 0) {
				minCost = Math.min(minCost, cost + grid[depth][0]);
			}
		}

		// dfs
		for(int next = 1; next < N; next++) {
			if(!visited[next] && grid[depth][next] != 0) {
				
				visited[next] = true;

				// 다음 장소 이동, 방문수+1, 이동 비용을 더하기
				dfs(next, count + 1, cost + grid[depth][next]);
			
				visited[next] = false;
			}
		}
	} 
}
