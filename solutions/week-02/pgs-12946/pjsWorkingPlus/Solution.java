import java.util.*;

class Solution {
    static List<int[]> answerList=new ArrayList<>();
    public int[][] solution(int n) {
        int[][] answer = null;
        
        // 처음 n개가 쌓인 시작 상태, 전체 n개를 start->end (1)->(3) 기둥으로 이동
        hanoi(n,1,3);
        
        answer=new int[answerList.size()][2];
        for(int i=0;i<answerList.size();i++){
            for(int j=0;j<2;j++)
                answer[i][j]=answerList.get(i)[j];
        }
        
        return answer;
    }
    
    // 하노이 dp
    // n : 옮길 블럭 갯수(위에서 부터 n개)
    // start : 출발할 기둥 위치
    // end : 도착할 기둥 위치
    static void hanoi(int n,int start,int end){
        // 옮길 블럭이 없으면 그냥 되돌아감
        if(n==0){
            return;
        }
        // 석래님이 알려준 start와 end 기둥을 제외한 경유지 기둥 확인 법
        // int via=6-(start+end);
        
        // 새로 고안한 xor 계산         
        int via=start^end;
        
        // 목표 : 출발지의 가장 아랫 블록을 도착지에 옮기기
        
        // 가장 아랫 블록 위의 모든 블록을 경유지 기둥에 옮긴다
        hanoi(n-1,start,via);
        // 가장 아랫 블록을 도착지에 옮긴다.
        answerList.add(new int[]{start,end});
        // 경유지 기둥에 옮긴 나머지 블록들을 도착지 블록에 옮긴다.
        hanoi(n-1,via,end);
    }
}