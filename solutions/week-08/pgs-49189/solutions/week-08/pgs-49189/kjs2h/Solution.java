import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        List<Integer>[] nodes = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            nodes[i] = new ArrayList<Integer>();
        }
        
        for(int[] edge: edges){
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(edge[0]);
        }
        
        for(int i = 0; i < n; i++){
            List<Integer> list = nodes[i];
            System.out.print(i+" :");
            for(int num: list){
                System.out.print(num+" ");
            }
            System.out.println();
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(1);
        
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        
        int[] linkedCnt = new int[n+1];
        linkedCnt[1] = 0;
                
        
        while(!dq.isEmpty()){
            int now = dq.poll();         
            System.out.println("방문: "+now+" ");
            visited[now] = true;
            int curr = linkedCnt[now];            
            
            for(int link: nodes[now]){
                
                if (!visited[link]){
                    System.out.println(link+"의 첫 방문을 환영합니다.");                   
                    dq.offer(link);
                    visited[link] = true;
                    linkedCnt[link] = curr+1;                                        
                }else{
                    System.out.println(link+" 노드는 이미 방문했습니다.");                    
                    continue;
                }                
            }                                   
        }
        int max = 0;
        for(int cnt: linkedCnt){
            max = Math.max(max,cnt);
        }
        
        int answer = 0;
        for(int i = 1; i <= n; i++){
            if(linkedCnt[i] == max) answer++;
        }
        System.out.println(Arrays.toString(linkedCnt));
        
        return answer;
    }
}