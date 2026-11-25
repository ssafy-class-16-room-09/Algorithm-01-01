import java.util.*;
import java.io.*;

// 그리디
// 오름차순 정렬 후 B가 최소값으로 이기기
// B가 A보다 작거나 같으면 pass

class Solution {
    public int solution(int[] A, int[] B) {
        // 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 포인터용
        int A_idx = 0;
        int B_idx = 0;
        
        // 이겼을 때 카운트
        int count = 0;
        
        
        while (A_idx < A.length && B_idx < B.length) {
            
            // A팀은 고정시키고 최소 만족이 될때까지 반복
            if (A[A_idx] < B[B_idx]) {
                count++;
                A_idx++;
                B_idx++;
            }
            else {
                B_idx++;;
            }
        }
        return count;
    }
}