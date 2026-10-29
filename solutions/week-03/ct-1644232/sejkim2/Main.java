import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, L, R;
    static int[][] ar;
    static boolean[][] visit;
    static int[] directRow = {0,1,-1,0};
    static int[] directCol = {1,0,0,-1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        ar = new int[n][n];
        visit = new boolean[n][n];

        int cnt = 0;
        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++) 
                ar[i][j] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        //1. n^2만큼 돌면서 만약 방문 안했으면 그 좌표 기준으로 bfs
        //2. bfs 다 돌리면 좌표를 갱신하고 갱신된 수만큼 반환
        //3. bfs 반환값이 1이면 자기자신만 갱신이고 (사실상 갱신된 것이 없음) 이게 n^2만큼 나오면 종료 (계란의 이동이 더이상 없을 때)
        while (true) {
            //매 분기마다 방문기록 초기화
            for(int i = 0; i<n; i++) 
                Arrays.fill(visit[i], false);                    
            
            cnt = 0;
            for(int i = 0; i<n; i++) {
                for(int j = 0; j<n; j++) {
                    if (visit[i][j] == false) {
                        if (bfs(i, j) == 1)
                            cnt++;
                    }
                }
            }
            if (cnt == n*n)
                break;
            ans++;
        }

        bw.write(ans+"");
        bw.flush();
    }

    static int bfs(int row, int col) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{row, col});
        visit[row][col] = true;

        //cnt는 변경된 수의 개수, sum은 그 합이고 나는 
        //list에 변경되는 모든 좌표를 넣고 bfs 다 돌리면 list 돌리면서 좌표에 값을 갱신해줄거임
        int cnt = 0;
        int sum = 0;
        ArrayList<int[]> list = new ArrayList<>();

        while (queue.size() > 0) {
            int[] cur = queue.pollFirst();
            int curValue = ar[cur[0]][cur[1]];
            sum += curValue;
            cnt++;
            list.add(cur);
            for(int i = 0; i<4; i++) {
                int nextRow = cur[0] + directRow[i];
                int nextCol = cur[1] + directCol[i];
                if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n)
                    continue;
                if (visit[nextRow][nextCol] == true)
                    continue;
                int nextValue = ar[nextRow][nextCol];
                int diff = Math.abs(curValue - nextValue);
                if (L <= diff && R >= diff) {
                    queue.offerLast(new int[]{nextRow, nextCol});
                    visit[nextRow][nextCol] = true;    
                }
            }
        }

        //이제 list에서 좌표 꺼내서 값 갱신해줌
        for(int[] point : list)
            ar[point[0]][point[1]] = (sum / cnt);
        return cnt;
    }
}