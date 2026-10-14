import java.util.*;

class Solution {
    boolean[] visit;
    int len;
    boolean[] isPrime;
    Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        len = numbers.length();
        visit = new boolean[len];
        char[] nums = numbers.toCharArray();
        Arrays.sort(nums);
        int max = Integer.parseInt(new StringBuilder(new String(nums)).reverse().toString());
        
        isPrime = new boolean[max+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i = 2; i<=Math.sqrt(max); i++) {
            if (isPrime[i] == true) {
                for(int j = i*i; j <= max; j += i)
                    isPrime[j] = false;
            }
        }
        
        dfs(0, numbers, "");
        
        for(int n : set) 
            if (isPrime[n])
                answer++;
        
        return answer;
    }
    
    void dfs(int depth, String numbers, String result) {
        if (depth == len) {
            return;
        }
        
        for(int i = 0; i<len; i++) {
            if (!visit[i]) {
                visit[i] = true;
                set.add(Integer.parseInt(result+numbers.charAt(i)));
                dfs(depth + 1, numbers, result+numbers.charAt(i));
                visit[i] = false;
            }
        }
    }
}