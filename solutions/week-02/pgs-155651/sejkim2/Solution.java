import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] time = new int[book_time.length][2];
        int idx = 0;
        for(String[] t : book_time) {
            int startMin = string2min(t[0]);
            int endMin = string2min(t[1]);
            time[idx][0] = startMin;
            time[idx][1] = endMin;
            idx++;
        }
        //시작 시간 기준으로 정렬 (모든 방을 배정해야 하므로)
        Arrays.sort(time, (a, b) -> a[0] - b[0]);
        //시작 시간 기준으로 빠른 방부터 넣고 heap에서 꺼낼때는 종료시간이 빠른 방부터 꺼냄(특정 방을 보고 있을 때 만약 heap에서 종료 가능한 방이 있으면 그 방에 배정함)
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for(int[] t : time) {
            int startMin = t[0];
            if (heap.size() == 0) {
                heap.offer(t);
                answer++;
            } else {
                if (heap.peek()[1] + 10 <= startMin) {    //10분 청소하는거 주의
                    heap.poll();
                    heap.offer(t);
                } else {
                    heap.offer(t);
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    static int string2min(String time) {
        String[] parse = time.split(":");
        int hour = Integer.parseInt(parse[0]) * 60;
        int minute = Integer.parseInt(parse[1]);
        
        return hour + minute;
    }
}
