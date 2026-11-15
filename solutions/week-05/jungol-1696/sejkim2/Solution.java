import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[][] room;
    static int[] directRow = {0,1, -1,0};
    static int[] directCol = {1,0, 0,-1};

    static class Node {
        int row;
        int col;
        int cost;
        Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        room = new int[n][n];
        for(int i = 0; i<n; i++) {
            String input = br.readLine();
            for(int j = 0; j<n; j++)
                room[i][j] = input.charAt(j) - '0';
        }
        
        int res = dijk();
        System.out.println(res);
    }

    static int dijk() {
        Node node = new Node(0, 0, 0);
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        heap.offer(node);
        int[][] dist = new int[n][n];
        for(int i = 0; i<n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = 0;

        while (heap.size() > 0) {
            Node cur = heap.poll();
            if (cur.row == n - 1 && cur.col == n - 1)
                return cur.cost;
            if (dist[cur.row][cur.col] < cur.cost)
                continue;
            
            for(int i = 0; i<4; i++) {
                int nextRow = cur.row + directRow[i];
                int nextCol = cur.col + directCol[i];
                if (!isPossible(nextRow, nextCol))
                    continue;
                int cost = 0;
                if (room[nextRow][nextCol] == 0)
                    cost = 1;
                int nextCost = cur.cost + cost;
                if (nextCost < dist[nextRow][nextCol]) {
                    dist[nextRow][nextCol] = nextCost;
                    heap.offer(new Node(nextRow, nextCol, nextCost));
                }
            }
        }

        return 0;
    }

    static boolean isPossible(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n)
            return false;
        else
            return true;
    }
}