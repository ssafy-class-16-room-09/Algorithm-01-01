import java.util.*;

class Solution {
    Map<Character, Integer> map = new HashMap<>();
    char[] ar = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    int answer = 0;
    
    public int solution(int n, String[] data) {
        dfs(0, data);
        return answer;
    }

    //visit + 저장 배열 대신 map 사용
    //일단 dfs 돌리고 마지막에 제한조건 만족하는지 검사 (value(자리)로 위치 계산)
    void dfs(int depth, String[] data) {
        if (depth == 8) {
            for(String parse : data) {
                int friendIdx = map.get(parse.charAt(0));
                int adjIdx = map.get(parse.charAt(2));
                char oper = parse.charAt(3);
                int dist = parse.charAt(4) - '0';
                int realDist = Math.abs(friendIdx - adjIdx) - 1;    //두 카카오프렌즈 사이 사람 개수에 주의
                
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

        //방문한 적 없으면 (map에 없으면) map에 저장(key:카카오프렌즈, value : 그 친구가 설 자리)
        for(int i = 0; i<8; i++) {
            if (!map.containsKey(ar[i])) {
                map.put(ar[i], depth);
                dfs(depth + 1, data);
                map.remove(ar[i]);
            }
        }
    }
}
