import java.util.*;

class Solution {
    int[] parent;
    
    public int solution(int n, int[][] computers) {
        
        parent = new int[n];
        for(int i = 0; i<n; i++)
            parent[i] = i;
        
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                if (i == j) continue;
                if (computers[i][j] == 1)
                    union(i, j);
            }
        }
        
        for(int i = 0; i<n; i++)
            find(i);
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<n; i++) 
            set.add(parent[i]);
        
        return set.size();
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