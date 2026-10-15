import java.util.*;
class Solution {
    
    // //유일성 충족 여부/선택 여부
    // static int[] uniq=null;
    //정답
    // static int answer;
    //db
    // static String[][] rel;
    //유일성 충족 갯수
    // static int uniqNum;
    // static Set<String> strSet=new HashSet<>();
    

    static int recordN;
    static int attN;
    
    public int solution(String[][] relation) {
        int answer = 0;
        
        recordN=relation.length;
        attN=relation[0].length;
        
        // 조합 생성
        // 처음에 dfs로 했으나 {1,2,3} ... {2,3} 순서로 키 검사를 하게 되고, {2,3}이 유일성을 만족할 경우 최소성을 만족 못하는 경우가 발생
        // bfs로 변경 필요
        // 큐의 시작을 공백으로 시작
        // 1번째, 2번째, 3번째 속성... 1개짜리 속성을 q에 넣기 
        
        // {1} {2}... {1,2} ...{1,3} 이렇게 담을 bfs큐 
        
        // 하지만 {0,2}...{0,1,2}의 사례가 있다면? 결국 동일한 문제 발생
        // 최소성 판단 반복문 추가함
        Queue<String> q=new ArrayDeque<>();
        Set<String> s=new HashSet<>();
        
        // 1개 속성
        for(int i=0;i<attN;i++){
            q.add(i+" ");
        }
        
        Set<String> checkUniqSet=new HashSet<>();
        String str=null;
        while(!q.isEmpty()){
            checkUniqSet.clear();
            // 키
            String keyStr=q.poll();
            
                        String[] tempA=keyStr.split(" ");
            String tempStr=tempA[tempA.length-1];
            // 현재 마지막으로 선택한 att 반환
            int lastAtt=Integer.parseInt(tempStr);
            
            // 최소성 만족 여부 확인
            boolean isMin=true;
            // 전체 유일키 순회
            for(String uniqKey:s){
                boolean hasAll=true;
                for(String att:uniqKey.split(" ")){
                    if(!keyStr.contains(att)){
                        hasAll=false;
                    }
                }
                if(hasAll){
                    isMin=false;
                    break;
                }
                    
            }
            if(!isMin)
                continue;
            
            
            // record 갯수 확인
            for(int i=0;i<recordN;i++){
                str="";
                for(int j=0;j<attN;j++){
                    if(keyStr.contains(j+"")){
                        str+=relation[i][j]+" ";
                    }
                }
                checkUniqSet.add(str);
            }
                
            // 중복 여부
            if(checkUniqSet.size()==recordN){
                answer++;
                s.add(keyStr);    
                continue;
            }
            
            // 조합 생성
            for(int i=lastAtt+1;i<attN;i++){
                q.add(keyStr+(i+" "));
            }
        }
        return answer;
    }    
}     
        
        
// 초기 dfs로 풀이한 코드 잔재
        
        // Set<String>[] setArr=new Set[attN];
//         // 유일성 충족 열 탐색
//         for(int att=0;att<attN;att++){
//             setArr[att]=new HashSet<String>();
            
//             for(int row=0;row<recordN;row++){
//                 setArr[att].add(relation[row][att]);
//             }
            
//             if(recordN==setArr[att].size()){
//                 // 1개 만으로 유일성 만족하는 속성
//                 uniq[att]=1;
//                 // 1개만으로 유일성 만족하는 속성 갯수
//                 uniqNum++;
//                 // 정답
//                 answer++;
//             }
//         }
                // combi(0,attN-uniqNum);

        
        
            


    
//     static void combi(int start,int cnt){
//         Set<String> tempSet=new HashSet<>();
//         String str="";
//         // 유일성 미충족 속성 전체 포함시
//         if(start==attN){
//             for(int i=0;i<recordN;i++){
//                 str="";
//                 // 각 행의 선택된 요소를 set에 추가
//                 for(int j=0;j<attN;j++){ 
//                     if(uniq[j]==-1){
//                         str+=rel[i][j]+" ";
//                     }
//                 } 
//                 tempSet.add(str);
//             }
//             // set이 유일성을 충족하는가?
//             if(strSet.size()==recordN){
//                 answer++;
//                 // 충족하면 이후 선택하는 열 속성은 최소성 위반이므로 반환
//             }
//             return;
//         }


        
//         //전체 행 순회
//         for(int i=0;i<recordN;i++){
//             str="";
//             // 각 행의 선택된 요소를 set에 추가
//             for(int j=0;j<attN;j++){ 
//                 if(uniq[j]==-1){
//                     str+=rel[i][j]+" ";
//                 }
//             }
            
//             //현재까지 키가 중복 존재하면 종료
//             if(strSet.contains(str)){
//                 return;
//             }
//             else{
//                strSet.add(str); 
//             }
//         }
//         // set이 유일성을 충족하는가?
//         if(strSet.size()==recordN){
//             answer++;
//             // 충족하면 이후 선택하는 열 속성은 최소성 위반이므로 반환
//             return;
//         }
        
//         for(int i=start;i<attN;i++){
        
//             //선택 가능한 번호는 uniq[] 값이 0인 배열 요소
//             if(uniq[i]==0){
//                 // 현재 번호 선택
//                 uniq[i]=-1;
//                 combi(i+1,cnt-1);
//                 uniq[i]=0;
//             }   
//         }
//     }

