import java.util.*;

class Solution {
    static String[] alphas = {"A", "E", "I", "O", "U"};
    
    static String givenWord;
    
    public int solution(String word) {
        givenWord = word;
        dfs(0, new StringBuilder());
        return walkCount;
    }
    
    static int walkCount = 0;
    boolean dfs(int depth, StringBuilder sb) {
        // [기저] 찾고자 하는 단어이면 성공으로 DFS 종료
        if (sb.toString().equals(givenWord)) {
            return true;
        }
        
        // 단어 길이가 5보다 길어지면 실패로 종료
        if (depth >= 5) return false;
        
        int len = sb.length();
        for (String a: alphas) {
            // 백트래킹
            sb.append(a);
            
            // 탐색 횟수 어떻게 DFS 내에서 셀 지 몰라서 전역으로 셈
            walkCount++;
            
            // 단어를 찾아서 성공으로 종료하면 위로 전파하기
            if (dfs(depth + 1, sb)) {
                return true;
            }
            // 백트래킹 원복
            sb.setLength(len);
        }
        
        // 현재 레벨에서 탐색 실패
        return false;
    }
}