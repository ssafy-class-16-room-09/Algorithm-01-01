import java.util.*;

class Solution {
    
    static int[] Parent;    
    public int solution(int n, int[][] computers) {
        
        Parent = new int[n];
        
        for(int i = 0; i < n; i++){
            Parent[i] = -1;
        }
        
        
        for(int i = 0; i < n; i++){            
            for(int j = 0; j < n; j++){   
                if (i==j) continue;                
                if (computers[i][j] == 1) union(i,j);
            }
        }
        
        int answer = 0;        
        for(int i = 0; i < n; i++){
            System.out.print(Parent[i]+" ");
            if (Parent[i] < 0) answer += 1;
        }                        
        return answer;        
    }
    
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX == rootY) return;
        
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