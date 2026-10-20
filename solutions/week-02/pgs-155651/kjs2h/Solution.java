import java.util.*;
class Solution {                    
    
    static int[][] times;
    static int n;
    static int t;
    
    public int solution(String[][] book_time) {        
        StringTokenizer st;
        // 일단 String을 int로 바꿔
        times = new int[book_time.length][2];                
        for(int i = 0; i < book_time.length; i++){
            st = new StringTokenizer(book_time[i][0],":");
            int start = 60 * Integer.parseInt(st.nextToken());
            start += Integer.parseInt(st.nextToken());  
                      
            st = new StringTokenizer(book_time[i][1],":");
            int end = 60 * Integer.parseInt(st.nextToken());
            end += Integer.parseInt(st.nextToken());

            times[i][0] = start;
            times[i][1] = end+10;
        }
        
        // 그리고 회의실 배정을 해 (주의: 시작순서를 빠르게 해야 됨)
        Arrays.sort(times, (o1,o2) -> {
            if (o1[0]==o2[0]) return Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[0], o2[0]);
        });
                
        Deque<int[]> dq = new ArrayDeque<>();
        for(int[] time: times) dq.offer(time);
        
        n=0; 
        //남아있는 회의가 있으면 방을 하나 빌려
        while(!dq.isEmpty()){
            int l = dq.size();
            t=0;
            
            //그 방에다가 탐욕적으로 전부 다 배정해
            while(l-- > 0){
                int[] time = dq.poll();
                if (t <= time[0]) t = time[1];
                else dq.offer(time);
            }                        
            n++;            
        }
        return n;
    }
}