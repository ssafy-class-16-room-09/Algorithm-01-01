/*
 dist가 1차원일 때 : (sumX*sumY) 결과값만 저장했기 때문에 안됨 (sumX, sumY 둘다 봐야 함)
 dist를 2차원으로 하고 sumX가 동일할 때 최소의 sumY를 찾으면 됨
 */

import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static class Edge {
		int to;
		int x;
		int y;
		
		Edge(int to, int x, int y) {
			this.to = to;
			this.x = x;
			this.y = y;
		}
	}
	
	static class State {
		int node;
		int sumX;
		int sumY;
		
		State(int node, int sumX, int sumY) {
			this.node = node;
			this.sumX = sumX;
			this.sumY = sumY;
		}
	}
		
	static int N, M;
	static int[][] dist;
	static ArrayList<Edge>[] graph;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int maxX = 9*(N-1);	//간선 개수가 최대 N-1개 이므로
			dist = new int[N + 1][maxX + 1];	//dist[node][sumX] : node까지 도달할 때 sumX가 되도록 도착할 때의 최소 sumY
			for(int i = 1; i<=N; i++)
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			
			graph = new ArrayList[N + 1];
			for(int i = 1; i<=N; i++)
				graph[i] = new ArrayList<>();
			
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				graph[A].add(new Edge(B, X, Y));
				graph[B].add(new Edge(A, X, Y));
			}
			
			PriorityQueue<State> heap = new PriorityQueue<>((a, b) -> a.sumY - b.sumY);
			heap.offer(new State(1, 0, 0));
			dist[1][0] = 0;
			
			while (heap.size() > 0) {
				State cur = heap.poll();
				
				if (dist[cur.node][cur.sumX] < cur.sumY)
					continue;
				
				for(Edge edge : graph[cur.node]) {
					int nextX = cur.sumX + edge.x;
					int nextY = cur.sumY + edge.y;
					
					if (nextX > maxX)	//visit 없어서 있어야 함 (재방문 가능)
						continue;
					
					if (dist[edge.to][nextX] > nextY) {
						dist[edge.to][nextX] = nextY;
						heap.offer(new State(edge.to, nextX, nextY));
					}
				}
			}
			
			//dist[][]에는 각 노드에 도달할 때 sumX가 0부터 maxX까지일때 최소sumY가 저장됨
			
			int min = Integer.MAX_VALUE;
			for(int i = 1; i <= maxX; i++) {
				if (dist[2][i] != Integer.MAX_VALUE)
					min = Math.min(min, i*dist[2][i]);
			}
			if (min == Integer.MAX_VALUE)
				min = -1;
			bw.write("#"+test_case+" "+min+"");
			bw.newLine();
		}
		bw.flush();
	}
}
