import java.util.*;
import java.util.stream.*;

class Solution_better {
    static class Edge {
        String from, to;
        boolean isTempRemoved;
        public Edge(String from, String to) {
            this.from =from;
            this.to = to;
        }
        
        @Override
        public String toString() {
            return to; 
        }
    }
    
    
    // 공항 -> [이웃공항...] 연결리스트
    static Map<String, List<Edge>> map = new HashMap<>();
    static List<String> results = new ArrayList<>();
    
    static int N_TICKETS;
    
    static List<String> path = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        N_TICKETS = tickets.length;
        for (String[] ticket: tickets) {
            var e = new Edge(ticket[0], ticket[1]);
            map.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(e);
        }
        
        // ICN -> AAA, BBB, CCC, ... 순으로 이웃공항을 이름순으로 미리 정렬
        for (List<Edge> edges: map.values()) {
            edges.sort(Comparator.comparing((Edge e) -> e.to));
        }
        
        // ICN에서 백트래킹 시작
        dfs(0, "ICN");
        path.add("ICN");
        
        String[] ans = new String[path.size()];
        for (int i = path.size() - 1; i >= 0; i--) 
            ans[ans.length - i - 1] = path.get(i);
        return ans;
    }
    
    static boolean dfs(int depth, String curr) {
        // [기저] 모든 티켓을 다 쓴 경우
        if (depth == N_TICKETS) {
            return true;
        }
        
        var neighbors = map.get(curr);
        if (neighbors == null) return false;
                
        // 이웃 공항을 이름 순으로 정렬했음.
        for (Edge e: neighbors) {
            if (e.isTempRemoved) continue;
            
            e.isTempRemoved = true;
            if (dfs(depth + 1, e.to)) { // 모든 티켓을 다 써서 탐색에 성공한 경우
                path.add(e.to); // 역으로 나오면서 경로 추적
                return true;   
            }
            e.isTempRemoved = false;
        }

        return false;
    }
}