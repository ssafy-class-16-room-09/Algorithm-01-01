import java.util.*;
import java.io.*;

// 크기 비교 선택
enum OP {
    LESS,MORE,EQ
}

class Solution {

    // char -> 숫자id로 변환
    static String[] characters={"A","C","F","J","M","N","R","T"};
    public static Map<String,Integer> map=new HashMap<>();
    
    // 각 캐릭터와 관련된 규칙을 담은 리스트 배열
    // 캐릭터1 : 규칙a, 규칙b
    // 캐릭터2 : 규칙c
    // 캐릭터3 : 규칙a ....
    static List<Rule>[] rules=null;
    
    // <캐릭터id, 캐릭터의 배치 순서> _ 헬퍼 자료구조 _ 캐릭터를 이미 세웠는지 여부를 순회로 확인할 필요 없음
    static Map<Integer,Integer> characterPosition=new HashMap<>();
    // 배치할 수 있는 자리 배열, 요소는 캐릭터ID
    static int[] answerArr=null;

    static int answer=0;    
    
    public int solution(int n, String[] data) throws IOException {
        // ?????????? 이거 뭔데, 이게 있어야 정답 나옴
        answer=0;    

        answerArr=new int[8];
        Arrays.fill(answerArr,-1);
        
        rules=new List[8];
        for(int i=0;i<8;i++){
            rules[i]=new ArrayList<Rule>();
        }
        
        // 캐릭터와 캐릭터Id 매핑
        for(int i=0;i<characters.length;i++){
            map.put(characters[i],i);
        }    
        
        int subject=-1, object=-1, gap=-1;
        String operatorStr=null;
        OP operatorEnum=null;
        Rule tempRule=null;


        // 캐릭터마다 연관된 룰을 Rules 자료구조에 등록
        for(int idx=0;idx<n;idx++){
            
            subject=map.get(data[idx].charAt(0)+"");
            object=map.get(data[idx].charAt(2)+"");
            operatorStr=data[idx].charAt(3)+"";
            
            if(operatorStr.equals("=")){
                operatorEnum=OP.EQ;
            }
            else if(operatorStr.equals("<")){
                operatorEnum=OP.LESS;
            }
            else if(operatorStr.equals(">")){
                operatorEnum=OP.MORE;
            }
            gap=data[idx].charAt(4)-'0';
            tempRule=new Rule(subject,operatorEnum,object,gap);
        
            rules[tempRule.subjectId].add(tempRule);
            tempRule=new Rule(object,operatorEnum,subject,gap);
            rules[tempRule.subjectId].add(tempRule);
            // rules[idx]=tempRule;
        }
        
        // 0번 캐릭터부터 배치 시작
        dfs(0);
        
        return answer;
    }
    
    //캐릭터Id가 cnt인 캐릭터를 배치한다
    static void dfs(int cnt){

        // 모두 배정 완료함 (캐릭터 번호는 0~7번까지만 존재함)
        if(cnt==8){
            answer++;
            return;
        }

        
        // 1안 규칙순서대로 주어와 목적어를 동시에 배정하려고함
        // 이미 캐릭터의 자리가 배정된 경우의 수
        // 1. 주어O 목적어0 <-반복 X
        // 2. 주어0 목적어X <-목적어 반복
        // 3. 주어X 목적어0 <-주어 반복
        // 4. 주어X 목적어X <-주어,목적어 반복문
        // 현재 룰에 만족여부
        // -> 만족하면, 다음 룰 적용
        
        
        // 1안 적용이 어려워 2안 고안
        // 기존 순열 생성에 규칙 준수 여부를 검사, 현재 cnt번 캐릭터를 배정 못하면 해당 가지를 프루닝함
        
        // cnt번 캐릭터가 가진 규칙들
        List<Rule> curCharacterRules=rules[cnt];
        
        // 현재 cnt가 캐릭터Id 지정
        
        boolean satisfyRules=true;
        int tempObjPosition=-1;
        
        // cnt번 캐릭터의 위치 선정
        for(int idx=0;idx<8;idx++){
            // cnt번 캐릭터를 넣을 위치에 이미 다른 캐릭터가 존재하면 다음 자리 확인
            if(answerArr[idx]!=-1)
                continue;
            
            // 현재 캐릭터를 idx번째 위치에 배정
            answerArr[idx]=cnt;
            // 헬퍼 자료구조 갱신
            characterPosition.put(cnt,idx);
            
            satisfyRules=true;
            
            //현재 캐릭터(cnt번 캐릭터)와 관련된 규칙 검사
            for(Rule curRule:curCharacterRules){
                int curSub=curRule.subjectId;
                int curObj=curRule.objectId;
                OP curOP=curRule.operator;
                int gap=curRule.gap;

                //cnt와 배치 관계를 가진 상대 캐릭터가 이미 배정 됐는가?
                tempObjPosition=characterPosition.getOrDefault(curObj,-1);
                // 상대 캐릭터가 아직 배정 전이라면, cnt번 캐릭터는 어디에 놓아도 조건을 충전하니 현재 규칙 만족 처리
                if(tempObjPosition==-1)
                    continue;
                
                // 룰 충족 검사
                if(curOP==OP.EQ&&Math.abs(idx-tempObjPosition)-1!=gap){
                    satisfyRules=false;
                    break;
                }
                else if(curOP==OP.LESS&&Math.abs(idx-tempObjPosition)-1>=gap){
                    satisfyRules=false;
                    break;
                }
                else if(curOP==OP.MORE&&Math.abs(idx-tempObjPosition)-1<=gap){
                    satisfyRules=false;
                    break;
                }
            
            }
            
            // 현재 캐릭터 배정이 모든 룰을 만족하면, 다음 캐릭터 배정
            if(satisfyRules){
                dfs(cnt+1);
            }

            // 현재 캐릭터를 다음 위치에 배정 시키기 위해 배정내역 초기화
            answerArr[idx]=-1;
            characterPosition.remove(cnt);
        }
    }
}



class Rule{
    int subjectId;
    int objectId;
    
    int gap;
    OP operator;
    public Rule(int s,OP op,int o,int g){
        this.subjectId=s;
        this.objectId=o;
        this.operator=op;
        this.gap=g;
    }
    
    @Override
    public String toString(){
        return "{ sub:"+ subjectId+" op: " +operator+" object: "+objectId+" gap: "+gap+" } ";
    }
}
