

import java.util.*;

class Solution {	
    int answer=0;
    Node headNode;
    Node tailNode;
    Map<String,Node> cache=new HashMap<>();
    
    public int solution(int cacheSize, String[] cities) {
        
        Node tempNode;
        
        // cache 사이즈 0일때
        if(cacheSize==0){
            return cities.length*5;
        }
        
        // cache 사이즈 1 이상 일때 진행
        for(String city:cities){
            
        	// System.out.println("<<"+city+">>");
        	city=city.toUpperCase();
            
            // cacheHit 처리 <- 여기서 pop과 add 순서로 디버깅함
            if(cache.containsKey(city)){
                
                // cache에 city가 존재한다면
                // 캐시에서 데이터를 가져온다.
                cacheHit();
                
                // 이전 풀이 개선점
                // 가장 최근 조회한 데이터를 바로 다시 조회하는 경우, 현재 캐시 상태 유지
                if(cache.get(city).post==null){
                    continue;
                }
                
                // 캐시에서 데이터를 가져오고, 가장 최근 조회 여부를 연결 리스트 tail로 옮겨 나타냄
                tempNode=cache.get(city);
                tempNode=cachePop(tempNode);
                cacheAdd(tempNode);
                continue;
            }
            
            
            // 이하 cacheMiss 처리
            
            // 캐시사이즈가 꽉 안차면
            // 캐싱
            if(cache.size()<cacheSize){
                tempNode=new Node(null,city,null);
                cacheAdd(tempNode);
            }
            // 캐시사이즈가 꽉 차면
            // LRU 데이터 빼고 현재 조회 데이터를 캐싱 
            else{
                tempNode= new Node(null,city,null);
                cacheAdd(tempNode);
                tempNode=cachePop(headNode);
            }
            cacheMiss();
// 출력 디버깅
//             Node temp;
//             temp=headNode;
//              while(true){
//                  System.out.print(temp.city+" ");
//                  if(temp.post==null)
//                      break;
//                  temp=temp.post;
//              }
             // System.out.println();
            
        }
        
        
        return answer;
    }
    // cacheHit 시간 계산
    void cacheHit(){
        answer+=1;
    }
    // cacheMiss 시간 계산
    void cacheMiss(){
        answer+=5;
    }
    
    // 캐시 구조
    // 각 캐시 공간(노드)로 접근하는 cache
    // Map 자료구조를 사용하여 모든 노드를 O(1) 시간 안에 접근 가능
    
    // 각 노드는 Map에 등록됨
    
    //       {node A : city 1} <-> {node B : city 2} <-> {node C : city 3}
    // (head, Least Recently Used)                      (tail, Recently Used) 
    
    // 찾는 도시를 반환, 반환으로 빈 공간을 앞 뒤 노드 연결하여 제거
    Node cachePop(Node a){
        
        // a.post 노드 갱신 (반환하는 노드의 뒤에 연결된 노드 갱신)
        
        // tailNode는 post노드 없으니 a.prev=null 처리
        if(tailNode!=a){
            a.post.prev=a.prev;
        }

        
        // a.prev 노드 갱신 (a의 앞 노드 갱신)     
        if(headNode!=a){
            a.prev.post=a.post;
        }
        
        // 반환하는 노드가 꼬리노드 였다면, 반환 노드의 앞 노드를 꼬리 노드로 설정
        if(tailNode==a) {
        	tailNode=a.prev;
        }
        
        // 반환하는 노드가 헤드노드 였다면, 2번째 노드를 헤드 노드로 당기기
        if(a==headNode){
        	headNode=a.post;
        }
        
        //반환할 노드 반환 
        a.prev=null;
        a.post=null;
        cache.remove(a.city);
        return a;

    }
    
    // LRU 캐시의 맨 뒤에 추가
    void cacheAdd(Node a){

        // 최초 노드 삽입시
        if(cache.size()==0){
        	headNode=a;
        }
        // 일반 적인 상황시
        else{
            // 가장 최근에 사용한 경우 LRU 캐시의 맨 뒤에 저장하게 됨
            tailNode.post=a;
            a.prev=tailNode;
        }
        cache.put(a.city,a);
        tailNode=a;
    }
    
}

class Node{
    Node prev;
    String city;
    Node post;
    Node(Node prev,String city,Node post){
        this.prev=prev;
        this.post=post;
        this.city=city;
    }
}
