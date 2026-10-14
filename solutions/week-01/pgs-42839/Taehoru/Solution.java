import java.util.*;

class Solution {
    static Set<Integer> set;
    static boolean[] visited;
    static char[] cards;
    
    public int solution(String numbers) {
        set = new HashSet<>();
        cards = numbers.toCharArray();
        visited = new boolean[cards.length];
        
        dfs("");
        
        int answer = 0;
        for (int num : set) {
            if (isPrime(num)) answer++;
        }
        return answer;
    }
    
    static void dfs(String curr) {
        if (!curr.isEmpty()) {
            set.add(Integer.parseInt(curr));
        }
        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(curr + cards[i]);
            visited[i] = false;
        }
    }
    
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}