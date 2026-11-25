import java.util.*;

// 27 / 7 = 3, 27 / 10 = 2 | 3+2=5
// 28 / 7 = 4, 27 / 10 = 2 | 4+2=6
// 29 / 7 = 4, 27 / 10 = 2 | 4+2=6

class Solution {

    public long solution(int n, int[] times) {
        long left = 1;
        long right = Long.MAX_VALUE;
        long answer = right;

        while(left <= right){
            long mid = left + (right - left) / 2;
            long possible = 0;

            for(int time: times){
                possible += mid / time;
                if (possible >= n) break;
            }

            if (possible >= n){
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }

        return answer;
    }

}
