import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        List<Reservation> book_list=new LinkedList<>();
        TreeMap<Integer,Reservation> treeMap=new TreeMap<>();
        int N=book_time.length;
        
        StringTokenizer st=null;
        int hour=-1;
        int min=-1;
        int s=-1;
        int e=-1;
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
        
        book_list.sort((a,b)->Integer.compare(a.start,b.start));
        
        Iterator<Reservation> iter=book_list.iterator();
        
        // for(int i=0;i<N;i++){
        //     System.out.println(book_list.get(i).end+" ");    
        // }
        // System.out.println();
        int roomCnt=0;
        while(iter.hasNext()){
            Reservation temp=iter.next();
            
            Integer key=treeMap.floorKey(temp.start);

            if(key==null){
                // 재사용할 방이 없으면
                
                // 문제 : 넣을 방은 없는데 끝나는 시간이 같은 방이 있다면?
                Reservation sameEndRoom=treeMap.getOrDefault(temp.end,null);
                if(sameEndRoom!=null){
                    sameEndRoom.cnt++;
                }
                else{
                    treeMap.put(temp.end,temp);    
                }
                
                roomCnt++;
            }
            else{
                // 재사용할 방이 있으면
                Reservation room=treeMap.get(key);
                
                // 문제 : 방을
                
                // 동일 시간에 끝나는 방이 복수이면
                if(room.cnt>1){
                    room.cnt--;
                }
                else{
                    treeMap.remove(key);
                }
                
                Reservation sameEndRoom=treeMap.getOrDefault(temp.end,null);
                if(sameEndRoom==null){
                    treeMap.put(temp.end,temp);   
                }
                else{
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
    int cnt;
    public Reservation(int s,int e){
        this.start=s;
        this.end=e;
        cnt=1;
    }
}
