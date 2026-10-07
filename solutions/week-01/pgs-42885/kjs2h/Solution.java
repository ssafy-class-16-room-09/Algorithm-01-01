import java.util.*;

class Solution {
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int start = 0;
        int end = people.length - 1;

        int anchovy = start;
        int pig = end;
        
        while(anchovy <= pig){
            
            //돼지가 없음
            if (anchovy > pig) break;
            
            //돼지는 있는데 멸치가 없음
            if (anchovy == pig){
                answer++;
                break;
            }
            
            //돼지가 너무 무거움
            if (people[pig] + people[anchovy] > limit){
                //돼지 혼자 타라
                pig--;
                answer++;
                continue;
            }
            
            //딱 좋음, 멸치도 타라
            pig--;
            anchovy++;
            answer++;
        }
        
        return answer;
    }
}

-----죽은 코드의 무덤-----
import java.util.*;

class Solution {
    
    static PriorityQueue<Integer> maxHeap;
    static PriorityQueue<Integer> minHeap;
    static Map<Integer,Integer> garbage;
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        garbage = new HashMap<>();
        
        for(int p: people){
            maxHeap.offer(p);
            minHeap.offer(p);
            garbage.put(p, garbage.getOrDefault(p,0)+1);
        }         
           
        while(true){
            int pig = getMax();
            
            //돼지가 없음
            if (pig == -1) break;
            
            int anchovy = checkMin();
            
            //돼지는 있는데 멸치가 없음
            if (anchovy == -1){
                answer++;
                break;
            }
            
            //돼지가 너무 무거움
            if (pig + anchovy > limit){
                //돼지 혼자 타라          
                answer++;
                continue;                
            }            
            
            //딱 좋음, 멸치도 타라
            getMin();
            answer++;            
        }      
        
        return answer;
    }
    
    int getMax(){
        while(!maxHeap.isEmpty()){
            int candidate = maxHeap.poll();            
            
            if (garbage.getOrDefault(candidate,0) > 0){
                garbage.put(candidate, garbage.get(candidate) - 1);
                return candidate;  
            } 
        }
        
        return -1;
    }
    
    int getMin(){
        while(!minHeap.isEmpty()){
            int candidate = minHeap.poll();
            
            if (garbage.getOrDefault(candidate,0) > 0){
                garbage.put(candidate, garbage.get(candidate) - 1);
                return candidate;
            }
        }
        
        return -1;
    }        
    
    int checkMin(){
        while(!minHeap.isEmpty()){
            int candidate = minHeap.peek();
            
            if (garbage.getOrDefault(candidate,0) > 0){
                return candidate;
            }
            
            minHeap.poll();
        }
        
        return -1;
    }
}
