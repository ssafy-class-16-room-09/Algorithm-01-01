import java.util.*;

//1개 1-> 정답칸
//2개 2+1-> 작 빈칸 -> 큰 정답칸 -> | 1개 
//3개 4+2+1-> 작 정답칸 -> 중 빈칸 -> 작 빈칸 -> 큰 정답칸 -> | 2개
//4개 7+4+2+1-> 작 빈칸 -> 약작 정답칸 -> 작 정답칸 -> 약큰 빈칸 -> 작 빈빈칸 -> 약작 빈칸 -> 작 빈칸 | 3개

// 위의 n-1개를 from → avail으로 옮긴다.
// 가장 큰 원판 1개를 from → to로 옮긴다. (1회)
// 나머지 n-1개를 avail → to로 옮긴다.



// n-1개를 1->2로 옮기는 것과 2->3으로 옮기는 것은 똑같음

class Solution {        
    
    static List<int[]> ans;
    
    public int[][] solution(int n) {                                
                
        ans = new ArrayList<>();
        hanoi(n,1,3);                
        
        int[][] answer = new int[ans.size()][2];
        for(int i = 0; i < ans.size(); i++) answer[i] = ans.get(i);
        return answer;
    }
    
    //n 은 옮겨야 하는 개수
    //from은 출발 기둥
    //to는 도착 기둥
    void hanoi(int n, int from, int to){
        int avail = 6 - (from + to);
        
        if (n == 1){
            ans.add(new int[]{from,to});
            return;
        }
        
        // 위의 n-1개를 from → avail으로 옮긴다.
        hanoi(n-1, from, avail);
        
        // 가장 큰 원판 1개를 from → to로 옮긴다. (1회) 
        // System.out.println((n-1)+"|"+from+"->"+avail+"끝.");
        ans.add(new int[]{from,to});
        // System.out.println("지금 시간에 가장 큰 원반 옮기기 끝.");
        
        // 나머지 n-1개를 avail → to로 옮긴다.                
        hanoi(n-1, avail, to);        
        // System.out.println((n-1)+"|"+avail+"->"+to+"끝.");                
        
    }
}