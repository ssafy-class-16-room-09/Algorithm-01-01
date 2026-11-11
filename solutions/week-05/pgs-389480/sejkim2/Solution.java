import java.util.*;
class Solution {
    int size;
    int global_n;
    int global_m;
    int answer;
    Set<String>[] set;
    
    public int solution(int[][] info, int n, int m) {
        answer = Integer.MAX_VALUE;
        size = info.length;  
        global_n = n;
        global_m = m;
        set = new HashSet[size];
        for(int i = 0; i<size; i++)
            set[i] = new HashSet<>();
        
        dfs(0, info, 0, 0);
        
        if (answer == Integer.MAX_VALUE)
            answer = -1;
        
        return answer;
    }
    
    void dfs(int depth, int[][] info, int sumA, int sumB) {
        if (answer < sumA)
            return;
        
        if (depth == size) {
            answer = Math.min(answer, sumA);
            return;    
        }
        
        //특정 시점에서(depth) 
        //(sumA, sumB)쌍이 같다면 그 다음 분기는 모두 결과가 같으므로 볼 필요 x
        if (set[depth].contains(sumA+" "+sumB))
            return;
        set[depth].add(sumA+" "+sumB);
        
        if (info[depth][1] + sumB < global_m)
            dfs(depth + 1, info, sumA, info[depth][1] + sumB);
        if (info[depth][0] + sumA < global_n)
            dfs(depth + 1, info, info[depth][0] + sumA, sumB);
    }
}