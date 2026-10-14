import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        
        while (start <= end) {
            if (start == end) {
                answer++;
                break;
            }
            if (people[start] == limit)
                start++;
            else if (people[end] == limit)
                end--;
            else {
                if (people[start] + people[end] <= limit) {
                    start++;
                    end--;
                } else
                    end--;
            }
            answer++;
        }
        return answer;
    }
}