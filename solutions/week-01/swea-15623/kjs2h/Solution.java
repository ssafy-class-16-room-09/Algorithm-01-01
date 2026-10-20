import java.io.*;
import java.util.*;

public class Solution {
	
	// x가중치와 y가중치를 따로 담음
	static class Edge {
		int to;
		int xw;
		int yw;
		Edge next;

		Edge(int to, int xw, int yw, Edge next) {
			this.to = to;
			this.xw = xw;
			this.yw = yw;
			this.next = next;
		}
	}
	
	// 각각의 상태는 x합과 y합을 보유함
	static class State {
		int v;
		int xs;
		int ys;

		State(int v, int xs, int ys) {
			this.v = v;
			this.xs = xs;
			this.ys = ys;
		}
	}

	static int N;
	static int M;

	static Edge[] adjList;
	static List<State>[] states;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= TC; tc++) {

			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adjList = new Edge[N + 1];
			states = new ArrayList[N + 1];

			for(int i = 1; i <= N; i++) states[i] = new ArrayList<>();			

			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int xw = Integer.parseInt(st.nextToken());
				int yw = Integer.parseInt(st.nextToken());

				adjList[from] = new Edge(to, xw, yw, adjList[from]);
				adjList[to] = new Edge(from, xw, yw, adjList[to]);
			}

      // 문제에서 시작 정점 번호는 1이라고 고정함
			State start = new State(1, 0, 0);
			states[1].add(start);
	
			Deque<State> queue = new ArrayDeque<>();
			queue.offer(start);
			
			while(!queue.isEmpty()) {
				
				State state = queue.poll();

				int curr = state.v;
				int currX = state.xs;
				int currY = state.ys;						
				
				for(Edge e = adjList[curr]; e != null; e = e.next) {

					// 새 상태 만들기
					int nextX = currX + e.xw;
					int nextY = currY + e.yw;

					// 최단 거리 비교
					boolean flag = false;

          // 만약 x와 y의 합이 모두 기존보다 크면 저장 안해도 됨 (어떤 경우에도 기존보다 좋아질 수 없음 )
					for(State s : states[e.to]) {

						int prevX = s.xs;
						int prevY = s.ys;

						if(prevX <= nextX && prevY <= nextY) {
							flag = true;
							break;
						}
					}
					
					if(flag) continue;


					// 만약 x와 y의 합이 모두 기존보다 작으면 기존을 삭제하고 지금을 추가
					for(int i = 0; i < states[e.to].size(); ) {
						State s = states[e.to].get(i);
						
						if (nextX <= s.xs && nextY <= s.ys) states[e.to].remove(i);
						else i++;
					}

					// 살아남은 상태를 저장하고 queue에 저장
					states[e.to].add(new State(e.to, nextX, nextY));
					queue.offer(new State(e.to, nextX, nextY));
				}
			}

      // 문제에서 끝 정점 번호는 2라고 고정함
			int answer = Integer.MAX_VALUE;
			for(State s : states[2]) answer = Math.min(answer, (s.xs*s.ys));
			
			sb.append(answer==Integer.MAX_VALUE?-1:answer).append("\n");
		}

		System.out.println(sb);
	}
}