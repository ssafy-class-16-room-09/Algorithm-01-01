import java.util.*;

class Edge implements Comparable<Edge>{
    
    int v1;
    int v2;
    int weight;
    
    Edge(int v, int vv, int w){
        this.v1 = v;
        this.v2 = vv;
        this.weight = w;
    }
    
    @Override
    public int compareTo(Edge o){
        return this.weight - o.weight;
    }
    
}

class Solution {
    
    static int[] Parent;
    
    public int solution(int n, int[][] costs) {
        
        Parent = new int[n];
        for(int i = 0 ; i < n; i++){
            Parent[i] = -1;
        }
        
        Edge[] edges = new Edge[costs.length];
        
        for(int i = 0; i < costs.length; i++){
            int v = costs[i][0];
            int vv = costs[i][1];
            int w = costs[i][2];
            
            edges[i] = new Edge(v, vv, w);
        }
        
        Arrays.sort(edges);
        
        int answer = 0;
        int count = 0;
        
        for(Edge edge: edges){
            
            int v1 = edge.v1;
            int v2 = edge.v2;
            
            // 이미 같은 그룹이면 이 간선을 추가하면 사이클 발생
            if(find(v1) == find(v2)) continue;
            
            union(v1, v2);
            
            answer += edge.weight;
            count++;                        
        }        
        return answer;
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
                        
        // 더 큰 트리에 작은 트리를 붙이기        
        if(Parent[rootX] > Parent[rootY]){
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        
        Parent[rootX] += Parent[rootY];
        Parent[rootY] = rootX;
    }
    
    public int find(int x){
        if(Parent[x] < 0) return x;
        else return find(Parent[x]);
    }
}