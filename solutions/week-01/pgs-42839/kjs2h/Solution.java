import java.util.*;

class Solution {
    
    static int ANS;
    static int MAXI;    
    static int[] UNIV;
    static boolean[] VISITED;
    static List<List<Integer>> IDXLIST;
    
    public int solution(String numbers) {
        ANS = 0;
        MAXI = numbers.length();
        UNIV = new int[MAXI];
        VISITED = new boolean[MAXI];
        IDXLIST = new ArrayList<>();
        
        for(int i = 0; i < MAXI; i++){
            UNIV[i] = numbers.charAt(i) - '0';
        }
        
        for(int s = 1; s <= MAXI; s++){
            List<Integer> idxs = new ArrayList<>();
            permutation(0,s,idxs);
        }
        
        Set<Integer> already = new HashSet<>();
        for(List<Integer> IDX: IDXLIST){
            StringBuilder sb = new StringBuilder();
            for(int idx: IDX){                
                sb.append(UNIV[idx]);
            }
            int num = Integer.parseInt(sb.toString());
            if (!already.contains(num) && isPrime(num)) ANS++;
            already.add(num);
        }
        return ANS;
    }
    
    void permutation(int depth, int required, List<Integer> idxs){
        List<Integer> curr = idxs;
        if (depth == required){
            IDXLIST.add(new ArrayList<>(idxs));
            return;
        }
        
        for(int i = 0; i < MAXI; i++){
            if (VISITED[i]) continue;
            curr.add(i);
            VISITED[i] = true;
            permutation(depth + 1, required, curr);
            
            curr.remove(curr.size()-1);
            VISITED[i] = false;            
        }                        
    }
    
    boolean isPrime(int n){
        if (n < 2) return false;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0) return false;
        }
        return true;
    }
}