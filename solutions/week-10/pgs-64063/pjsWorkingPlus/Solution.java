// 시간복잡도 O(n) 공간복잡도 O(n)
// 문제풀이
// 1. 방 번호를 요청한 클라이언트의 수만큼 반복문을 돌면서, 각 클라이언트가 요청한 방 번호를 확인한다.
// 2. 요청한 방 번호가 이미 등록되어 있는지 확인한다.
// 3. 등록되어 있다면, 해당 방의 그룹의 터미널 노드 번호를 가져와서 +1을 한 값을 배정받은 방 번호로 설정한다.
// 4. 등록되어 있지 않다면, 배정받은 방 번호를 현재 클라이언트가 요청한 방 번호로 설정한다.
// 5. 배정받은 방 번호로 새로운 방 객체를 생성하고 등록된 방 맵에 추가한다.
// 6. 이전 방과 이후 방을 확인하여 그룹 정보를 가져온다.\

// 효율성  테스트
// 테스트 1 〉	통과 (72.21ms, 118MB)
// 테스트 2 〉	통과 (77.95ms, 118MB)
// 테스트 3 〉	통과 (57.88ms, 119MB)
// 테스트 4 〉	통과 (56.63ms, 119MB)
// 테스트 5 〉	통과 (83.14ms, 131MB)
// 테스트 6 〉	통과 (75.33ms, 122MB)
// 테스트 7 〉	통과 (80.43ms, 128MB)

import java.util.*;

class Solution {
    // 임시
    static Room tempRoom=null;
    static Group tempGroup=null;

    public long[] solution(long k, long[] room_number) {

        int clientCnt=room_number.length;
        int idx=0;

        long[] answer=new long[clientCnt];

        // Map<방번호, 등록된 방 객체>

        HashMap<Long,Room> registered=new HashMap<>();
        // 이전방, 이후방, 현재방, 이전그룹, 이후그룹
        Room preRoom=null;
        Room postRoom=null;
        Room curRoom=null;
        Group postGroup=null;
        Group preGroup=null;
        // 배정받은 방 번호
        long assignedRoomNum=-1;

        //방을 만들고 그룹에 넣는 과정
        for(long client:room_number){
          // 이미 등록된 방이 있는지 확인
            curRoom=registered.getOrDefault(client,null);

            // 방이 이미 등록되어 있다면, 해당 방의 그룹의 터미널 노드 번호를 가져와서 +1을 한 값을 배정받은 방 번호로 설정
            if(curRoom!=null){
                long temp=Group.find(curRoom.group).terminalNum;
                assignedRoomNum=(temp)+1;
            }
            // 방이 등록되어 있지 않다면, 배정받은 방 번호를 현재 클라이언트가 요청한 방 번호로 설정
            else{
                assignedRoomNum=client;
            }

            // 배정받은 방 번호로 새로운 방 객체를 생성하고 등록된 방 맵에 추가
            curRoom=new Room(assignedRoomNum);
            registered.put(assignedRoomNum,curRoom);

            // 이전 방과 이후 방을 확인하여 그룹 정보를 가져옴
            preRoom=registered.getOrDefault(assignedRoomNum-1,null);
            postRoom=registered.getOrDefault(assignedRoomNum+1,null);

            // 이후 방이 없으면 이후 그룹은 null
            if(postRoom==null){
                postGroup=null;
            }
            else{
                postGroup=postRoom.group;
            }

            // 이전 방이 없으면 이전 그룹은 null
            if(preRoom==null){
                preGroup=null;
            }
            else{
                preGroup=preRoom.group;
            }

            // 앞 존재, 뒤 없음
            if(preGroup!=null&&postGroup==null){
                preGroup.terminalNum=assignedRoomNum;
                curRoom.group=preGroup;
            }
            // 앞 없음, 뒤 존재
            else if(preGroup==null&&postGroup!=null){
                curRoom.group=postGroup;
            }
            // 앞 없음, 뒤 없음
            else if(preGroup==null&&postGroup==null){
                curRoom.group=new Group(assignedRoomNum);
            }
            //(postGroup!=null&&preGroup!=null)
            // 앞 뒤 존재
            else{
              // 이전 그룹의 터미널 번호를 이후 그룹의 터미널 번호로 업데이트하고, 이전 그룹의 다음 그룹을 이후 그룹으로 설정
                tempRoom=registered.get(preGroup.terminalNum);
                preGroup.nextGroup=postGroup;
                // 틀린 풀이법
//                while(tempRoom!=null){
//                    tempRoom.group=postGroup;
//                    tempRoom=registered.getOrDefault(tempRoom.roomId-1,null);
//                }
                curRoom.group=postGroup;
            }

            answer[idx++]=assignedRoomNum;
        }
        return answer;
    }
}

// 방 객체
class Room{
    long roomId;
    public Room(long roomId){
        this.roomId=roomId;
    }
    Group group;
}
// 그룹 객체
class Group{
    long terminalNum;
    Group nextGroup;
    public Group(long num){
        this.terminalNum=num;
        this.nextGroup=null;
    }
    // 그룹의 터미널 노드를 찾는 재귀 함수
    static Group find(Group curGroup){
        if(curGroup.nextGroup==null){
            return curGroup;
        }
        return curGroup.nextGroup=find(curGroup.nextGroup);
    }
}
