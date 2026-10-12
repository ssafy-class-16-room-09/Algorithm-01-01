import java.util.*;

/*
    정렬된 배열에서 맨 앞과 맨 뒤에 포인터 존재
    smallIdex가 가르키는 사람과 bigIdx이 가르키는 사람의 몸무게 합으로 보트 배정
    smallIdx 몸무게+bigIdx<=boat_limit면 보트 1개 배정, smallIdx++,bigIdx--
    boat_limit보다 크면 현재 가장 무거운 사람(bigIdx)에게 보트 1개 배정하고 bigIdx--
    smallIdx>bigIdx 라면 모든 사람 배정 했으니 종료
*/

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int smallIdx=0;
        int bigIdx=people.length-1;
        //해당 Idx의 요소값을 담을 변수
        int lightW=-1;
        int heavyW=-1;
        
        // 모든 사람들에 대한 보트 배정이 완료될 때까지
        while(smallIdx<=bigIdx){

            lightW=people[smallIdx];
            heavyW=people[bigIdx];
            // System.out.println(smallIdx+" "+bigIdx);
            // System.out.println(lightW+" "+heavyW);
            // System.out.println("=====================");
            
            // 사람이 1명 남았을 때
            if(smallIdx==bigIdx){
                answer++;
                break;
            }
            
            // 가장 무거운 사람이 다른 사람과 들어가지 못하면, 가장 무거운 사람만 배정
            if(heavyW+lightW>limit){
                bigIdx--;
            }
            // 가장 무거운 사람이 가장 가벼운 사람과 들어갈 수 있다면 두명에게 보트 배정
            else{
                bigIdx--;
                smallIdx++;
            }
            //보트 1개 배정
            answer++;
        }
        
        return answer;
    }
}