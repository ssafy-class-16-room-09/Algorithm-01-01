import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int idx = 0;
        
        //전략 : 일단 데미지 계속 입히고 죽으면 맥뎀기록장치에서 맥뎀만큼 회복하고 무적권 사용하기
        for(; idx < enemy.length; idx++) {
            n -= enemy[idx];
            heap.offer(enemy[idx]);
            
            if (n < 0) {
                int maxDamage = heap.poll();
                if (k == 0)
                    break;
                k--;
                n += maxDamage;
            }
        }
        int t = 0;
        for(; t < 5; t++);
            
        System.out.println("wow"+ t);
        return idx;
    }
}