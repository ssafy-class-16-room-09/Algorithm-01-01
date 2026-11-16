import java.util.*;

class Solution {
    //word와 result를 담은 map
    Map<String,Integer> map = new HashMap<>();
    //단어사전
    String[] vowel = new String[]{"A","E","I","O","U"};
    //순차 인덱스
    int idx = 0;
    
    public int solution(String word) {
        //str로 시작하는, 단어사전의 조합으로 만들 수 있는 모든 단어 (사전식)        
        makeWord("");                        
        return map.getOrDefault(word,0);
    }
    
    public void makeWord(String now){        
        if (now.length() == 6) return;
        
        if(!now.isEmpty()){
            idx+=1;
            map.put(now,idx);
        }                
        
        for(String v: vowel){
            makeWord(now+v);
        }
    }
}