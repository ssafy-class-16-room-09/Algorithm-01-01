import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;

public class Solution
 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        // int[] nums = new int[n];
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // nums[i] = sc.nextInt();
            nums.add(sc.nextInt());
        }

        // 턴의 수: n
        // 목표 수: m, (m-1 만큼 전진 필요)
        // 말의 수 : K

        // cycle
        // 윷놀이에서 나온 모든 수들(전진)의 부분 합을 SubSum이라고 할 때
        // 1개 말이 전진하는데 가져야하는 값은 
        // Subsum >= m-1 이고 SubSum-(m-1) 의 차는 최소화 해야함
    
        int bitMask = -1;
        int answer = 0;
        int min = -1;
        int subSum = -1;

        // 첫번째 말부터 도착지로 이동 
        for(int mal = 0; mal < k ; mal++){
            min = Integer.MAX_VALUE;
            
            bitMask = -1;
            // 현재 말이 앞으로 갈 숫자들 조합 계산
            // 선택할 수 있는 수들의 부분 합 중 가장 작은 값(subSum>=m-1 조건)을 찾는 반복문
            for(int masking = 0; masking < 1 << nums.size(); masking++){

                subSum=0;
                // 조합으로 부분합 계산
                for(int i = 0 ; i < nums.size(); i++){
                    if((masking & (1 << i)) != 0){
                        subSum += nums.get(i);
                    }
                }

                // 현재 주어진 숫자들로 만든 최소 부분 합이 조건이 만족하는 최솟값인가?
                if(subSum >= m-1 && subSum < min){
                    bitMask = masking;
                    min = subSum;
                }
            }

            // 조건에 충족하는 조합이 있는지?
            // 현재 선택된 말이 도착지에 도착할 수 있는가?
            if(bitMask != -1){
                // 뒤부터 지우는 이유는 비트마스킹은 index 숫자로 지우는데,
                // 앞부터 지우면 배열이 인덱스 값이 앞으로 밀림
                for(int i = nums.size()-1 ; i >= 0 ; i--){
                    if((bitMask & (1 << i)) != 0){
                        nums.remove(i);
                    }
                }
                answer++;
            }
        }

        System.out.println(answer);
    }
}
