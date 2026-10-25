import java.util.*;

// 적들의 수가 많은 상위 k개 라운드를 라운드가 진행하면서 갱신해감
// k개 무적권을 상위k개 라운드에서 사용하도록 하여 가장 많은 적을 무시하도록 함
// 위 로직을 따를 때 현재 라운드에서 통과 가능여부 공식
// n>enemySum-kTopSum

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        // enemy 수가 제일 많은 상위 K개 라운드를 오름차순으로 담은 pq
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->Integer.compare(a,b));
        
        int N=enemy.length;
        int enemySum=0;
        
        // 상위 K개 적들의 합
        int kTopSum=0;
        
        int temp=-1;
        
        // 첫번째 라운드부터 stage 라운드 까지 상위 K개에 스킬권을 쓴다고 가정하고 도달여부 확인
        // 현재 상태 stage일 때 stage-1까지 통과 완료한 상태 
        for(int stage=0;stage<N;stage++){
            // 처음 만나는 k개 라운드는 스킬권 갯수만큼 무조건 통과 가능
            if(stage<k){
                enemySum+=enemy[stage];
                kTopSum+=enemy[stage];
                pq.offer(enemy[stage]);
                answer=stage+1;
                continue;
            }
            
            // 0 - (k-1) 라운드까지 등장하는 적들의 총 합 계산
            enemySum+=enemy[stage];
            
            //현재 라운드의 적이 top K개에 해당하는가
            if(pq.peek()<enemy[stage]){
                kTopSum-=pq.poll();
                kTopSum+=enemy[stage];
                pq.offer(enemy[stage]);
            }
            
            // 현재 라운드를 통과할 수 있는가? << 이부분이 위 조건문보다 위에 존재하여 순서 오류로 디버깅 좀 걸림
            if(n<enemySum-kTopSum){
                break;  
            }
            
            answer=stage+1;    
        }        
        return answer;
    }
}

// 3차 접근 : 뒷 스테이지부터 순회하며 선택 스테이지에 도달 가능 여부를 판단
// N-1~0번째 스테이지 순회N
// 현재 스테이지의 적이 상위 K개 묶음에 속하는 확인하는 경우의 수 최악 k
// O(NK) 시간 초과

// import java.util.*;
// class Solution {
//     public int solution(int n, int k, int[] enemy) {
//         int answer = 0;
        
//         // <스테이지에 존재하는 적의 수, 똑같은 스테이지 갯수>
//         TreeMap<Integer,Integer> enemyPerStage=new TreeMap<>();
//         int N=enemy.length;
//         int enemySum=0;
        
//         for(int i=0;i<N;i++){
//             enemySum+=enemy[i];
//             enemyPerStage.put(enemy[i],enemyPerStage.getOrDefault(enemy[i],0)+1);
//         }
//         boolean isInK=false;
//         int count=0;
//         int kTopSum=0;
//         // N단계 스테이지부터 1단계 스테이지 까지 도달 가능 여부 파악 
//         for(int stage=N;stage>0;stage--){
//             // 조건상 스킵권은 1개 이상 이므로, 1단계는 무조건 깰 수 있음
//             if(stage==1){
//                 answer=1;
//                 break;
//             }
            
//             // allEnemy: 현재 스테이지까지 클리어하는데 필요한 모든 적 병력
//             // army: 나의 병력
//             // allEnemy>army 이면 현재 스테이지 도달 불가
//             // 
//             // allEnemy 계산법: 1~현재스테이지까지의 스테이지별 적중에 가장 많은 적이 많은 스테이지 상위 k개 만큼 제거
//             count=0;
//             kTopSum=0;
            
//             for (Integer enemyCnt : enemyPerStage.descendingKeySet()) {
//                 if(isInK){
//                     isInK=false;
//                     break;
//                 }
                    
//                 if (count >= k) 
//                     break;
//                 for(int i=0;i<enemyPerStage.get(enemyCnt);i++){
//                     kTopSum+=enemyCnt; 
//                     count++;
//                 }
//             }
            
//             if(enemySum<=n+kTopSum){
//                 answer=stage;
//                 break;
//             }
//             // System.out.printf("stage %d n+k %d totalEnemy %d\n",stage,n+kTopSum,enemySum);
//             // 현재 스테이지의 적수를 총 적 병력에서 제거
//             enemySum-=enemy[stage-1];
            

//             count=0;
//             for (Integer enemyCnt : enemyPerStage.descendingKeySet()) {
//                 if(count>k)
//                     break;
//                 if (enemyPerStage.get(enemy[stage-1])==enemyCnt){
//                     isInK=true;
//                 }
                    
//                 for(int i=0;i<enemyPerStage.get(enemyCnt);i++){
//                     kTopSum+=enemyCnt; 
//                     count++;
//                 }
//             }
            
//             enemyPerStage.put(enemy[stage-1],enemyPerStage.get(enemy[stage-1])-1);
//             if(enemyPerStage.get(enemy[stage-1])==0){
//                 enemyPerStage.remove(enemy[stage-1]);
//             }
            
            

//         }        
//         return answer;
//     }
// }

// dfs 버전, 혹시나 가능 확인

// import java.util.*;
// class Solution {
//     static int N;
//     static int lastRound=-1;
//     static int[] enemyArr;
//     public int solution(int n, int k, int[] enemy) {
//         int answer = 0;
//         N=enemy.length;
//         enemyArr=enemy;
       
//         dfs(n,0,k);
        
//         answer=lastRound;
//         return answer;
//     }
    
    
//     static void dfs(int curArmy, int curRound, int curSkillCnt){
//         if(curRound==N){
//             lastRound=curRound;
//             return;
//         }
        
//         lastRound=Math.max(lastRound,curRound);
        
//         // 1. 스킬권 사용해서 다음 적을 깬다
//         if(curSkillCnt>0)
//             dfs(curArmy,curRound+1,curSkillCnt-1);
//         // 2. 스킬권 미사용 (다음 스테이지를 깰 수 있는가)
//         if(curArmy-enemyArr[curRound]>=0)
//             dfs(curArmy-enemyArr[curRound],curRound+1,curSkillCnt);
//     }
// }


// bfs 버전 - 메모리 초과

// import java.util.*;
// class Solution {
//     public int solution(int n, int k, int[] enemy) {
//         int answer = 0;
//         int N=enemy.length;
        
//         Round lastRound=null;
//         Round tempRound=new Round(n,0,k);
        
//         Queue<Round> q=new ArrayDeque<>();
//         q.offer(tempRound);
        
//         // q상태(0) -> enemy[0]을 깰 수 있는 병력이 있는가?
//         // 깰 수 있다면 1스테이지를 깸
//         // Round의 상태는 1스테이지를 깬 상태
        
//         int curRound=-1;
//         int curSkillCnt=-1;
//         int curArmy=-1;
        
//         while(!q.isEmpty()){
//             tempRound=q.poll();
//             curRound=tempRound.roundNum;
//             curSkillCnt=tempRound.skillRemain;
//             curArmy=tempRound.armyRemain;
            
//             // 마지막 스테이지라면 
//             if(curRound==N){
//                 lastRound=tempRound; 
//                 continue;
//             }
            
//             // System.out.printf("curRound#%2d remainArmy: %d curEnemy: %d remainSkill: %d\n",curRound,curArmy,enemy[curRound],curSkillCnt);
//             lastRound=tempRound; 
            
//             // 1. 스킬권 사용해서 다음 적을 깬다
//             if(curSkillCnt>0)
//                 q.offer(new Round(curArmy,curRound+1,curSkillCnt-1));
                
//             // 2. 스킬권 미사용 (다음 스테이지를 깰 수 있는가)
//             if(curArmy-enemy[curRound]>=0)
//                 q.offer(new Round(curArmy-enemy[curRound],curRound+1,curSkillCnt));k
//         }
//         answer=lastRound.roundNum;
//         return answer;
//     }
// }

// class Round{
//     int armyRemain;
//     int roundNum;
//     int skillRemain;
//     public Round(int army,int roundNum,int skillRemain){
//         this.armyRemain=army;
//         this.roundNum=roundNum;
//         this.skillRemain=skillRemain;
//     }
// }
