import java.util.*;

class Solution {
    
    static Map<Long,Long> reqToAssign;  
    
    public long[] solution(long k, long[] room_number) {        
        reqToAssign = new HashMap<>();                
        
        for(int i = 0; i < room_number.length; i++){
            
            long required = room_number[i];
            
            long assign = find(required);
            
            room_number[i] = assign;
        }                
        return room_number;        
    }                            
    
    public long find(long x){        
        if(!reqToAssign.containsKey(x)){
            reqToAssign.put(x,x+1);
            return x;       
        }else{                                  
            long now = find(reqToAssign.get(x));
            reqToAssign.put(x,now);
            return now;       
        }
    }
    
    
}