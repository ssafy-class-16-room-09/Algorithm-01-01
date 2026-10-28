import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        // pq
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < enemy.length; i++) {

            pq.add(enemy[i]);
            
            // 병사소모
            if (pq.size() > k) {
                n -= pq.poll();
            }
            
            // 병사부족
            if (n < 0) {
                // 라운드 수 반환
                return i;
            }
        }
        // 모든 라운드를 막았다? 끝
        return enemy.length;
    }
}