import java.util.*;

class Solution {
    
    class Edge {
        int vertex1;
        int vertex2;
        int weight;
        
        Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }
    }
    
    int[] parent;
    Edge[] edges;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        edges = new Edge[costs.length];
        int cnt = 0;
        parent = new int[n + 1];
        for(int i = 0; i<n; i++)
            parent[i] = i;
        
        for(int[] cost : costs) {
            int v1 = cost[0];
            int v2 = cost[1];
            int weight = cost[2];
            edges[cnt] = new Edge(v1, v2, weight);
            cnt++;
        }
        
        Arrays.sort(edges, (a, b) -> a.weight - b.weight);
        for(Edge edge : edges) {
            int v1 = edge.vertex1;
            int v2 = edge.vertex2;
            int weight = edge.weight;
            if (find(v1) != find(v2)) {
                union(v1, v2);
                answer += weight;
            }
        }
        return answer;
    }
    
    int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA == rootB)
            return;
        
        parent[rootA] = rootB;
    }
}