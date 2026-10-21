import java.io.*;
import java.util.*;

public class Solution {

    static int nc;
    static int nr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = 10;

        for (int tc = 1; tc <= TC; tc++) {
            sb.append("#" + tc + " ");

            br.readLine();
            int[][] ladder = new int[100][100];
            int sc = 0;
            int sr = 0;

            for (int col = 0; col < 100; col++) {
                st = new StringTokenizer(br.readLine());

                for (int row = 0; row < 100; row++) {
                    int num = Integer.parseInt(st.nextToken());
                    ladder[col][row] = num;

                    if (num == 2) {
                        sc = col;
                        sr = row;
                    }
                }
            }

            Deque<int[]> dq = new ArrayDeque<>();
            dq.offer(new int[]{sc, sr});

            int[] dc = new int[]{0, 0};
            int[] dr = new int[]{-1, 1};

            // 왼쪽이 1이라면 0이 나올 때까지 왼쪽으로 간 c를 확인하고 그 점에서 한 칸 위로 가기
            // 오른쪽이 1이라면 0이 나올 때까지 오른쪽으로 간 c를 확인하고 그 점에서 한 칸 위로 가기
            // 왼쪽/오른쪽이 1이 아니라면 그냥 한 칸 위로 가기

            while (!dq.isEmpty()) {
                int[] curr = dq.poll();
                int c = curr[0];
                int r = curr[1];

                if (c == 0) {
                    sb.append(r).append("\n");
                    break;
                }

                nr = r;

                for (int d = 0; d < 2; d++) {
                    nc = c + dc[d];
                    int nextR = r + dr[d];

                    if (nextR < 0 || nextR >= 100) continue;

                    if (ladder[nc][nextR] == 1) {

                        // 왼쪽으로 이동
                        if (d == 0) {
                            while (nr - 1 >= 0 && ladder[nc][nr - 1] == 1) {
                                nr--;
                            }
                        }

                        // 오른쪽으로 이동
                        else {
                            while (nr + 1 < 100 && ladder[nc][nr + 1] == 1) {
                                nr++;
                            }
                        }

                        // 한 방향으로 이동했으므로 다른 방향 검사 X
                        break;
                    }
                }

                // 한 칸 위로 이동
                dq.offer(new int[]{c - 1, nr});
            }
        }

        System.out.println(sb);
    }
}