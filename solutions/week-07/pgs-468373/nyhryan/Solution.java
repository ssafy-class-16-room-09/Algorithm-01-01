import java.util.*;

class Solution {
    record Edge(int from, int to, int type) {
        Edge(int[] edge) {
            this(edge[0], edge[1], edge[2]);
        }
    }
    
    static int n, infection, k;
    static List<Edge>[] edgeByType;
    
    static List<List<Integer>>[] ADJACENT_LISTS;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        Solution.n = n;
        Solution.infection = infection;
        Solution.k = k;
        
        edgeByType = new ArrayList[]{
            null, 
            new ArrayList<Edge>(), // A 타입의 파이프들
            new ArrayList<Edge>(), // B
            new ArrayList<Edge>()  // C
        };
        
        for (int[] edge: edges) {
            int from = edge[0], to = edge[1], type = edge[2];
            edgeByType[type].add(new Edge(from, to, type));
        }
        
        ADJACENT_LISTS = new List[] {
            null,
            buildGraph(n, edgeByType[1]),   // A 파이프만 뽑아서 트리(=그래프) 만들기
            buildGraph(n, edgeByType[2]),   // B ...
            buildGraph(n, edgeByType[3])    // C ...
        };
        
        boolean[] infects = new boolean[n + 1];
        infects[infection] = true;
        dfs(0, infects , 1, new ArrayList<>());
        return maxInfects;
    }
    
    static List<List<Integer>> buildGraph(int n, List<Edge> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        for (Edge e: edges) {
            graph.get(e.from).add(e.to);
            graph.get(e.to).add(e.from);
        }
        
        return graph;
    }
    
    static Set<Integer> bfs(List<List<Integer>> graph, boolean[] infected) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        for (int node = 1; node < infected.length; node++) {
            if (!infected[node]) continue;
            
            q.add(node);
            visited[node] = true;
        }
        
        Set<Integer> infectedDuringBFS = new HashSet<>();
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            // List<Integer> neighbors = graph.get(curr);
            for (int neighbor: graph.get(curr)) {
                // 이미 방문한 노드거나, 이미 감염된 놈이면 방문 스킵
                if (visited[neighbor] || infected[neighbor]) continue;
                
                visited[neighbor] = true;
                infectedDuringBFS.add(neighbor);
                q.offer(neighbor);
            }
        }
        return infectedDuringBFS;
    }
    
    static int maxInfects = Integer.MIN_VALUE;
    void dfs(int depth, boolean[] infects, int infectCnt, List<Integer> order) {
        if (depth == k) {
            maxInfects = Math.max(maxInfects, infectCnt);
            return;
        }
        
        Set<Integer> temp = new HashSet<>();
        
        for (int i = 1; i <= 3; i++) {
            temp.clear();
            
            List<List<Integer>> adj = ADJACENT_LISTS[i];
            
            Set<Integer> tempInfected = bfs(adj, infects);

            for (int node: tempInfected) infects[node] = true;
            dfs(depth + 1, infects, infectCnt + tempInfected.size(), order);
            for (int node: tempInfected) infects[node] = false;
        }
    }
    
}