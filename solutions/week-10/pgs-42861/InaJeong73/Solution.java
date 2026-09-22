import java.util.*;

class Solution {
    static int sum=0;
    static int[]parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents=new int[n];
        for(int i=0;i<parents.length;i++)   parents[i]=i;

        Arrays.sort(costs,(a,b)->{return Integer.compare(a[2],b[2]);});
        for(int i=0;i<costs.length;i++){
            union(costs[i][0],costs[i][1],costs[i][2]);
        }
        return sum;
    }

    // 두 노드를 합치는 함수
    public void union(int n0,int n1, int w){
        //각 노드의 부모가 달라야 합치기 가능 -> 같으면 사이클 발생하므로 합치기 불가
        if(find(n0)!=find(n1)){
            parents[find(n0)]=find(n1);
            sum+=w;
        }
    }

    //부모 노드 조회 함수
    public int find(int a){
        if(parents[a]==a){
            return parents[a];
        }
        return parents[a]=find(parents[a]);
    }
}