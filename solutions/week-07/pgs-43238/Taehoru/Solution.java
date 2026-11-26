import java.util.*;
import java.io.*;

// 파라메트릭 서치
// 최소값을 구해야하고 입력조건이 억단위를 넘어가니까 써야한다고함

// left는 최소, right는 최대
// 그 중간값인 mid를 기준으로 people >= n이 가능한지
// 즉, mid분 안에 심사가 가능하면 저장 
// people < n 인 경우는 mid 시간 안에 심사가 불가능.
// 그래서 left를 오른쪽으로
// 이것을 반복하는거임 

class Solution {
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        
        // 최솟값과 최대
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        long limit = right;
        
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long people = 0;
            
            // mid 기준으로 몇 명 처리 가능한지
            for (int t : times) {
                people += mid / t;
                
                if (people >= n) {
                    break;
                }
            }
            
            if (people >= n) {
                limit = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
            
        }
        
        return limit;
    }
}