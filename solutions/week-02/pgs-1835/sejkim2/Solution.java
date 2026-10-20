import java.util.*;

class Solution {
    Map<Character, Integer> map = new HashMap<>();
    char[] ar = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    int answer = 0;
    
    public int solution(int n, String[] data) {
        dfs(0, data);
        return answer;
    }
    
    void dfs(int depth, String[] data) {
        if (depth == 8) {
            for(String parse : data) {
                int friendIdx = map.get(parse.charAt(0));
                int adjIdx = map.get(parse.charAt(2));
                char oper = parse.charAt(3);
                int dist = parse.charAt(4) - '0';
                int realDist = Math.abs(friendIdx - adjIdx) - 1;
                
                if (oper == '>') {
                    if (realDist <= dist)
                        return;
                } else if (oper == '<') {
                    if (realDist >= dist)
                        return;
                } else {    //oper == '='
                    if (realDist != dist)
                        return;
                }
            }
            answer++;
            return;
        }
        
        for(int i = 0; i<8; i++) {
            if (!map.containsKey(ar[i])) {
                map.put(ar[i], depth);
                dfs(depth + 1, data);
                map.remove(ar[i]);
            }
        }
    }
}