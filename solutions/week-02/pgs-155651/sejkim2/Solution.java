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
        Arrays.sort(time, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for(int[] t : time) {
            int startMin = t[0];
            if (heap.size() == 0) {
                heap.offer(t);
                answer++;
            } else {
                if (heap.peek()[1] + 10 <= startMin) {
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