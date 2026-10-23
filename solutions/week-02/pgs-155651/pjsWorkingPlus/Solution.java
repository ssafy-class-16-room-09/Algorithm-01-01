import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        // 시작시간 오름차순으로 정렬할 리스트
        List<Reservation> book_list=new LinkedList<>();
        // 비권장 자료구조, PQ 추천
        // TreeMap에 예약 객체를 등록하면 일반적으로 방을 배정함을 의미함
        // key : 예약이 끝나는 시간, value : 예약 객체(1개 방 배정)
        // 다만 TreeMap의 크기가 방의 갯수를 표현하지 못함(PQ는 가능)
        TreeMap<Integer,Reservation> treeMap=new TreeMap<>();
        int N=book_time.length;
        
        StringTokenizer st=null;
        int hour=-1;
        int min=-1;
        int s=-1;
        int e=-1;

        // 모든 예약 객체 생성
        for(int i=0;i<N;i++){

            for(int j=0;j<2;j++){
                st=new StringTokenizer(book_time[i][j],":");
                if(j==0){
                    hour=Integer.parseInt(st.nextToken());
                    
                    min=Integer.parseInt(st.nextToken());

                    s=hour*60+min;

                    // System.out.print("start  "+s);

                }
                else{
                    hour=Integer.parseInt(st.nextToken());
                    min=Integer.parseInt(st.nextToken());

                    e=hour*60+min+10;
                    // System.out.print("  end  "+e);
                }
            }
            // System.out.println();
            book_list.add(new Reservation(s,e));
        }
        
        // 예약 객체를 시작 순서대로 오름차순 정렬
        book_list.sort((a,b)->Integer.compare(a.start,b.start));
        
        // 정렬된 예약을 순회하면서 배정 완료한 예약을 제거하는 이터레이터
        Iterator<Reservation> iter=book_list.iterator();
        
        // TreeMap의 크기가 방 갯수를 카운팅 할 수 없어 roomCnt가 실제 배정된 방 갯수를 나타냄
        int roomCnt=0;

        // 순서대로 배정
        while(iter.hasNext()){
            Reservation temp=iter.next();
            
            // TreeMap 등록된 key는 
            // 현재 배정할 예약의 시작시간 이전에 끝나는 시간을 가진 방을 찾음
            // ex) 현재 배정할 방이 A{1410~1500}
            // TreeMap에 등록된 방이 1개 있고 해당 방에 등록된 예약이 B{1300~1430} 인 경우
            Integer key=treeMap.floorKey(temp.start);

            // 위 케이스의 경우 검색한 temp.start가 1410이고
            // treeMap의 키들 중에 1410 작은 key값이 없으니 배정할 방이 없음 null 값임
            if(key==null){
                // 재사용할 방이 없으면 방을 배정한다.
                
                // 문제 : 넣을 방은 없는데 끝나는 시간이 같은 방이 있다면?
                // 아래 예약 객체의 cnt 변수 설명 참고
                // TreeMap은 종료되는 시간이 같아 중첩되는 예약을 구분하지 못함
                // 그로 인해 생기는 뻘짓임
                Reservation sameEndRoom=treeMap.getOrDefault(temp.end,null);
                if(sameEndRoom!=null){
                    sameEndRoom.cnt++;
                }
                else{
                    treeMap.put(temp.end,temp);    
                }
                
                // 방 새로 배정함
                roomCnt++;
            }
            else{
                // 재사용할 방이 있으면, 기존 treeMap에 등록된 사용한 방을 지움
                // 새로 배정하여 방을 재사용함
                Reservation room=treeMap.get(key);
                
                // TreeMap에 중첩되는 예약으로 인한 뻘짓
                // 동일 시간에 끝나는 방이 복수이면
                if(room.cnt>1){
                    room.cnt--;
                }
                else{
                    treeMap.remove(key);
                }
                
                // 중첩되는 예약 처리를 처리 한후, 다시 중첩되는 배정을 하는가?
                Reservation sameEndRoom=treeMap.getOrDefault(temp.end,null);
                if(sameEndRoom==null){
                    // 미중첩되는 일반적인 방 배정
                    treeMap.put(temp.end,temp);   
                }
                else{
                    // 중첩되는 방배정
                    sameEndRoom.cnt++; 
                }
                
                
            } 
            // System.out.println("size"+roomCnt);
            // System.out.println(treeMap);
            iter.remove();
        }
        answer=roomCnt;
        return answer;
    }
}

class Reservation{
    int start;
    int end;
    // TreeMap에는 중복된 end 값을 가진 예약을 배정할 수 없음
    // 현재 로직상 끝시간이 가장 빠른 방에 다음 예약을 배정하는 방식임

    // 예를 들어 A{14:10~15:00}와 B{13:00~15:00}는 다른 예약 객체로 만들어짐
    // A와 B는 (끝나는 시간)을 key로 하여 A이후 B를 배정하게 될경우 A에 B를 덮어씌움
    // 방이 2개 배정되야 할 것이 중첩돼 1개로 배정되는 상황
    // 이를 해결하기 위해 객체지향을 위배한 임의 필드값 cnt를 넣음

    // TreeMap에 이미 (끝나는 시간)이 등록됐지만 같은 시간 예약을 배정하게 되면,
    // 기존 TreeMap에 존재하는 예약 객체의 cnt++시켜 실제 방 갯수를 카운팅 할 수 있도록 처리함
    int cnt;
    public Reservation(int s,int e){
        this.start=s;
        this.end=e;
        cnt=1;
    }
}