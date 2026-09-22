import java.util.*;

class Solution {
    static int[]parent;

    public int solution(int n, int[][] computers) {
        parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(computers[i][j]==1)union(i,j);
            }
        }
        int answer=0;
        for(int i=0;i<n;i++){
            if(parent[i]==i)answer++;
        }
        return answer;
    }

    public static void union(int a, int b){
        if(find(a)!=find(b)){
            parent[find(b)]=find(a);
        }
    }
    public static int find(int a){
        if(parent[a]==a){
            return parent[a];
        }else{
            return find(parent[a]);
        }
    }
}