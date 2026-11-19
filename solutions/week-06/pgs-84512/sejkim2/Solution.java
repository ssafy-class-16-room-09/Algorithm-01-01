import java.util.*;

class Solution {
    
    Set<String> set;
    int answer;
    char[] ch = {'A','E','I','O','U'};
    static final int LIMIT = 5;
    
    public int solution(String word) {
        answer = 0;
        set = new HashSet<>();
        dfs(0, "", word);
        return answer;
    }
    
    void dfs(int depth, String str, String word) {
        //같을때 set의 크기가 곧 사전순서
        if (str.equals(word)) {
            answer = set.size();
            return;
        }
        if (depth == LIMIT) 
            return;
    
        //각 단계마다 만들수 있는 문자열 set에 넣기
        //모음 순서대로 추가해서 넣기 때문에 사전순 보장됨 (A, AA, AAA ....)
        for(int i = 0; i < 5; i++) {
            set.add(str+ch[i]);
            dfs(depth + 1, str+ch[i], word);
        }
    }
}