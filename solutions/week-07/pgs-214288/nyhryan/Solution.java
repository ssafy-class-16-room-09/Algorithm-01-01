import java.util.*;
import java.util.stream.*;
class Solution {
    static int k, n;
    static List<int[]> possibleCombinations;
    
    // record Person(int arrival, int time) {}
    
    public int solution(int k, int n, int[][] reqs) {
        Solution.k = k;
        Solution.n = n;
        
        List<int[]>[] reqsByCat = new ArrayList[k];
        for (int i = 0; i < k; i++) reqsByCat[i] = new ArrayList<int[]>();
        for (int[] req: reqs) {
            reqsByCat[req[2] - 1].add(req);
        }
        
        // 19 C 4 = 20명을 5 그룹으로 나누는 모든 가짓 수
        // = 20명 사이에 있는 19개의 공간에 4개의 자리를 뽑아서 파티션을 세우고 그룹을 5개로 나누는 가짓수
        // = 3876
        possibleCombinations = new ArrayList<>(3876);
        int[] staffs = new int[k];
        dfs(0, n, staffs);
                
        PriorityQueue[] rooms = new PriorityQueue[k];
        for (int i = 0; i < k; i++) rooms[i] = new PriorityQueue<Integer>();
        
        int minWait = Integer.MAX_VALUE;
        for (int[] possibleComb: possibleCombinations) {
            
            int currentCombTotalWait = 0;
            
            // System.out.println("testing: " + Arrays.toString(possibleComb));
            for (int cat = 0; cat < k; cat++) {
                int currentTotalWait = 0;
                
                int staffCount = possibleComb[cat];
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                while (staffCount-- > 0) pq.offer(0);
                List<int[]> people = reqsByCat[cat];
                // System.out.println("\t" + cat + "번 유형 " + people.stream().map(Arrays::toString).collect(Collectors.joining(",")));
                for (int pIdx = 0; pIdx < people.size(); pIdx++) {
                    int[] req = people.get(pIdx);
                    int arrival = req[0], duration = req[1];
                    
                    int earliestEnd = pq.poll();
                    int newEnd = 0;
                    if (earliestEnd <= arrival) {
                        newEnd = arrival + duration;
                    }
                    else {
                        // System.out.println("\t\t" +pIdx + " 번 사람이 "+ (earliestEnd - arrival) +" 만큼 대기");
                        currentTotalWait += (earliestEnd - arrival);
                        newEnd = earliestEnd + duration;
                    }
                    
                    pq.offer(newEnd);
                }
                
                currentCombTotalWait += currentTotalWait;
            }
            
            if (currentCombTotalWait < minWait) {
                minWait = currentCombTotalWait;
                // System.out.println("작다! " + Arrays.toString(possibleComb)  + ", total=" + minWait);
            }
        } 
        

        return minWait;
    }
    
    void dfs(int depth, int remain, int[] staffs) {
        if (depth == k - 1) {
            staffs[depth] = remain;
            possibleCombinations.add(staffs.clone());
            return;
        }
        
        for (int i = 1; i <= remain - (k - depth - 1); i++) {
            staffs[depth] = i;
            dfs(depth + 1, remain - i, staffs);
        }
    }
}