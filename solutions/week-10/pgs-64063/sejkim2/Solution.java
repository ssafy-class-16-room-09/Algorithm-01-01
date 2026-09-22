import java.util.*;

class Solution {
    
    Map<Long, Long> parent = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        
        for(int i = 0; i<room_number.length; i++) {
            long room = room_number[i];
            long tmp = find(room);
            room_number[i] = tmp;
        }
        return room_number;
    }
    
    long find(long x) {
        //최상위 부모면 
        if (!parent.containsKey(x)) {
            parent.put(x, x+1);
            return x;
        }
        
        long nextParent = find(parent.get(x));
        parent.put(x, nextParent);  //경로 압축으로 재귀 돌면서 트리 조정
        return nextParent;
    }
}