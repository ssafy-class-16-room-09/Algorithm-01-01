import java.io.*;
import java.util.*;

// 급해서 주석을 못달앗ㅇㅁ
// 일단 bfs 썼는데 bfs 핵심 로직만 써놓고 그 이상을 어떻게 해야할지 몰라서 GPT 행님의 도움을 받앗음
// 생각한 아이디어의 80은 맞았지만 틀렸던거는 합의 나눔을 다시 배열에 추가해서 또 bfs 들어가야하는데 이를 놓침 

public class Solution {
    
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; 

    static int n, minL, maxR;
    static boolean[][] visited;
    static int[][] egg;

    static ArrayList<ArrayList<int[]>> groups ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken().trim());
        minL = Integer.parseInt(st.nextToken().trim());
        maxR = Integer.parseInt(st.nextToken());
        egg = new int[n][n];
        int cnt = 0; 
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                egg[i][j] = Integer.parseInt(st.nextToken().trim());
            }
        }
        
        while (true) {
            visited = new boolean[n][n];
            groups = new ArrayList<>();


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            
            if (groups.isEmpty()) {
                break;
            }

            for (ArrayList<int[]> group : groups) {
                int sum = 0;

                for (int[] cur : group) {
                    int r = cur[0];
                    int c = cur[1];

                    sum += egg[r][c];
                }
                int avg = sum / group.size();

                for (int[] cur : group) {
                    int r = cur[0];
                    int c = cur[1];
                    egg[r][c] = avg;
                }
            }
        cnt++;
        }
        
        System.out.println(cnt);

        
    }

    public static void bfs(int r, int c) {
        ArrayList<int[]> group = new ArrayList<>();

        group.add(new int[]{r, c});
        visited[r][c] = true;
        
        for (int i = 0; i < group.size(); i++) {
            int[] cur = group.get(i);
            int cr = cur[0];
            int cc = cur[1];

            for (int j = 0; j < 4; j++) {
                int nr = cr + dr[j];
                int nc = cc + dc[j];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;

                int diff = Math.abs(egg[cr][cc] - egg[nr][nc]);

                if (diff >= minL && diff <= maxR) {
                    visited[nr][nc] = true;
                    group.add(new int[]{nr, nc});
                }
            }
        }

        if (group.size() > 1) {
            groups.add(group);
        }
    }
}
