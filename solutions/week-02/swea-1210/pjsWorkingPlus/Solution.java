import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static final int N = 100;
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;

    public static void main(String[] args) throws Exception {
        for (int testCase = 1; testCase <= 10; testCase++) {
            int testCaseNumber = Integer.parseInt(br.readLine());
            int destination = initMap();
            int answer = findStart(destination);

            System.out.println("#" + testCaseNumber + " " + answer);
        }
    }

    static int findStart(int x) {
        int y = N - 1;

        while (y > 0) {
            map[y][x] = 0;

            if (x > 0 && map[y][x - 1] == 1) {
                x--;
            } else if (x + 1 < N && map[y][x + 1] == 1) {
                x++;
            } else {
                y--;
            }
        }

        return x;
    }

    static int initMap() throws Exception {
        int destination = -1;
        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (y == N - 1 && map[y][x] == 2) {
                    destination = x;
                }
            }
        }

        return destination;
    }
}
