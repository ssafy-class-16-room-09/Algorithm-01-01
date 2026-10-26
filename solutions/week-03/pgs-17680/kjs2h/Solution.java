// cacheSize가 0인 경우 모든 요청이 Miss 처리되므로 예외 처리 (실행 시간 * 5)
// 입력되는 도시 이름을 모두 소문자로 통일하여 처리 (대소문자 구분 없음)
// 캐시 히트와 캐시 미스를 나누어서
// 히트인 경우 (map.containsKey) 해당 key의 runtime에 + 해준다
// 미스인 경우 : map에서 가장 적은 runtime을 가진 것을 빼고 지금걸 더한다
import java.util.*;

class City implements Comparable<City> {
    String name;
    int used;
    
    City(String n, int u) {
        name = n;
        used = u;
    }
    
    @Override 
    public int compareTo(City o){
        return Integer.compare(this.used, o.used);  
    } 
}

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
        // City를 담은 cache
        List<City> cache = new ArrayList<>();
        // 도시 이름으로 City에 빠르게 접근하기 위한 map
        Map<String, City> map = new HashMap<>(); 
        
        
        int runtime = 0;        
        for (int i = 0; i < cities.length; i++) {
            // 전부 소문자로 변경
            String city = cities[i].toLowerCase(); 
            
            // Cache Hit
            if (map.containsKey(city)) {
                runtime += 1;
                
                City curr = map.get(city);
                curr.used = i; // 최근 사용 시간 갱신
            } 
            // Cache Miss
            else {
                runtime += 5;
                
                // 캐시가 가득 찼다면 가장 오래된 항목 제거
                if (cache.size() >= cacheSize) {
                    Collections.sort(cache);
                    
                    City oldest = cache.get(0);
                    
                    cache.remove(0);
                    map.remove(oldest.name);
                }
                
                City curr = new City(city, i);
                cache.add(curr);
                map.put(city, curr);
            }
        }
        
        return runtime;
    }
}