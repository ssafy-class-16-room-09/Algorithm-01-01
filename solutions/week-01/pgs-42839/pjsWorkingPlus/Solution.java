import java.util.*;

class Solution {
    static String[] nums=null;
    static Set<Integer> set=new HashSet<>();
    static int[] visited=null;
    static int answer=0;
    static int n=-1;
    public int solution(String numbers) {
        
        n=numbers.length();
        
        visited=new int[n];    
    
        nums=numbers.split("");
        
        System.out.println(Arrays.toString(nums));
        // 1. 순열로 번호 생성
        // 2. 생성된 번호를 1~n까지 나머지 발생 경우의 수가 2개를 초과하는가?
        dfs(n,"");
        
        
        return answer;
    }
    
    static void dfs(int cnt,String str){

      // 전체 번호를 사용한 숫자일 경우
        if(cnt==0){

            int num=Integer.parseInt(str);
            
            if(!set.contains(num)){
                set.add(num);
                System.out.println(cnt+" "+num);
                int primeCnt=0;
                for(int i=1;i<=num;i++){
                    if(num%i==0&&num!=0){
                        primeCnt++;
                    }
                }
                if(primeCnt==2){
                     answer++;               
                }
            }
            return;
        }
        // 현재 숫자가 set에 존재하는지 확인, 없으면 set에 넣고 소수여부 확인
        // 공백 숫자는 스킵
        if(!str.equals("")){
            int num=Integer.parseInt(str);

            if(!set.contains(num)){
                set.add(num);
                System.out.println(cnt+" "+num);
                int primeCnt=0;
                for(int i=1;i<=num;i++){
                    if(num%i==0&&num!=0){
                        primeCnt++;
                    }
                }
                if(primeCnt==2){
                     answer++;               
                }
            }
        }

        // (현재 숫자*10)+(다음 숫자)
        for(int i=0;i<n;i++){
            if(visited[i]==1){
                continue;
            }
            visited[i]=1;
            dfs(cnt-1,str+nums[i]);            
            visited[i]=0;
        }
    }
}