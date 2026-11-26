import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long l = 0;
        // 상한값 = 10억분이 걸리는 심사관 1명만 있고, 손님이 10억명오면
        // 모두 심사하는데 10억 * 10억 분이 걸림
        long r = 1_000_000_000L * 1_000_000_000L; 
        long mid;
        
        long minTime = Long.MAX_VALUE;
        
        // n 명을 모두 처리하는 시간을 이진 탐색으로 찾아보기
        while (l <= r) {
            mid = l + (r - l) / 2;
            
            // mid 분까지 처리한 사람 수 구하기
            long processed = 0L;
            for (int t: times) {
                // 처리하는데 t 분 걸리는 심사관이 mid 분까지 심사한 사람 수
                // t 분마다 심사하니깐 mid 분을 t분으로 나눈 몫이 심사한 사람 수
                processed += mid / t;
            }
            
            // mid 분까지 처리한 사람이 입력 사람 수보다 크면 이진탐색 범위 OVER
            // = right를 줄여본다.
            if (processed >= n) {
                minTime = Math.min(minTime, mid);
                r = mid - 1;
            }
            // mid 분까지 처리한 사람이 입력 사람 수보다 모자르면 
            // mid 분을 늘려서 사람을 좀 더 처리해 봐야하니 
            // left를 올린다
            else {
                l = mid + 1;
            }
        }
        
        return minTime;
    }
}