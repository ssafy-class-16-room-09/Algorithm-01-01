import java.util.*;

class Edge{
    String to;
    boolean visited;

    Edge(String t){
        this.to = t;
    }

    @Override
    public String toString(){
        return to + ": " + visited;
    }
}

class Solution{    
    static final boolean DEBUG = true;
    
    // 출발지 -> 도착지 MAP
    static Map<String, List<Edge>> map;    
    // [완성] 여행 경로
    static List<String> answer;
    // 여행 경로 길이
    static int total;    
    // [중간] 여행 경로
    static String[] path;
    static boolean flag;

    public String[] solution(String[][] tickets){
        // ========[정렬]=============
        Arrays.sort(tickets, (o1, o2) -> {
            if (o1[0].equals(o2[0])) return o1[1].compareTo(o2[1]);
            else return o1[0].compareTo(o2[0]);
        });        
        
        // ========[초기화]=============
        map = new HashMap<>();
        answer = new ArrayList<>();

        total = tickets.length;
        path = new String[total + 1];

        for(String[] t : tickets){
            String from = t[0];
            String to = t[1];
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(new Edge(to));
        }       
        // ========[초기화]=============
        
        // ========[디버깅]=============
        if(DEBUG){
            System.out.println("[초기 MAP 구조] [완성 여행 경로 길이 (cnt 값)] " + total);
            for(Map.Entry<String, List<Edge>> each: map.entrySet()){
                System.out.println(each.getKey()+" | "+each.getValue());
            }
        }
        // ========[디버깅]=============
        
        // 항상 인천공항에서 출발함
        path[0] = "ICN";
        dfs("ICN", 0);                                
        
        String[] ans = new String[total + 1]; 
        for(int i = 0; i <= total; i++) ans[i] = answer.get(i);
        return ans;
    }

    void dfs(String from, int cnt){
        if (flag) return;
        
        log(cnt, "cnt : " + cnt + " | 도착 : " + from +  " | 경로 : " + Arrays.toString(path));
        // ========기저조건=============
        List<Edge> list = map.get(from);

        // 전부 도달했다면 성공
        if(cnt == total){                        
            for(int i = 0; i <= total; i++) answer.add(path[i]);                        
            log(cnt, "성공: " + answer);
            flag = true;
            return;
        }
        
        // cnt가 total이 아닌데 더 이상 갈 수 없다면 실패
        if (list == null){
            log(cnt, "막다른 길 (" + from + "에서 출발하는 항공권 없음)");
            return;
        }
        // ========기저조건=============
        
        // ========분기처리=============
        for(Edge next : list){
            // 이미 방문했다면 건너뜀
            if(next.visited){
                log(cnt, "- 건너뜀: " + from + " -> " + next.to + " (이미 사용한 항공권)");
                continue;
            }
            // 다음 경로 하나 선택하고
            next.visited = true;            
            path[cnt + 1] = next.to;
            log(cnt, "-> 사용: " + from + " -> " + next.to);
            
            // 재귀하고
            dfs(next.to, cnt + 1);
            
            // 복귀 처리
            path[cnt + 1] = null;
            next.visited = false;
            log(cnt, "<- 되돌림: " + from + " -> " + next.to);
        }
        // ========분기처리=============
    }

    // [디버그] 재귀 깊이(cnt)만큼 들여쓰기
    static void log(int depth, String message){
        if(!DEBUG) return;
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++) sb.append("    ");
        sb.append(message);
        System.out.println(sb);
    }  
}
