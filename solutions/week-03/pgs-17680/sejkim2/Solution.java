import java.util.*;

class Solution {
    static final int HIT = 1;
    static final int MISS = 5;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        //map 사용한 근거 : 캐시에 저장된 "도시"를 바로 꺼내야 하므로, value는 저장시간
        //treemap 사용한 근거 : 캐시에 도시가 없을 때 && 캐시가 꽉 차서 사용한지 가장 오래된 캐시 삭제해야 할 때 바로 삭제하기 위해서 
        
        Map<String, Integer> cache = new HashMap<>();
        TreeMap<Integer, String> time = new TreeMap<>();
        
        //와우 이걸 생각못해서...
        if (cacheSize == 0)
            return MISS * cities.length;
        
        for(String city : cities) {
            String upperCity = city.toUpperCase();  //대소문자 구분 없으므로 모두 대문자로 통일
            
            if (cache.size() == 0) { // 캐시 비어있으면 무조건 cash miss (주어진 cacheSize가 0이 아닐때만)
                answer += MISS;
                //캐시에 넣고 시간 기록
                cache.put(upperCity, answer);   
                time.put(answer, upperCity);
            } else {
                //case1 : cache hit
                if (cache.containsKey(upperCity)) {
                    int key = cache.get(upperCity);
                    answer += HIT;
                    cache.put(upperCity, answer);    //least recently used update
                    time.remove(key);               //이전 접근 시간 지우고 다시 갱신
                    time.put(answer, upperCity);
                } else {        //case2 : cache miss
                    answer += MISS;
                    if (cache.size() != cacheSize) {    //캐시 공간 남았을 때
                        cache.put(upperCity, answer);
                        time.put(answer, upperCity);
                    } else {    //cache 꽉차서 least recently used 제거해야 함
                        int key = time.firstKey();  //treemap이므로 가장 오래전에 접근한 시간 (가장 작은 시간)이 첫번째 원소에 있음
                        String removeCity = time.get(key);  //그 시간으로 도시를 꺼내서 캐시에서 지움
                        cache.remove(removeCity);
                        
                        cache.put(upperCity, answer);          //다시 도시 시간 갱신하고 시간treemap도 갱신
                        time.remove(key);
                        time.put(answer, upperCity);
                    }
                }
            }
        }
        return answer;
    }
}