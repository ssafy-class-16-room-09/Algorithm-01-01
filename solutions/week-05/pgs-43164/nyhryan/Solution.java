import java.util.*;
import java.util.stream.*;

class Solution {
    // 그래프 간선 클래스
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
    
    public String[] solution(String[][] tickets) {
        N_TICKETS = tickets.length;
        for (String[] ticket: tickets) {
            var e = new Edge(ticket[0], ticket[1]);
            map.computeIfAbsent(ticket[0], k -> new ArrayList<>()).add(e);
        }

        String visitedConcat = "ICN";
        
        // ICN에서 백트래킹 시작
        // "ICNJFKHNDIAD" 와 같이 공항 이름을 붙여가며 결과 정리
        dfs(0, "ICN", visitedConcat);
        
        // 이하 결과 정리하기
        results.sort(Comparator.naturalOrder()); // 결과를 알파벳순 정렬
        
        // 제일 앞선 경로
        String concat = results.get(0);
        
        List<String> ans = new ArrayList<>(concat.length() / 3);
        StringBuilder sb = new StringBuilder();
        // 경로를 세 글자 씩 잘라내며 답 만들기
        for (int i = 0; i <= concat.length() - 3; i += 3) {
            sb.setLength(0);
            
            sb.append(concat.charAt(i))
                .append(concat.charAt(i + 1))
                .append(concat.charAt(i + 2));
            
            ans.add(sb.toString());
        }
        
        return ans.stream().toArray(String[]::new);
    }
    
    static void dfs(int depth, String curr, String visitedConcat) {
        // [기저] 모든 티켓을 다 쓴 경우
        if (depth == N_TICKETS) {
            // System.out.println(visitedConcat);
            results.add(visitedConcat); // 지금까지 방문한 공항 이름을 결과에 삽입
            return;
        }
        
        // 현재 공항에서 갈 수 있는 공항들
        var neighbors = map.get(curr);
        if (neighbors == null || neighbors.isEmpty()) return;
                
        for (Edge e: neighbors) {
            // 현재 DFS 경로에서 방문한 적이 있는 공항이라면 스킵
            if (e.isTempRemoved) continue;
            
            // 백트래킹: "임시로 마크 - 재귀 - 원복" 패턴
            e.isTempRemoved = true;
            dfs(depth + 1, e.to, visitedConcat + e.to);
            e.isTempRemoved = false;
        }
    }
}